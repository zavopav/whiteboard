package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Service;

import java.util.Set;

public interface WhiteboardService extends Service {
    Set<Id> getUserIds(Id whiteboardId);
    Set<Id> getWhiteboardIds(Id userId);
    boolean add(Id userId, Id whiteboardId);
    boolean remove(Id userId, Id whiteboardId);
    boolean removeUser(Id userId);
    boolean removeWhiteboard(Id whiteboardId);
}
