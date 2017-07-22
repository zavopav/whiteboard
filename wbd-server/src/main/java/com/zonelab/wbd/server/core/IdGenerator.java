package com.zonelab.wbd.server.core;

import com.zonelab.wbd.server.api.Id;

public final class IdGenerator {
    private long id;

    public IdGenerator(long id) {
        this.id = id;
    }

    public Id next() {
        return Id.create(++id);
    }
}
