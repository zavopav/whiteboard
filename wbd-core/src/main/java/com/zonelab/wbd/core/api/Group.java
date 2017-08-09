package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Base;
import com.zonelab.wbd.core.common.BaseBuilder;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Group extends Base<Group.Builder> {
    private final String name;
    private final Id parentGroupId;

    private Group(final Id id, final String name, final Id parentGroupId) {
        super(id);
        requireNonNull(name, "Name is null");
        requireNonNull(parentGroupId, "ParentGroupId is null");
        this.name = name;
        this.parentGroupId = parentGroupId;
    }

    public String getName() {
        return name;
    }

    public Id getParentGroupId() {
        return parentGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group that = (Group) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentGroupId, that.parentGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentGroupId);
    }

    @Override
    public Builder toBuilder() {
        return builder().setId(id).setName(name).setParentGroupId(parentGroupId);
    }

    @Override
    public String toString() {
        return "Whiteboard{id=" + id +
                ", name='" + name + '\'' +
                ", parentGroupId=" + parentGroupId +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseBuilder<Builder, Group> {
        private String name;
        private Id parentGroupId;

        public Builder setName(final String name) {
            requireNonNull(name, "Name is null");
            this.name = name;
            return this;
        }

        public Builder setParentGroupId(final Id parentGroupId) {
            requireNonNull(parentGroupId, "ParentGroupId is null");
            this.parentGroupId = parentGroupId;
            return this;
        }

        @Override
        public Group build() {
            return new Group(id, name, parentGroupId);
        }
    }
}
