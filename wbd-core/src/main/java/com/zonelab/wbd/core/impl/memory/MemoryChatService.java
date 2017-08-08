package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.ChatService;
import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.Services;
import com.zonelab.wbd.core.common.AbstractService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Objects.requireNonNull;

public class MemoryChatService extends AbstractService implements ChatService {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Id, List<Id>> chatMessages = new HashMap<>();

    MemoryChatService(Services services) {
        super(services);
    }

    @Override
    public void addMessageToChat(final Id chatId, final Id messageId) {
        requireNonNull(chatId, "ChatId is null");
        requireNonNull(messageId, "MessageId is null");
        safeExecute(lock.writeLock(), () ->
                chatMessages.computeIfAbsent(chatId, id -> new ArrayList<>()).add(messageId)
        );
    }


    @Override
    public List<Id> getChatMessageIds(final Id chatId) {
        requireNonNull(chatId, "ChatId is null");
        return safeExecute(lock.readLock(), () -> {
            final List<Id> ids = chatMessages.get(chatId);
            return ids == null ? Collections.emptyList() : Collections.unmodifiableList(ids);
        });
    }
}
