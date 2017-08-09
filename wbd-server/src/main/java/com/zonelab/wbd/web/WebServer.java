package com.zonelab.wbd.web;

import com.zonelab.wbd.web.servlets.ChatWebSocketServlet;
import com.zonelab.wbd.web.servlets.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer {

    public static void main(String[] args) throws Exception {
        final WebAppContext app = new WebAppContext();
        app.setContextPath("/");
        app.setResourceBase("frontend/app");
        app.addServlet(ChatWebSocketServlet.class, "/ws/chat");
        app.addServlet(LoginServlet.class, "/login");

        final Server server = new Server();
        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(80);
        server.addConnector(connector);
        server.setHandler(app);

        server.start();
        server.dump(System.out);
        server.join();
    }
}
