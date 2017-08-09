package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.GroupService;
import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.Services;
import com.zonelab.wbd.core.common.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Objects.requireNonNull;

public class MemoryGroupService extends AbstractService implements GroupService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Id, Set<Id>> usersPerGroup = new HashMap<>();
    private final Map<Id, Set<Id>> groupsPerUser = new HashMap<>();

    MemoryGroupService(Services services) {
        super(services);
    }

    @Override
    public Set<Id> getUserIds(final Id groupId) {
        requireNonNull(groupId, "GroupId is null");
        return safeExecute(lock.readLock(), () ->
                usersPerGroup.get(groupId)
        );
    }

    @Override
    public Set<Id> getGroupIds(final Id userId) {
        requireNonNull(userId, "UserId is null");
        return safeExecute(lock.readLock(), () ->
                groupsPerUser.get(userId)
        );
    }

    @Override
    public boolean add(final Id userId, final Id groupId) {
        requireNonNull(userId, "UserId is null");
        requireNonNull(groupId, "GroupId is null");
        log.info("Add: userId={}, groupId={}", userId, groupId);
        return safeExecute(lock.writeLock(), () -> {
            final Set<Id> userIds = usersPerGroup.computeIfAbsent(groupId, id -> new HashSet<>());
            userIds.add(userId);

            final Set<Id> groupIds = groupsPerUser.computeIfAbsent(userId, id -> new HashSet<>());
            return groupIds.add(groupId);
        });
    }

    @Override
    public boolean remove(final Id userId, final Id groupId) {
        // unsupported yet
        return true;
    }

    @Override
    public boolean removeUser(Id userId) {
        // unsupported yet
        return true;
    }

    @Override
    public boolean removeGroup(Id groupId) {
        // unsupported yet
        return true;
    }
}
