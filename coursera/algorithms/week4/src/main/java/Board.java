import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] board;
    private final int size;
    private final String stringRepesentation;
    private boolean isGoal;
    private int zeroPosX;
    private int zeroPosY;
    private int hammingPriority;
    private int manhattonPriority;


    public Board(int[][] blocks) {
        StringBuilder buffer;

        if (blocks == null) {
            throw new IllegalArgumentException();
        }

        size = blocks.length;
        hammingPriority = 0;
        manhattonPriority = 0;
        board = new int[size][size];
        buffer = new StringBuilder("" + dimension() + "\n");
        isGoal = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = blocks[i][j];

                if (board[i][j] == 0) {
                    zeroPosX = j;
                    zeroPosY = i;
                } else if (board[i][j] != i * size + j + 1) {
                    hammingPriority++;
                    manhattonPriority += Math.abs(i - (board[i][j] - 1) / size);
                    manhattonPriority += Math.abs(j - (board[i][j] - 1) % size);
                    isGoal = false;
                }
                buffer.append(" ").append(board[i][j]);
            }
            buffer.append("\n");
        }
        stringRepesentation = buffer.toString();
    }

    private static int[][] clone2dArray(int[][] blocks) {
        int[][] tmpBoard = new int[blocks.length][];

        for (int i = 0; i < blocks.length; i++) {
            tmpBoard[i] = blocks[i].clone();
        }

        return tmpBoard;
    }

    public int dimension() {
        return size;
    }

    public int hamming() {
        return hammingPriority;
    }

    public int manhattan() {
        return manhattonPriority;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public Board twin() {
        int tmp;
        int[][] tmpBlocks = clone2dArray(board);

        if (tmpBlocks[0][0] * tmpBlocks[0][1] > 0) {
            tmp = tmpBlocks[0][0];
            tmpBlocks[0][0] = tmpBlocks[0][1];
            tmpBlocks[0][1] = tmp;
        } else {
            tmp = tmpBlocks[1][0];
            tmpBlocks[1][0] = tmpBlocks[1][1];
            tmpBlocks[1][1] = tmp;
        }

        return new Board(tmpBlocks);
    }

    public boolean equals(Object otherObject) {
        Board other;

        if (otherObject == null) {
            return false;
        }

        if (!(otherObject.getClass().equals(Board.class))) {
            return false;
        }

        other = (Board) otherObject;

        if (size != other.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (board[i].length != other.board[i].length) {
                return false;
            }
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] != other.board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        int[][] tmpBlocks;
        List<Board> neighborList = new ArrayList<>();

        if (zeroPosX > 0) {
            tmpBlocks = clone2dArray(board);
            tmpBlocks[zeroPosY][zeroPosX] = tmpBlocks[zeroPosY][zeroPosX - 1];
            tmpBlocks[zeroPosY][zeroPosX - 1] = 0;
            neighborList.add(new Board(tmpBlocks));
        }
        if (zeroPosX < dimension() - 1) {
            tmpBlocks = clone2dArray(board);
            tmpBlocks[zeroPosY][zeroPosX] = tmpBlocks[zeroPosY][zeroPosX + 1];
            tmpBlocks[zeroPosY][zeroPosX + 1] = 0;
            neighborList.add(new Board(tmpBlocks));
        }
        if (zeroPosY > 0) {
            tmpBlocks = clone2dArray(board);
            tmpBlocks[zeroPosY][zeroPosX] = tmpBlocks[zeroPosY - 1][zeroPosX];
            tmpBlocks[zeroPosY - 1][zeroPosX] = 0;
            neighborList.add(new Board(tmpBlocks));
        }
        if (zeroPosY < dimension() - 1) {
            tmpBlocks = clone2dArray(board);
            tmpBlocks[zeroPosY][zeroPosX] = tmpBlocks[zeroPosY + 1][zeroPosX];
            tmpBlocks[zeroPosY + 1][zeroPosX] = 0;
            neighborList.add(new Board(tmpBlocks));
        }

        return neighborList;
    }

    public String toString() {
        return stringRepesentation;
    }

    public static void main(String[] args) {
        StdOut.println();
    }
}