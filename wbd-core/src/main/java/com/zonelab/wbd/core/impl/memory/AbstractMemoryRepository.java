package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.common.Base;
import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.common.BaseBuilder;
import com.zonelab.wbd.core.common.IdGenerator;
import com.zonelab.wbd.core.common.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public abstract class AbstractMemoryRepository<T extends Base<? extends BaseBuilder>> implements Repository<T> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final IdGenerator idGenerator = new IdGenerator(System.currentTimeMillis());
    private final ConcurrentHashMap<Id, T> memory = new ConcurrentHashMap<>();

    @Override
    public T get(final Id id) {
        requireNonNull(id, "Id is null");
        return memory.get(id);
    }

    @Override
    public boolean contains(final Id id) {
        requireNonNull(id, "Id is null");
        return memory.containsKey(id);
    }

    @Override
    public Map<Id, T> asMap() {
        return new HashMap<>(memory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T create(final T object) {
        requireNonNull(object, "Object is null");
        final Id id = idGenerator.next();
        final T clone = (T) object.toBuilder().setId(id).build();
        memory.put(id, clone);
        log.info("Created: {}", clone);
        return clone;
    }

    @Override
    public T delete(final Id id) {
        requireNonNull(id, "Id is null");
        final T object = memory.remove(id);
        if (object != null) {
            log.info("Deleted: {}", object);
        } else {
            log.warn("Id not found: {}", id);
        }
        return object;
    }

    @Override
    public int size() {
        return memory.size();
    }
}
