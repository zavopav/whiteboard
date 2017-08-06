package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.Whiteboard;
import com.zonelab.wbd.core.api.WhiteboardRepository;

public class MemoryWhiteboardRepository extends AbstractMemoryRepository<Whiteboard> implements WhiteboardRepository {

    @Override
    public String toString() {
        return "MemoryWhiteboardRepository, size=" + size();
    }
}
