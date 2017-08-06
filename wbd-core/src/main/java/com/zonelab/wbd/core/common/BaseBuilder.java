package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

import static java.util.Objects.requireNonNull;

public abstract class BaseBuilder<B extends BaseBuilder, T extends Base> implements Builder<T> {
    protected Id id = Id.NULL;

    public B setId(final Id id) {
        requireNonNull(id, "Id is null");
        this.id = id;
        //noinspection unchecked
        return (B) this;
    }
}
