package com.zonelab.wbd.web.servlets;

import com.google.gson.Gson;
import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.web.controllers.ChatController;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@WebSocket
public class ChatWebSocket {
    private static final Logger log = LoggerFactory.getLogger(ChatWebSocketServlet.class);
    private static final Gson GSON = new Gson();

    private final ChatController controller;
    private final Id userId;

    private volatile Session session;

    ChatWebSocket(final ChatController controller, final Id userId) {
        this.controller = controller;
        this.userId = userId;

    }

    @OnWebSocketConnect
    public void onWebSocketConnect(final Session session) {
        log.info("[{}] Connected", userId);
        this.session = session;
        controller.addSession(session);
    }

    @OnWebSocketClose
    public void onWebSocketClose(int statusCode, String reason) {
        log.info("[{}] Disconnected: {} '{}'", userId, statusCode, reason);
        controller.removeSession(session);
    }

    @OnWebSocketMessage
    public void onWebSocketText(final String text) {
        log.info("[{}] Received: {}", userId, text);
        controller.handle(session, text);
    }
}
