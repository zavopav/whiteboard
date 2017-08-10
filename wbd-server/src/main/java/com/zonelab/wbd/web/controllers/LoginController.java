package com.zonelab.wbd.web.controllers;

import com.zonelab.wbd.core.api.User;

import java.util.Objects;

public class LoginController extends AbstractController {
    public final User login(final String login, final String password) {
        log.info("Login: {}", login);
        final User user = User.builder().setName(Objects.toString(login, "Guest")).build();
        return services().getUserRepository().create(user);
    }
}
