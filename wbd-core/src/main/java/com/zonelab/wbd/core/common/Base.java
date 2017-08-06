package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

import static java.util.Objects.requireNonNull;

public abstract class Base<B extends BaseBuilder> implements ToBuilder<B> {
    protected final Id id;

    protected Base(final Id id) {
        requireNonNull(id, "Id is null");
        this.id = id;
    }

    public final Id getId() {
        return id;
    }
}
