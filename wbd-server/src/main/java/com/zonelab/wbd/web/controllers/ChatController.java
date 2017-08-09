package com.zonelab.wbd.web.controllers;

import com.google.gson.Gson;
import com.zonelab.wbd.core.api.ChatMessage;
import com.zonelab.wbd.core.api.ChatService;
import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.impl.memory.MemoryServices;
import com.zonelab.wbd.web.json.Json;
import com.zonelab.wbd.web.json.JsonChatMessage;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private static final Gson GSON = new Gson();

    private final ChatService chatService = MemoryServices.instance().getChatService();
    private final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    public void addSession(final Session session) {
        sessions.add(session);
    }

    public void removeSession(final Session session) {
        sessions.remove(session);
    }

    public void handle(final Session session, final String text) {
        final JsonChatMessage json = GSON.fromJson(text, JsonChatMessage.class);
        final Id chatId = Id.create(json.getChatId());

        if (json.getCommand() == JsonChatMessage.Command.LOAD) {
            loadChat(session, chatId);
        } else {
            final ChatMessage chatMessage = Json.fromJson(json);
            chatService.createAndAddMessageToChat(chatId, chatMessage);
            json.setAuthor("test");
            broadcast(json);
        }
    }

    public void loadChat(final Session session, final Id chatId) {
        final List<ChatMessage> messages = chatService.getChatMessages(chatId);
        for (ChatMessage message : messages) {
            final JsonChatMessage jsonMessage = Json.toJson(message);
            send(session, jsonMessage);
        }

    }

    public void broadcast(final JsonChatMessage msg) {
        final String text = GSON.toJson(msg);
        for (Session session : sessions) {
                send(session, text);
        }
    }

    public static void send(final Session session, final JsonChatMessage msg) {
        final String text = GSON.toJson(msg);
        send(session, text);
    }

    public static void send(final Session session, final String text) {
        if (session.isOpen()) {
            session.getRemote().sendString(text, new WriteCallback() {
                @Override
                public void writeFailed(Throwable x) {
                    log.error("Failed to send to " + session, x);
                }

                @Override
                public void writeSuccess() {
                    if (log.isDebugEnabled()) {
                        log.debug("Sent: " + text);
                    }
                }
            });
        }
    }
}
