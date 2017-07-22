package com.zonelab.wbd.server.api;

import com.zonelab.wbd.server.core.Base;

import static java.util.Objects.requireNonNull;

public class Whiteboard extends Base {
    private String name;
    private Id ownerId;

    public Whiteboard() {
    }

    private Whiteboard(final Whiteboard whiteboard) {
        this.name = whiteboard.name;
        this.ownerId = whiteboard.ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        requireNonNull(name, "Name is null");
        this.name = name;
    }

    public Id getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final Id ownerId) {
        requireNonNull(ownerId, "OwnerId is null");
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Whiteboard{id=" + getId() +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Whiteboard clone() {
        return new Whiteboard(this);
    }
}
