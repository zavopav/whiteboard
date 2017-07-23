package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Base;

import static java.util.Objects.requireNonNull;

public class User extends Base {
    private String name;

    public User() {
    }

    private User(final User user) {
        this.name = user.name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        requireNonNull(name, "Name is null");
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public User clone() {
        return new User(this);
    }
}
