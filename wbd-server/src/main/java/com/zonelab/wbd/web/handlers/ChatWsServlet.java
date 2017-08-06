package com.zonelab.wbd.web.handlers;

import com.google.gson.Gson;
import com.zonelab.wbd.core.api.ChatRepository;
import com.zonelab.wbd.core.impl.memory.MemoryServices;
import com.zonelab.wbd.web.json.ChatMessage;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ChatWsServlet extends WebSocketServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Gson gson = new Gson();
    private final ChatRepository chatRepository = MemoryServices.instance().getChatRepository();
    private final Set<Session> sessions = new HashSet<>();

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.setCreator((servletUpgradeRequest, servletUpgradeResponse) -> new WebSocketAdapter(){
            @Override
            public void onWebSocketConnect(final Session session) {
                log.info("Connected: " + session);
                super.onWebSocketConnect(session);
                sessions.add(session);
            }

            @Override
            public void onWebSocketClose(int statusCode, String reason) {
                super.onWebSocketClose(statusCode, reason);
                sessions.remove(getSession());
            }

            @Override
            public void onWebSocketText(final String text) {
                log.info("Received: " + text);
                final ChatMessage msg = gson.fromJson(text, ChatMessage.class);
                msg.setUser("User" + msg.getUserId());
                broadcast(msg);
            }
        });
    }

    private void broadcast(final ChatMessage msg) {
        final String text = gson.toJson(msg);
        for (Session session : sessions) {
            if (session.isOpen()) {
                send(session, text);
            }
        }
    }

    private void send(final Session session, final String text) {
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