import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private Set<Point2D> tree;

    public PointSET() {
        tree = new TreeSet<>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        tree.add(point);
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        return tree.contains(point);
    }

    public void draw() {
        for (Point2D point : tree) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Set<Point2D> points = new TreeSet<>();

        for (Point2D point : tree) {
            if (rect.contains(point)) {
                points.add(point);
            }
        }

        return points;
    }

    public Point2D nearest(Point2D originPoint) {
        double minDistance = Double.POSITIVE_INFINITY;
        Point2D pointWithMinDistance = null;

        for (Point2D point : tree) {
            double distance = originPoint.distanceSquaredTo(point);
            if (distance < minDistance) {
                minDistance = distance;
                pointWithMinDistance = point;
            }
        }

        return pointWithMinDistance;
    }

    public static void main(String[] args) {
        PointSET brute = new PointSET();
        In in = new In("kdtree/input10k.txt");

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0.0D, 1.0D);
        StdDraw.setYscale(0.0D, 1.0D);

        StdDraw.enableDoubleBuffering();

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }

        brute.draw();
        StdDraw.show();
    }
}