package com.zonelab.wbd.core.api;

import com.zonelab.wbd.core.common.Service;

import java.util.Set;

public interface GroupService extends Service {
    Set<Id> getUserIds(Id groupId);
    Set<Id> getGroupIds(Id userId);
    boolean add(Id userId, Id groupId);
    boolean remove(Id userId, Id groupId);
    boolean removeUser(Id userId);
    boolean removeGroup(Id groupId);
}
