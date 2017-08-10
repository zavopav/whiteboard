package com.zonelab.wbd.web;

import com.zonelab.wbd.core.api.Id;

import javax.servlet.http.HttpSession;

public final class SessionContext {
    private static final String ATTRIBUTE = "context";

    private final Id userId;
    private final long loginTimeMs;

    public SessionContext(Id userId, long loginTimeMs) {
        this.userId = userId;
        this.loginTimeMs = loginTimeMs;
    }

    public Id getUserId() {
        return userId;
    }

    public long getLoginTimeMs() {
        return loginTimeMs;
    }

    public static void setContext(HttpSession httpSession, SessionContext ctx) {
        httpSession.setAttribute(ATTRIBUTE, ctx);
    }

    public static SessionContext getContext(HttpSession httpSession) {
        return (SessionContext) httpSession.getAttribute(ATTRIBUTE);
    }
}
