import java.util.Arrays;

public class BruteCollinearPoints {

    private final Point[] points;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array should not be null");
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Point should not be null");
            }
        }

        this.points = points;
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Points should be distinct");
            }
        }

        processPoints();
    }


    private LineSegment[] extend(LineSegment[] lineSegments, int size, int count) {
        LineSegment[] tmp = new LineSegment[size];
        System.arraycopy(lineSegments, 0, tmp, 0, count);

        return tmp;
    }

    private void processPoints() {
        LineSegment[] lineSegments = new LineSegment[1];
        int segmentsCount = 0;

        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r])
                                && points[p].slopeTo(points[s]) == points[p].slopeTo(points[r])) {
                            if (lineSegments.length == segmentsCount) {
                                lineSegments = extend(lineSegments, 2 * segmentsCount + 1,
                                        segmentsCount);
                            }
                            lineSegments[segmentsCount++] = new LineSegment(points[p], points[s]);
                        }
                    }
                }
            }
        }
        segments = new LineSegment[segmentsCount];
        System.arraycopy(lineSegments, 0, segments, 0, segmentsCount);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}