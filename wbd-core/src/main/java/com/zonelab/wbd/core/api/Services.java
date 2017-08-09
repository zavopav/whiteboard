package com.zonelab.wbd.core.api;

public interface Services {
    UserRepository getUserRepository();

    GroupRepository getGroupRepository();
    GroupService getGroupService();

    WhiteboardRepository getWhiteboardRepository();
    WhiteboardService getWhiteboardService();

    ChatRepository getChatRepository();
    ChatMessageRepository getChatMessageRepository();
    ChatService getChatService();
}
