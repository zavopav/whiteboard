package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

import java.util.concurrent.atomic.AtomicLong;

public final class IdGenerator {
    private final AtomicLong counter;

    public IdGenerator(long counter) {
        this.counter = new AtomicLong(counter);
    }

    public Id next() {
        return Id.create(counter.incrementAndGet());
    }
}
