import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int topVirtualSite;
    private final int bottomVirtualSite;
    private final int size;
    private final boolean[][] matrix;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    private int openSitesAmount;


    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Percolation: n should be positive integer");
        }

        size = n;
        topVirtualSite = 0;
        bottomVirtualSite = n * n + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 0; i < size; i++) {
            weightedQuickUnionUF.union(topVirtualSite, convertRCtoPQ(0, i));
            weightedQuickUnionUF.union(bottomVirtualSite, convertRCtoPQ(size - 1, i));
        }
        matrix = new boolean[n][n];
        openSitesAmount = 0;
    }

    //Convert row and col into P/Q
    private int convertRCtoPQ(int row, int col) {
        return (1 + size * row + col);
    }


    public void open(final int row, final int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("open: row and col should be" +
                    " integer values from range (0;size]");
        }
        openInt(row - 1, col - 1);
    }

    private void openInt(final int row, final int col) {
        int siteIndex = convertRCtoPQ(row, col);

        if (!isOpenInt(row, col)) {
            matrix[row][col] = true;
            openSitesAmount++;

            if (row > 0 && isOpenInt(row - 1, col)) {
                weightedQuickUnionUF.union(convertRCtoPQ(row - 1, col), siteIndex);
            }
            if (row < size - 1 && isOpenInt(row + 1, col)) {
                weightedQuickUnionUF.union(convertRCtoPQ(row + 1, col), siteIndex);
            }
            if (col > 0 && isOpenInt(row, col - 1)) {
                weightedQuickUnionUF.union(convertRCtoPQ(row, col - 1), siteIndex);
            }
            if (col < size - 1 && isOpenInt(row, col + 1)) {
                weightedQuickUnionUF.union(convertRCtoPQ(row, col + 1), siteIndex);
            }
        }
    }

    /**
     * Check if site (row, col) is open
     */
    public boolean isOpen(final int row, final int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("isOpen: row and col should be" +
                    " integer values from range (0;size]");
        }

        return isOpenInt(row - 1, col - 1);
    }

    private boolean isOpenInt(final int row, final int col) {
        return matrix[row][col];
    }

    /**
     * Check if site (row, col) is full
     */
    public boolean isFull(final int row, final int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("isFull: row and col should be" +
                    " integer values from range (0;size]");
        }

        return isOpenInt(row - 1, col - 1) &&
                isFullInt(row - 1, col - 1);
    }

    private boolean isFullInt(final int row, final int col) {
        int p = convertRCtoPQ(row, col);

        return weightedQuickUnionUF.connected(p, topVirtualSite);
    }

    /**
     * Number of open sites
     */
    public int numberOfOpenSites() {
        return openSitesAmount;
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        if (size == 1) {
            return isOpen(1, 1);
        }
        return weightedQuickUnionUF.connected(bottomVirtualSite, topVirtualSite);
    }

    //No need
    public static void main(String[] args) {
    }

}
