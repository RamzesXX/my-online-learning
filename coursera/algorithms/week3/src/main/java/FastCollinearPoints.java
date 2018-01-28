import java.util.Arrays;

public class FastCollinearPoints {

    private final Point[] points;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
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

        for (int p = 0; p < points.length - 4; p++) {
            Point point = points[p];
            int start = 0;
            double slop;

            Point[] workPoints = new Point[points.length - p - 1];
            System.arraycopy(points, p + 1, workPoints, 0, workPoints.length);
            Arrays.sort(workPoints, point.slopeOrder());
            slop = point.slopeTo(workPoints[0]);
            for (int q = 1; q < workPoints.length; q++) {
                double slop2 = point.slopeTo(workPoints[q]);
                if (slop != slop2) {
                    if (q - start > 2) {
                        if (lineSegments.length == segmentsCount) {
                            lineSegments = extend(lineSegments, 2 * segmentsCount + 1,
                                    segmentsCount);
                        }
                        lineSegments[segmentsCount++] = new LineSegment(point, workPoints[q - 1]);
                    }
                    start = q;
                    slop = point.slopeTo(workPoints[q]);
                }
            }
            if (workPoints.length - start > 2) {
                if (lineSegments.length == segmentsCount) {
                    lineSegments = extend(lineSegments, segmentsCount + 1, segmentsCount);
                }
                lineSegments[segmentsCount++] = new LineSegment(point,
                        workPoints[workPoints.length - 1]);
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
