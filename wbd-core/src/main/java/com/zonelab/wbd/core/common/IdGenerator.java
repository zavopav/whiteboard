package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

public final class IdGenerator {
    private long id;

    public IdGenerator(long id) {
        this.id = id;
    }

    public Id next() {
        return Id.create(++id);
    }
}
