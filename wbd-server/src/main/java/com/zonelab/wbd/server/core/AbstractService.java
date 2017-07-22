package com.zonelab.wbd.server.core;

import com.zonelab.wbd.server.api.Services;

public abstract class AbstractService implements Service {
    protected final Services services;

    protected AbstractService(Services services) {
        this.services = services;
    }
}
