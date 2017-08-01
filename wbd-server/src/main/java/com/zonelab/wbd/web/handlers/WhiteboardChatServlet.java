package com.zonelab.wbd.web.handlers;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WhiteboardChatServlet extends WebSocketServlet {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.setCreator((servletUpgradeRequest, servletUpgradeResponse) -> new WebSocketListener(){

            private Session session;

            @Override
            public void onWebSocketText(String s) {
                log.info("onWebSocketText: {}", s);
                try {
                    send(s);
                } catch (IOException e) {
                    log.error("Failed to send", e);
                }
            }

            private void send(final String s) throws IOException {
                session.getRemote().sendString("Hello " + s);
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