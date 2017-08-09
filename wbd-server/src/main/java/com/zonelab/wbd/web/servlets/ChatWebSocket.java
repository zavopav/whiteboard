package com.zonelab.wbd.web.servlets;

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

    private final ChatController controller;
    private final Id userId;

    ChatWebSocket(final ChatController controller, final Id userId) {
        this.controller = controller;
        this.userId = userId;
    }

    @OnWebSocketConnect
    public void onWebSocketConnect(final Session session) {
        log.info("[{}] Connected", userId);
        controller.addSession(userId, session);
    }

    @OnWebSocketClose
    public void onWebSocketClose(int statusCode, String reason) {
        log.info("[{}] Disconnected: {} '{}'", userId, statusCode, reason);
        controller.removeSession(userId);
    }

    @OnWebSocketMessage
    public void onWebSocketText(final String text) {
        log.info("[{}] Received: {}", userId, text);
        controller.handle(userId, text);
    }
}
