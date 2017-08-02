package com.zonelab.wbd.web;

import com.zonelab.wbd.web.handlers.WhiteboardChatServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer {

    public static void main(String[] args) throws Exception {
        final Server server = new Server();
        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(80);
        server.setConnectors(new Connector[]{connector});

        final WebAppContext app = new WebAppContext();
        app.setContextPath("/");
        app.setResourceBase("frontend/app");
        app.addServlet(WhiteboardChatServlet.class, "/ws/chat");

        server.setHandler(app);
        server.start();
    }
}
