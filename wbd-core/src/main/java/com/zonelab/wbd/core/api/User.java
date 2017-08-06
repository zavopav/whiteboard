package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Base;
import com.zonelab.wbd.core.common.BaseBuilder;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class User extends Base<User.Builder> {
    private final String name;

    private User(final Id id, final String name) {
        super(id);
        requireNonNull(name, "Name is null");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public Builder toBuilder() {
        return builder().setId(id).setName(name);
    }

    @Override
    public String toString() {
        return "User{id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseBuilder<Builder, User> {
        private String name;

        public Builder setName(final String name) {
            requireNonNull(name, "Name is null");
            this.name = name;
            return this;
        }

        @Override
        public User build() {
            return new User(id, name);
        }
    }
}
