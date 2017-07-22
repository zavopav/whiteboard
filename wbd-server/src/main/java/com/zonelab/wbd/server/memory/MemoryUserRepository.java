package com.zonelab.wbd.server.memory;

import com.zonelab.wbd.server.api.User;
import com.zonelab.wbd.server.api.UserRepository;

public class MemoryUserRepository extends AbstractMemoryRepository<User> implements UserRepository {

    @Override
    public String toString() {
        return "MemoryUserRepository, size=" + size();
    }
}
