package com.zonelab.wbd.server.api;

public interface Services {
    UserRepository getUserRepository();
    WhiteboardRepository getWhiteboardRepository();
    UserWhiteboardService getUserWhiteboardService();
}
