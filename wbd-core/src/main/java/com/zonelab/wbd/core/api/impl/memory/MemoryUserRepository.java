package com.zonelab.wbd.core.api.impl.memory;

import com.zonelab.wbd.core.api.User;
import com.zonelab.wbd.core.api.UserRepository;

public class MemoryUserRepository extends AbstractMemoryRepository<User> implements UserRepository {

    @Override
    public String toString() {
        return "MemoryUserRepository, size=" + size();
    }
}
