package com.zonelab.wbd.core.api;

public interface Services {
    UserRepository getUserRepository();
    WhiteboardRepository getWhiteboardRepository();
    UserWhiteboardService getUserWhiteboardService();
}
