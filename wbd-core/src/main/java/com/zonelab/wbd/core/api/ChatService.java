package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public interface ChatService extends Service {
    default ChatMessage createAndAddMessageToChat(final Id chatId, final ChatMessage message) {
        requireNonNull(chatId, "ChatId is null");
        requireNonNull(message, "Message is null");
        final ChatMessage newMessage = services().getChatMessageRepository().create(message);
        addMessageToChat(chatId, newMessage.getId());
        return newMessage;
    }

    void addMessageToChat(Id chatId, Id messageId);

    default List<ChatMessage> getChatMessages(Id chatId) {
        final List<Id> messageIds = getChatMessageIds(chatId);
        final List<ChatMessage> messages = new ArrayList<>(messageIds.size());
        final ChatMessageRepository messageRepository = services().getChatMessageRepository();
        for (Id messageId: messageIds) {
            messages.add(messageRepository.get(messageId));
        }
        return messages;
    }
    List<Id> getChatMessageIds(Id chatId);
}
