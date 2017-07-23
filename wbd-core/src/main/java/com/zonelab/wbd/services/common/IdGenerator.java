package com.zonelab.wbd.services.common;

import com.zonelab.wbd.services.api.Id;

public final class IdGenerator {
    private long id;

    public IdGenerator(long id) {
        this.id = id;
    }

    public Id next() {
        return Id.create(++id);
    }
}
