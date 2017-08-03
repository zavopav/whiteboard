package com.zonelab.wbd.services.api;

import com.zonelab.wbd.services.common.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Chat extends Base {
    public static class Message {
        private final Id authorId;
        private final String text;
        private final long timestamp;

        public Message(Id authorId, String text) {
            Objects.requireNonNull(authorId);
            Objects.requireNonNull(text);
            this.authorId = authorId;
            this.text = text;
            this.timestamp = System.currentTimeMillis();
        }

        public Id getAuthorId() {
            return authorId;
        }

        public String getText() {
            return text;
        }

        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "authorId=" + authorId +
                    ", text='" + text + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
    private Id ownerId;
    private final List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    private Chat(final Chat chat) {
        this.ownerId = chat.ownerId;
    }

    public Id getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final Id ownerId) {
        requireNonNull(ownerId, "OwnerId is null");
        this.ownerId = ownerId;
    }

    public void addMessage(final Message message) {
        Objects.requireNonNull(message, "Message is null");
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "Chat{id=" + getId() +
                ", ownerId=" + ownerId +
                ", messagesCount=" + messages.size() +
                '}';
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Chat clone() {
        return new Chat(this);
    }
}
