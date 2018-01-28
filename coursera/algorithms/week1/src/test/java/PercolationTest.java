import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PercolationTest {

    @Test
    public void fileTest() {
        PercolationIterator iterator = new PercolationIterator("files/heart25.txt");
        while (iterator.hasNextStep()) {
            iterator.doNextStep();
//            if (iterator.currentStep == 231) {
//                break;
//            }
        }

        iterator.draw();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class PercolationIterator {
        class Pair {
            final private int row;
            final private int col;

            public Pair(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        private Percolation percolation;
        private List<Pair> steps;
        private int currentStep;
        private int size;

        PercolationIterator(String fileName) {
            currentStep = 0;
            try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName);
                 Scanner scanner = new Scanner(stream)) {
                int row, col;

                size = scanner.nextInt();
                percolation = new Percolation(size);
                steps = new ArrayList<>();
                while (scanner.hasNextInt()) {
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                    steps.add(new Pair(row, col));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        public void doNextStep() {
            if (!hasNextStep()) {
                throw new RuntimeException("There is no more steps");
            }
            Pair pair = steps.get(currentStep++);

            percolation.open(pair.row, pair.col);
        }

        public boolean hasNextStep() {
            return steps.size() > currentStep;
        }

        public Percolation getPercolation() {
            return percolation;
        }

        public void draw() {
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setXscale(-0.05 * size, 1.05 * size);
            StdDraw.setYscale(-0.05 * size, 1.05 * size);   // leave a border to write text
            StdDraw.filledSquare(size / 2.0, size / 2.0, size / 2.0);

            // draw n-by-n grid
            int opened = 0;
            for (int row = 1; row <= size; row++) {
                for (int col = 1; col <= size; col++) {
                    if (percolation.isFull(row, col)) {
                        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                        opened++;
                    } else if (percolation.isOpen(row, col)) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        opened++;
                    } else
                        StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledSquare(col - 0.5, size - row + 0.5, 0.45);
                }
            }

            // write status text
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(0.25 * size, -0.025 * size, opened + " open sites");
            if (percolation.percolates())
                StdDraw.text(0.75 * size, -0.025 * size, "percolates");
            else StdDraw.text(0.75 * size, -0.025 * size, "does not percolate");

        }
    }
}
