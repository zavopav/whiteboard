package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Service;

import java.util.Set;

public interface UserWhiteboardService extends Service {
    Set<User> findUsers(Id whiteboardId);
    Set<Whiteboard> findWhiteboards(Id userId);
    void add(User user, Whiteboard whiteboard);
    void remove(User user, Whiteboard whiteboard);
    void remove(User user);
    void remove(Whiteboard whiteboard);
}
