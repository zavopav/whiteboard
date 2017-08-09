package com.zonelab.wbd.web.servlets;

import com.zonelab.wbd.core.api.Id;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        final HttpSession session = req.getSession(true);
        session.setAttribute("userId", Id.create(13));
    }
}
