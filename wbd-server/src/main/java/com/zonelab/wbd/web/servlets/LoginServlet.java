package com.zonelab.wbd.web.servlets;

import com.zonelab.wbd.core.api.User;
import com.zonelab.wbd.web.SessionContext;
import com.zonelab.wbd.web.controllers.LoginController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";

    private final LoginController controller = new LoginController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        final String login = req.getParameter(LOGIN_PARAM);
        final String password = req.getParameter(PASSWORD_PARAM);
        final User user = controller.login(login, password);
        final SessionContext context = new SessionContext(user.getId(), System.currentTimeMillis());

        final HttpSession httpSession = req.getSession(true);
        SessionContext.setContext(httpSession, context);
    }
}
