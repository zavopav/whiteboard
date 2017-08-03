package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.api.Chat;
import com.zonelab.wbd.services.api.ChatRepository;

public class MemoryChatRepository extends AbstractMemoryRepository<Chat> implements ChatRepository {

    @Override
    public String toString() {
        return "MemoryChatRepository, size=" + size();
    }
}
