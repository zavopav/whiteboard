package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.api.Id;
import com.zonelab.wbd.services.api.Services;
import com.zonelab.wbd.services.api.User;
import com.zonelab.wbd.services.api.UserRepository;
import com.zonelab.wbd.services.api.UserWhiteboardService;
import com.zonelab.wbd.services.api.Whiteboard;
import com.zonelab.wbd.services.api.WhiteboardRepository;
import com.zonelab.wbd.services.common.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class MemoryUserWhiteboardService extends AbstractService implements UserWhiteboardService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Map<Id, Set<Id>> usersPerWhiteboard = new HashMap<>();
    private final Map<Id, Set<Id>> whiteboardsPerUser = new HashMap<>();

    public MemoryUserWhiteboardService(Services services) {
        super(services);
    }

    @Override
    public Set<User> findUsers(final Id whiteboardId) {
        requireNonNull(whiteboardId, "Whiteboard id is null");
        final Set<Id> userIds = usersPerWhiteboard.get(whiteboardId);
        final Set<User> users;
        if (userIds != null && !userIds.isEmpty()) {
            users = new HashSet<>(userIds.size());
            final UserRepository userService = services.getUserRepository();
            for (Id userId : userIds) {
                final User user = userService.find(userId);
                if (user != null) {
                    users.add(user);
                } else {
                    log.warn("Unknown user id: {}", userId);
                }
            }
        } else {
            users = Collections.emptySet();
        }
        return users;
    }

    @Override
    public Set<Whiteboard> findWhiteboards(final Id userId) {
        requireNonNull(userId, "User id is null");
        final Set<Id> whiteboardIds = whiteboardsPerUser.get(userId);
        final Set<Whiteboard> whiteboards;
        if (whiteboardIds != null && !whiteboardIds.isEmpty()) {
            whiteboards = new HashSet<>(whiteboardIds.size());
            final WhiteboardRepository whiteboardService = services.getWhiteboardRepository();
            for (Id whiteboardId : whiteboardIds) {
                final Whiteboard whiteboard = whiteboardService.find(whiteboardId);
                if (whiteboard != null) {
                    whiteboards.add(whiteboard);
                } else {
                    log.warn("Unknown whiteboard id: {}", whiteboardId);
                }
            }
        } else {
            whiteboards = Collections.emptySet();
        }
        return whiteboards;
    }

    @Override
    public void add(final User user, final Whiteboard whiteboard) {
        requireNonNull(user, "User is null");
        requireNonNull(whiteboard, "Whiteboard is null");

        final Set<Id> userIds = usersPerWhiteboard.computeIfAbsent(whiteboard.getId(), id -> new HashSet<>());
        userIds.add(user.getId());

        final Set<Id> whiteboardIds = whiteboardsPerUser.computeIfAbsent(user.getId(), id -> new HashSet<>());
        whiteboardIds.add(whiteboard.getId());
    }

    @Override
    public void remove(final User user, final Whiteboard whiteboard) {
        // unsupported yet
    }

    @Override
    public void remove(User user) {
        // unsupported yet
    }

    @Override
    public void remove(Whiteboard whiteboard) {
        // unsupported yet
    }
}
