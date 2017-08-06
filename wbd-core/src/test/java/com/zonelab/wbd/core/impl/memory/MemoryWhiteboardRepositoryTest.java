package com.zonelab.wbd.core.impl.memory;

import com.zonelab.wbd.core.api.Id;
import com.zonelab.wbd.core.api.Whiteboard;
import com.zonelab.wbd.core.api.WhiteboardRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryWhiteboardRepositoryTest {
    private WhiteboardRepository repository;
    @Before
    public void setUp() throws Exception {
        repository = MemoryServices.instance().getWhiteboardRepository();
        assertEquals(0, repository.size());
    }

    @Test
    public void test() throws Exception {
        Whiteboard testBoard;
        String boardName = "Test board";
        Id boardOwnerId = Id.create(16);
        {
            // Add board
            final Whiteboard board = Whiteboard.builder().setName(boardName).setOwnerId(boardOwnerId).build();
            assertSame(Id.NULL, board.getId());
            testBoard = repository.create(board);
            assertTrue(testBoard.getId().asLong() > 0);
            final Whiteboard stored = repository.get(testBoard.getId());
            assertSame(testBoard, stored);
            assertEquals(board.getName(), stored.getName());
            assertEquals(board.getOwnerId(), stored.getOwnerId());
            assertEquals(1, repository.size());
        }
        {
            // Add another board
            final Whiteboard board = Whiteboard.builder().setName("Alex's board").setOwnerId(boardOwnerId).build();
            assertSame(Id.NULL, board.getId());
            repository.create(board);
            assertEquals(2, repository.size());
        }
        {
            // delete unknown board
            final Whiteboard delete = repository.delete(Id.create(0));
            assertNull(delete);
        }
        {
            // deleted test board
            final Whiteboard deleted = repository.delete(testBoard.getId());
            assertSame(testBoard, deleted);
            assertEquals(1, repository.size());
            assertNull(repository.get(testBoard.getId()));
        }
    }
}