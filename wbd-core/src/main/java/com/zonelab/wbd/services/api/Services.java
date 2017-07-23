package com.zonelab.wbd.services.api;

public interface Services {
    UserRepository getUserRepository();
    WhiteboardRepository getWhiteboardRepository();
    UserWhiteboardService getUserWhiteboardService();
}
