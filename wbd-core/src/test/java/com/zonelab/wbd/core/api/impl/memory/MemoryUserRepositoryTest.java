package com.zonelab.wbd.core.api.impl.memory;

import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.User;
import com.zonelab.wbd.core.api.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryUserRepositoryTest {
    private UserRepository repository;
    @Before
    public void setUp() throws Exception {
        repository = MemoryServiceFactory.instance().getUserRepository();
        assertEquals(0, repository.size());
    }

    @Test
    public void test() throws Exception {
        Id olegId;
        String olegName = "Oleg";
        {
            // Add user Oleg
            final User user = new User();
            user.setName(olegName);
            assertSame(Id.NULL, user.getId());
            final Id userId = repository.create(user);
            assertTrue(userId.toLong() > 0);
            final User userRepo = repository.find(userId);
            assertEquals(user.getName(), userRepo.getName());
            assertEquals(1, repository.size());
            olegId = userId;
        }
        {
            // Add user Alex
            final User user = new User();
            user.setName("Alex");
            assertSame(Id.NULL, user.getId());
            final Id userId = repository.create(user);
            assertTrue(userId.toLong() > 0);
            final User userRepo = repository.find(userId);
            assertEquals(user.getName(), userRepo.getName());
            assertEquals(2, repository.size());
        }
        {
            // delete unknown user
            final User delete = repository.delete(Id.create(0));
            assertNull(delete);
        }
        {
            // delete Oleg
            final User delete = repository.delete(olegId);
            assertEquals(delete.getId(), olegId);
            assertEquals(delete.getName(), olegName);
            assertEquals(1, repository.size());
            assertNull(repository.find(olegId));
        }
    }
}