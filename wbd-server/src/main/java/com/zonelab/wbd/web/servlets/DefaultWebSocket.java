package com.zonelab.wbd.web.servlets;

import com.zonelab.wbd.web.SessionContext;
import com.zonelab.wbd.web.controllers.WebSocketController;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@WebSocket
public class DefaultWebSocket {
    private static final Logger log = LoggerFactory.getLogger(DefaultWebSocket.class);

    private final WebSocketController controller;
    private final SessionContext ctx;
    private volatile Session session;

    DefaultWebSocket(final WebSocketController controller, final SessionContext ctx) {
        this.controller = controller;
        this.ctx = ctx;
    }

    @OnWebSocketConnect
    public void onWebSocketConnect(final Session session) {
        log.info("[{}] Connected", ctx.getUserId());
        this.session = session;
        if (!controller.addSession(session, ctx)) {
            session.close(403, "Connection rejected");
        }
    }

    @OnWebSocketClose
    public void onWebSocketClose(int statusCode, String reason) {
        log.info("[{}] Disconnected: {} '{}'", ctx.getUserId(), statusCode, reason);
        controller.removeSession(session);
    }

    @OnWebSocketMessage
    public void onWebSocketText(final String text) {
        log.info("[{}] Received: {}", ctx.getUserId(), text);
        controller.onMessage(session, ctx, text);
    }
}
