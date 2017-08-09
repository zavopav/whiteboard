package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Base;
import com.zonelab.wbd.core.common.BaseBuilder;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class ChatMessage extends Base<ChatMessage.Builder> {
    private final Id authorId;
    private final String text;
    private final long timestamp;

    private ChatMessage(final Id id, final Id authorId, final String text, final long timestamp) {
        super(id);
        requireNonNull(authorId, "AuthorId is null");
        requireNonNull(text, "Text is null");
        this.authorId = authorId;
        this.text = text;
        this.timestamp = timestamp;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return timestamp == that.timestamp &&
                Objects.equals(id, that.id) &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, text, timestamp);
    }

    @Override
    public Builder toBuilder() {
        return builder().setId(id).setAuthorId(authorId).setText(text).setTimestamp(timestamp);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "authorId=" + authorId +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseBuilder<Builder, ChatMessage> {
        private Id authorId;
        private String text;
        private long timestamp;

        public Builder setAuthorId(Id authorId) {
            requireNonNull(authorId, "AuthorId is null");
            this.authorId = authorId;
            return this;
        }

        public Builder setText(String text) {
            requireNonNull(text, "Text is null");
            this.text = text;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        @Override
        public ChatMessage build() {
            return new ChatMessage(id, authorId, text, timestamp);
        }
    }
}
