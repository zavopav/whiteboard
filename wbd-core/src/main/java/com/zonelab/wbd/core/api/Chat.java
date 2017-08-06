package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Base;
import com.zonelab.wbd.core.common.BaseBuilder;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Chat extends Base<Chat.Builder> {
    private final Id ownerId;

    private Chat(final Id id, final Id ownerId) {
        super(id);
        requireNonNull(ownerId, "OwnerId is null");
        this.ownerId = ownerId;
    }

    public Id getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) &&
                Objects.equals(ownerId, chat.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId);
    }

    @Override
    public Builder toBuilder() {
        return builder().setId(id).setOwnerId(ownerId);
    }

    @Override
    public String toString() {
        return "Chat{id=" + id +
                ", ownerId=" + ownerId +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseBuilder<Builder, Chat> {
        private Id ownerId;

        public Builder setOwnerId(final Id ownerId) {
            requireNonNull(ownerId, "OwnerId is null");
            this.ownerId = ownerId;
            return this;
        }

        @Override
        public Chat build() {
            return new Chat(id, ownerId);
        }
    }
}
