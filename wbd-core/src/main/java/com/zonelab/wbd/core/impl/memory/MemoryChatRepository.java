package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.Chat;
import com.zonelab.wbd.core.api.ChatRepository;

public class MemoryChatRepository extends AbstractMemoryRepository<Chat> implements ChatRepository {

    @Override
    public String toString() {
        return "MemoryChatRepository, size=" + size();
    }
}
