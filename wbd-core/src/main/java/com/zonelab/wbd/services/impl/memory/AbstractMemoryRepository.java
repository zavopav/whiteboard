package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.common.Base;
import com.zonelab.wbd.services.api.Id;
import com.zonelab.wbd.services.common.IdGenerator;
import com.zonelab.wbd.services.common.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public abstract class AbstractMemoryRepository<T extends Base> implements Repository<T> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final IdGenerator idGenerator = new IdGenerator(System.currentTimeMillis());
    private final Map<Id, T> memory = new HashMap<>();

    @Override
    public T find(final Id id) {
        requireNonNull(id, "Id is null");
        return memory.get(id);
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(memory.values());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Id create(final T object) {
        requireNonNull(object, "Object is null");
        final Id id = idGenerator.next();
        final T clone = (T) object.clone();
        clone.setId(id);
        memory.put(id, clone);
        log.info("Created new object: {}", clone);
        return id;
    }

    @Override
    public T delete(final Id id) {
        requireNonNull(id, "Id is null");
        final T object = memory.remove(id);
        if (object != null) {
            log.info("Deleted object: {}", object);
        } else {
            log.info("Object with id={} not found", id);
        }
        return object;
    }

    @Override
    public int size() {
        return memory.size();
    }
}
