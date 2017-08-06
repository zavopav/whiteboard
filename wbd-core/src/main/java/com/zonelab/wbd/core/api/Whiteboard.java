package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Base;
import com.zonelab.wbd.core.common.BaseBuilder;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Whiteboard extends Base<Whiteboard.Builder> {
    private final String name;
    private final Id ownerId;

    private Whiteboard(final Id id, final String name, final Id ownerId) {
        super(id);
        requireNonNull(name, "Name is null");
        requireNonNull(ownerId, "OwnerId is null");
        this.name = name;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public Id getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Whiteboard that = (Whiteboard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerId);
    }

    @Override
    public Builder toBuilder() {
        return builder().setId(id).setName(name).setOwnerId(ownerId);
    }

    @Override
    public String toString() {
        return "Whiteboard{id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseBuilder<Builder, Whiteboard> {
        private String name;
        private Id ownerId;

        public Builder setName(final String name) {
            requireNonNull(name, "Name is null");
            this.name = name;
            return this;
        }

        public Builder setOwnerId(final Id ownerId) {
            requireNonNull(ownerId, "OwnerId is null");
            this.ownerId = ownerId;
            return this;
        }

        @Override
        public Whiteboard build() {
            return new Whiteboard(id, name, ownerId);
        }
    }
}
