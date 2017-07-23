package com.zonelab.wbd.core.api;

public final class Id implements Comparable<Id> {
    public static final Id NULL = new Id(-1);
    private final long id;

    private Id(final long id) {
        this.id = id;
    }

    public static Id create(final long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Bad id value: " + id);
        }
        return new Id(id);
    }

    public long toLong() {
        return id;
    }

    @Override
    public int compareTo(final Id o) {
        return Long.compare(id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id id1 = (Id) o;

        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return this == NULL ? "NULL" : String.valueOf(id);
    }
}
