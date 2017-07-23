package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.api.User;
import com.zonelab.wbd.services.api.UserRepository;

public class MemoryUserRepository extends AbstractMemoryRepository<User> implements UserRepository {

    @Override
    public String toString() {
        return "MemoryUserRepository, size=" + size();
    }
}
