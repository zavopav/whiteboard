package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.api.*;

public final class MemoryServiceFactory implements Services {
    private static final Services INSTANCE = new MemoryServiceFactory();

    private final UserRepository userRepository;
    private final WhiteboardRepository whiteboardRepository;
    private final ChatRepository chatRepository;
    private final UserWhiteboardService userWhiteboardService;

    private MemoryServiceFactory() {
        // repositories first
        userRepository = new MemoryUserRepository();
        whiteboardRepository = new MemoryWhiteboardRepository();
        chatRepository = new MemoryChatRepository();
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
    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    @Override
    public UserWhiteboardService getUserWhiteboardService() {
        return userWhiteboardService;
    }

    public static Services instance() {
        return INSTANCE;
    }
}
