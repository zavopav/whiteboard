package com.zonelab.wbd.web.handlers;

import com.google.gson.Gson;
import com.zonelab.wbd.web.json.ChatMessage;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WhiteboardChatServlet extends WebSocketServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Gson gson = new Gson();

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.setCreator((servletUpgradeRequest, servletUpgradeResponse) -> new WebSocketListener(){

            private Session session;

            @Override
            public void onWebSocketText(String s) {
                log.info("onWebSocketText: {}", s);
                try {
                    ChatMessage msg = gson.fromJson(s, ChatMessage.class);
                    msg.setUser("User" + msg.getUserId());
                    send(msg);
                } catch (IOException e) {
                    log.error("Failed to send", e);
                }
            }

            private void send(final ChatMessage msg) throws IOException {
                session.getRemote().sendString(gson.toJson(msg));
            }

            @Override
            public void onWebSocketConnect(Session session) {
                log.info("Connected");
                this.session = session;
            }

            @Override
            public void onWebSocketClose(int i, String s) {
                log.info("onWebSocketClose");
            }
            @Override
            public void onWebSocketError(Throwable e) {
                log.error("onWebSocketError", e);
            }
            @Override
            public void onWebSocketBinary(byte[] bytes, int i, int i1) {
                log.info("onWebSocketBinary");
            }
        });
    }
}