package com.zonelab.wbd.core.impl.memory;


import com.zonelab.wbd.core.api.ChatMessageRepository;
import com.zonelab.wbd.core.api.ChatRepository;
import com.zonelab.wbd.core.api.ChatService;
import com.zonelab.wbd.core.api.Services;
import com.zonelab.wbd.core.api.UserRepository;
import com.zonelab.wbd.core.api.WhiteboardRepository;
import com.zonelab.wbd.core.api.WhiteboardService;

public final class MemoryServices implements Services {
    private static final Services INSTANCE = new MemoryServices();

    private final UserRepository userRepository;
    private final WhiteboardRepository whiteboardRepository;
    private final WhiteboardService whiteboardService;
    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatService chatService;

    private MemoryServices() {
        // repositories first
        userRepository = new MemoryUserRepository();
        whiteboardRepository = new MemoryWhiteboardRepository();
        chatRepository = new MemoryChatRepository();
        chatMessageRepository = new MemoryChatMessageRepository();
        // then services
        whiteboardService = new MemoryWhiteboardService(this);
        chatService = new MemoryChatService(this);
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
    public WhiteboardService getWhiteboardService() {
        return whiteboardService;
    }

    @Override
    public ChatRepository getChatRepository() {
        return chatRepository;
    }

    @Override
    public ChatMessageRepository getChatMessageRepository() {
        return chatMessageRepository;
    }

    @Override
    public ChatService getChatService() {
        return chatService;
    }

    public static Services instance() {
        return INSTANCE;
    }
}
