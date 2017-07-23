package com.zonelab.wbd.services.common;

import com.zonelab.wbd.services.api.Services;

public abstract class AbstractService implements Service {
    protected final Services services;

    protected AbstractService(Services services) {
        this.services = services;
    }
}
