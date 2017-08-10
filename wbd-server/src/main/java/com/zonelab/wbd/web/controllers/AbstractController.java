package com.zonelab.wbd.web.controllers;

import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.Services;
import com.zonelab.wbd.core.api.User;
import com.zonelab.wbd.core.impl.memory.MemoryServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController implements Controller {
    final Logger log = LoggerFactory.getLogger(getClass());
    private final Services services = MemoryServices.instance();

    @Override
    public Services services() {
        return services;
    }

    User getUser(final Id userId) {
        return services.getUserRepository().get(userId);
    }
}
