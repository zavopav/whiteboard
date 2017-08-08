package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.Services;
import com.zonelab.wbd.core.api.WhiteboardService;
import com.zonelab.wbd.core.common.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Objects.requireNonNull;

public class MemoryWhiteboardService extends AbstractService implements WhiteboardService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Id, Set<Id>> usersPerWhiteboard = new HashMap<>();
    private final Map<Id, Set<Id>> whiteboardsPerUser = new HashMap<>();

    MemoryWhiteboardService(Services services) {
        super(services);
    }

    @Override
    public Set<Id> getUserIds(final Id whiteboardId) {
        requireNonNull(whiteboardId, "WhiteboardId is null");
        return safeExecute(lock.readLock(), () ->
                usersPerWhiteboard.get(whiteboardId)
        );
    }

    @Override
    public Set<Id> getWhiteboardIds(final Id userId) {
        requireNonNull(userId, "UserId is null");
        return safeExecute(lock.readLock(), () ->
                whiteboardsPerUser.get(userId)
        );
    }

    @Override
    public boolean add(final Id userId, final Id whiteboardId) {
        requireNonNull(userId, "UserId is null");
        requireNonNull(whiteboardId, "WhiteboardId is null");
        log.info("Add: userId={}, whiteboardId={}", userId, whiteboardId);
        return safeExecute(lock.writeLock(), () -> {
            final Set<Id> userIds = usersPerWhiteboard.computeIfAbsent(whiteboardId, id -> new HashSet<>());
            userIds.add(userId);

            final Set<Id> whiteboardIds = whiteboardsPerUser.computeIfAbsent(userId, id -> new HashSet<>());
            return whiteboardIds.add(whiteboardId);
        });
    }

    @Override
    public boolean remove(final Id userId, final Id whiteboardId) {
        // unsupported yet
        return true;
    }

    @Override
    public boolean removeUser(Id userId) {
        // unsupported yet
        return true;
    }

    @Override
    public boolean removeWhiteboard(Id whiteboardId) {
        // unsupported yet
        return true;
    }
}
