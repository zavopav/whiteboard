package com.zonelab.wbd.web.controllers;

import com.zonelab.wbd.web.SessionContext;
import org.eclipse.jetty.websocket.api.Session;

public interface WebSocketController extends Controller {
    boolean addSession(final Session session, final SessionContext ctx);

    void removeSession(final Session session);

    void onMessage(final Session session, final SessionContext ctx, final String text);

}
