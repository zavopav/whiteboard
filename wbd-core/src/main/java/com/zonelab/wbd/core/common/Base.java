package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

import static java.util.Objects.requireNonNull;

public abstract class Base implements Cloneable {
    private Id id = Id.NULL;

    public final Id getId() {
        return id;
    }

    public final void setId(final Id id) {
        requireNonNull(id, "Id is null");
        if (this.id != Id.NULL) {
            throw new IllegalStateException("Please use object.clone() to create a new object");
        }
        this.id = id;
    }

    @Override
    public abstract Base clone();
}
