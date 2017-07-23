package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Services;

public abstract class AbstractService implements Service {
    protected final Services services;

    protected AbstractService(Services services) {
        this.services = services;
    }
}
