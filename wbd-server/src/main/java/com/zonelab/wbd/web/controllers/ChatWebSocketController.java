package com.zonelab.wbd.web.controllers;

import com.google.gson.Gson;
import com.zonelab.wbd.core.api.Chat;
import com.zonelab.wbd.core.api.ChatMessage;
import com.zonelab.wbd.core.api.ChatRepository;
import com.zonelab.wbd.core.api.ChatService;
import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.User;
import com.zonelab.wbd.web.SessionContext;
import com.zonelab.wbd.web.json.ChatMessageJson;
import org.eclipse.jetty.websocket.api.Session;

import java.util.List;

public class ChatWebSocketController extends AbstractWebSocketController {
    private static final Gson GSON = new Gson();

    private final ChatRepository chatRepository = services().getChatRepository();
    private final ChatService chatService = services().getChatService();

    @Override
    public void onMessage(final Session session, final SessionContext ctx, final String text) {
        final ChatMessageJson json = GSON.fromJson(text, ChatMessageJson.class);
        json.setTimestamp(System.currentTimeMillis());

        final User user = getUser(ctx.getUserId());
        final Id chatId = Id.create(json.getChatId());
        final Chat chat = chatRepository.get(chatId);

        if (chat == null || user == null) {
            sendError(session, chatId, "Error");
        } else {
            if (json.getCommand() == ChatMessageJson.Command.LOAD) {
                loadChat(session, chatId);
            } else {
                addMessage(json, chatId, user);
            }
        }
    }

    private void loadChat(final Session session, final Id chatId) {
        final List<ChatMessage> messages = chatService.getChatMessages(chatId);

        for (ChatMessage message : messages) {
            final User author = getUser(message.getAuthorId());
            final ChatMessageJson json = toJsonObject(message, author);
            send(session, GSON.toJson(json));
        }
    }

    private void addMessage(final ChatMessageJson json, final Id chatId, final User user) {
        final ChatMessage chatMessage = fromJsonObject(json, user.getId());
        chatService.createAndAddMessageToChat(chatId, chatMessage);
        json.setAuthor(user.getName());
        broadcast(GSON.toJson(json));
    }

    private void sendError(final Session session, final Id chatId, final String error) {
        final ChatMessageJson json = new ChatMessageJson(ChatMessageJson.Command.ERROR, chatId.asLong(), error);
        send(session, GSON.toJson(json));
    }

    private static ChatMessage fromJsonObject(final ChatMessageJson json, final Id authorId) {
        return ChatMessage.builder()
                .setAuthorId(authorId)
                .setText(json.getText())
                .setTimestamp(json.getTimestamp())
                .build();
    }

    private static ChatMessageJson toJsonObject(final ChatMessage object, final User author) {
        final ChatMessageJson json = new ChatMessageJson();
        json.setChatId(object.getId().asLong());
        json.setAuthor(author.getName());
        json.setText(object.getText());
        json.setTimestamp(object.getTimestamp());
        return json;
    }
}
