package com.zonelab.wbd.web.servlets;

import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.web.controllers.ChatController;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class ChatWebSocketServlet extends WebSocketServlet {
    private static final Logger log = LoggerFactory.getLogger(ChatWebSocketServlet.class);
    private final ChatController chatController = new ChatController();

    @Override
    public void configure(final WebSocketServletFactory factory) {
        log.info("ChatWebSocketServlet.configure");
        factory.setCreator(new ChatWebsocketCreator());
    }

    private final class ChatWebsocketCreator implements WebSocketCreator {
        @Override
        public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
            final HttpSession httpSession = req.getHttpServletRequest().getSession(true);
            final Id userId = (Id) httpSession.getAttribute("userId");
            return new ChatWebSocket(chatController, userId);
        }
    }
}