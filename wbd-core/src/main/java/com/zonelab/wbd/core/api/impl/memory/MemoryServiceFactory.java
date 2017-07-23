package com.zonelab.wbd.core.api.impl.memory;

import com.zonelab.wbd.core.api.Services;
import com.zonelab.wbd.core.api.UserRepository;
import com.zonelab.wbd.core.api.UserWhiteboardService;
import com.zonelab.wbd.core.api.WhiteboardRepository;

public final class MemoryServiceFactory implements Services {
    private static final Services INSTANCE = new MemoryServiceFactory();

    private final UserRepository userRepository;
    private final WhiteboardRepository whiteboardRepository;
    private final UserWhiteboardService userWhiteboardService;

    private MemoryServiceFactory() {
        // repositories first
        userRepository = new MemoryUserRepository();
        whiteboardRepository = new MemoryWhiteboardRepository();
        // then services
        userWhiteboardService = new MemoryUserWhiteboardService(this);
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public WhiteboardRepository getWhiteboardRepository() {
        return whiteboardRepository;
    }

    @Override
    public UserWhiteboardService getUserWhiteboardService() {
        return userWhiteboardService;
    }

    public static Services instance() {
        return INSTANCE;
    }
}
