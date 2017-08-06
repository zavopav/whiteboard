package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.ChatMessage;
import com.zonelab.wbd.core.api.ChatMessageRepository;

public class MemoryChatMessageRepository extends AbstractMemoryRepository<ChatMessage> implements ChatMessageRepository {

    @Override
    public String toString() {
        return "MemoryChatMessageRepository, size=" + size();
    }
}
