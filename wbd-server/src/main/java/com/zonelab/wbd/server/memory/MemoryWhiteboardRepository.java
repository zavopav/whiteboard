package com.zonelab.wbd.server.memory;

import com.zonelab.wbd.server.api.Whiteboard;
import com.zonelab.wbd.server.api.WhiteboardRepository;

public class MemoryWhiteboardRepository extends AbstractMemoryRepository<Whiteboard> implements WhiteboardRepository {

    @Override
    public String toString() {
        return "MemoryWhiteboardRepository, size=" + size();
    }
}
