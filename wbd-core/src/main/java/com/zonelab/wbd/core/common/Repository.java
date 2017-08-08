package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

import java.util.Map;

public interface Repository<T extends Base> {
    T get(final Id id);
    boolean contains(final Id id);
    Map<Id, T> asMap();
    T create(final T model);
    T delete(final Id id);
    int size();
}
