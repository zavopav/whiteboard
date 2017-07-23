package com.zonelab.wbd.services.impl.memory;

import com.zonelab.wbd.services.api.Id;
import com.zonelab.wbd.services.api.Whiteboard;
import com.zonelab.wbd.services.api.WhiteboardRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryWhiteboardRepositoryTest {
    private WhiteboardRepository repository;
    @Before
    public void setUp() throws Exception {
        repository = MemoryServiceFactory.instance().getWhiteboardRepository();
        assertEquals(0, repository.size());
    }

    @Test
    public void test() throws Exception {
        Id boardId;
        String boardName = "Test board";
        Id boardOwnerId = Id.create(16);
        {
            // Add board
            final Whiteboard board = new Whiteboard();
            board.setName(boardName);
            board.setOwnerId(boardOwnerId);
            assertSame(Id.NULL, board.getId());
            boardId = repository.create(board);
            assertTrue(boardId.toLong() > 0);
            final Whiteboard boardRepo = repository.find(boardId);
            assertEquals(board.getName(), boardRepo.getName());
            assertEquals(1, repository.size());
        }
        {
            // Add another board
            final Whiteboard board = new Whiteboard();
            board.setName("Alex's board");
            assertSame(Id.NULL, board.getId());
            final Id id = repository.create(board);
            assertTrue(id.toLong() > 0);
            final Whiteboard boardRepo = repository.find(id);
            assertEquals(board.getName(), boardRepo.getName());
            assertEquals(2, repository.size());
        }
        {
            // delete unknown board
            final Whiteboard delete = repository.delete(Id.create(0));
            assertNull(delete);
        }
        {
            // delete test board
            final Whiteboard delete = repository.delete(boardId);
            assertEquals(delete.getId(), boardId);
            assertEquals(delete.getName(), boardName);
            assertEquals(delete.getOwnerId(), boardOwnerId);
            assertEquals(1, repository.size());
            assertNull(repository.find(boardId));
        }
    }
}