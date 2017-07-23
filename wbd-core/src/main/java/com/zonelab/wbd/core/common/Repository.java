package com.zonelab.wbd.core.common;

import com.zonelab.wbd.core.api.Id;

import java.util.Set;

public interface Repository<T extends Base> extends Service {
    T find(final Id id);
    Set<T> findAll();
    Id create(final T model);
    T delete(final Id id);
    int size();
}