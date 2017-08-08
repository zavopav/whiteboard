package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Services;

import java.util.Objects;
import java.util.concurrent.locks.Lock;

public abstract class AbstractService implements Service {
    private final Services services;

    protected AbstractService(Services services) {
        Objects.requireNonNull(services, "Services object is null");
        this.services = services;
    }

    @Override
    public final Services services() {
        return services;
    }

    protected static<T> T safeExecute(final Lock lock, final Executable<T> executable) {
        lock.lock();
        try {
            return executable.execute();
        } finally {
            lock.unlock();
        }
    }

    protected interface Executable<T> {
        T execute();
    }
}
