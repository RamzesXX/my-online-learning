import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FastCollinearPointsTest {

    @Test
    public void test() {
        Point[] points = getPointsFromFile("files/grid4x4.txt");
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Point[] getPointsFromFile(String fileName) {
        Point[] points = new Point[0];
        int count;

        try (InputStream inputStream = FastCollinearPointsTest.class.getClassLoader().getResourceAsStream(fileName)) {
            Scanner scanner = new Scanner(inputStream);
            points = new Point[scanner.nextInt()];
            count = 0;
            while (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[count++] = new Point(x, y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }
}