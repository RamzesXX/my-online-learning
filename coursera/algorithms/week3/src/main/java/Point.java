import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    public String toString() {
        return String.format("[%d, %d]", x, y);
    }

    public int compareTo(Point that) {
        if (Integer.compare(this.y, that.y) != 0) {
            return Integer.compare(this.y, that.y);
        }

        return Integer.compare(this.x, that.x);
    }

    public double slopeTo(Point that) {
        if (compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        if (Integer.compare(this.x, that.x) == 0) {
            return Double.POSITIVE_INFINITY;
        }

        if (Integer.compare(this.y, that.y) == 0) {
            return +0.0d;
        }


        return 1.0d * (that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder() {
        final Point zeroPoint = this;

        return new Comparator<Point>() {
            @Override
            public int compare(Point first, Point second) {
                int result = Double.compare(zeroPoint.slopeTo(first), zeroPoint.slopeTo(second));

                if (result != 0.0d) {
                    return result;
                }

                return first.compareTo(second);
            }
        };
    }
}