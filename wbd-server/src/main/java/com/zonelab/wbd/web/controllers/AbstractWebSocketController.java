package com.zonelab.wbd.web.controllers;

import com.zonelab.wbd.web.SessionContext;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WriteCallback;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractWebSocketController extends AbstractController implements WebSocketController {
    private final ConcurrentHashMap<Session, SessionContext> sessions = new ConcurrentHashMap<>();

    @Override
    public boolean addSession(Session session, SessionContext ctx) {
        sessions.put(session, ctx);
        return true;
    }

    @Override
    public void removeSession(Session session) {
        sessions.remove(session);
    }

    void broadcast(final String text) {
        for (Session session : sessions.keySet()) {
            send(session, text);
        }
    }

    void send(final Session session, final String text) {
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
