import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double K = 1.96;
    private final int size;
    private final int trials;
    private final double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(
                    "PercolationStats: n and trials should be positive integer values greater than 0");
        }

        this.size = n;
        this.trials = trials;
        this.results = new double[trials];
        calc();
    }

    private double doTry(final int n) {
        Percolation percolation = new Percolation(n);

        while (!percolation.percolates()) {
            percolation.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
        }

        return 1.0 * percolation.numberOfOpenSites() / (n * n);
    }

    private void calc() {
        for (int i = 0; i < trials; i++) {
            results[i] = doTry(size);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - K * stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + K * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int size = 20;
        int trials = 20;

        if (args.length >= 2) {
            size = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }
        PercolationStats stats = new PercolationStats(size, trials);

        System.out.println("mean  = " + stats.mean());
        System.out.println("stdev = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
