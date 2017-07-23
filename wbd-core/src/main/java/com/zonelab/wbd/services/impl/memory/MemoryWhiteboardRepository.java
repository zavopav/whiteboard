package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.api.Whiteboard;
import com.zonelab.wbd.services.api.WhiteboardRepository;

public class MemoryWhiteboardRepository extends AbstractMemoryRepository<Whiteboard> implements WhiteboardRepository {

    @Override
    public String toString() {
        return "MemoryWhiteboardRepository, size=" + size();
    }
}
