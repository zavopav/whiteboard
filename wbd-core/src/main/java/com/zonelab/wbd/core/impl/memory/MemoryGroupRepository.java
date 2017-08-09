package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.Group;
import com.zonelab.wbd.core.api.GroupRepository;

public class MemoryGroupRepository extends AbstractMemoryRepository<Group> implements GroupRepository {

    @Override
    public String toString() {
        return "MemoryGroupRepository, size=" + size();
    }
}
