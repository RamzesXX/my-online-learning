import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    private final int N = 3;
    private Board board;
    private Board solvedBoard;

    @Before
    public void setUp() {
        board = new Board(new int[][]{{0, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        solvedBoard = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
    }

    @Test
    public void dimension() throws Exception {
        assertEquals(N, board.dimension());
    }

    @Test
    public void hamming() throws Exception {
        assertEquals(solvedBoard.hamming(), 0);
        assertEquals(board.hamming(), 7);
        assertEquals(new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}}).hamming(), 5);
    }

    @Test
    public void manhattan() throws Exception {
        assertEquals(solvedBoard.manhattan(), 0);
        assertEquals(new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}}).manhattan(), 10);
        assertEquals(board.manhattan(), 20);
    }

    @Test
    public void isGoal() throws Exception {
        assertFalse(board.isGoal());
        assertTrue(solvedBoard.isGoal());
    }

    @Test
    public void twin() throws Exception {
        assertEquals(board.twin(), new Board(new int[][]{{0, 8, 7}, {5, 6, 4}, {3, 2, 1}}));
        assertEquals(solvedBoard.twin(), new Board(new int[][]{{2, 1, 3}, {4, 5, 6}, {7, 8, 0}}));
    }

    @Test
    public void equalsTest() throws Exception {
        assertTrue(board.equals(new Board(new int[][]{{0, 8, 7}, {6, 5, 4}, {3, 2, 1}})));
        assertFalse(board.equals(solvedBoard));
        assertFalse(board.equals(null));
        assertFalse(board.equals("String"));
        assertFalse(board.equals(new Board(new int[][]{{0, 8}, {6, 5}})));
    }

    @Test
    public void neighbors() throws Exception {
        List<Board> neighbors = (List) board.neighbors();

        assertEquals(neighbors.get(1), new Board(new int[][]{{6, 8, 7}, {0, 5, 4}, {3, 2, 1}}));
        assertEquals(neighbors.get(0), new Board(new int[][]{{8, 0, 7}, {6, 5, 4}, {3, 2, 1}}));
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("3\n 0 8 7\n 6 5 4\n 3 2 1\n", board.toString());
    }

}