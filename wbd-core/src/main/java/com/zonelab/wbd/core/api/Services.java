package com.zonelab.wbd.core.api;

public interface Services {
    UserRepository getUserRepository();

    WhiteboardRepository getWhiteboardRepository();
    WhiteboardService getWhiteboardService();

    ChatRepository getChatRepository();
    ChatMessageRepository getChatMessageRepository();
    ChatService getChatService();
}
