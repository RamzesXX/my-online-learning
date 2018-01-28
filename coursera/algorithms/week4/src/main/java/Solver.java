import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class Solver {
    private static class SearchNode implements Comparable<SearchNode> {
        private final Board state;
        private final SearchNode prevState;
        private final int moves;

        public SearchNode(Board state, SearchNode prevState, int moves) {
            this.state = state;
            this.prevState = prevState;
            this.moves = moves;
        }

        private int getPriority() {
            return state.manhattan() + moves;
        }

        @Override
        public int compareTo(SearchNode other) {
            return getPriority() - other.getPriority();
        }
    }

    private MinPQ<SearchNode> boardTree;
    private SearchNode solutionNode;
    private Board originBoard;


    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        boardTree = new MinPQ<>();
        originBoard = initial;
        boardTree.insert(new SearchNode(originBoard, null, 0));
        boardTree.insert(new SearchNode(originBoard.twin(), null, 0));
        solutionNode = solve();
    }

    private SearchNode solve() {
        SearchNode node = boardTree.delMin();
        SearchNode parentNode;

        while (!node.state.isGoal()) {
            for (Board state : node.state.neighbors()) {
                if (node.prevState == null || !state.equals(node.prevState.state)) {
                    boardTree.insert(new SearchNode(state, node, node.moves + 1));
                }
            }

            node = boardTree.delMin();
        }
        parentNode = node;
        while (parentNode.prevState != null) {
            parentNode = parentNode.prevState;
        }
        if (parentNode.state.equals(originBoard)) {
            return node;
        }

        return null;
    }

    public boolean isSolvable() {
        return solutionNode != null;
    }

    public int moves() {
        if (isSolvable()) {
            return solutionNode.moves;
        }

        return -1;
    }

    public Iterable<Board> solution() {
        LinkedList<Board> solution = null;

        if (isSolvable()) {
            solution = new LinkedList<>();
            for (SearchNode node = solutionNode; node != null; node = node.prevState) {
                solution.addFirst(node.state);
            }
        }

        return solution;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}