public class LineSegment {

    private Point p;
    private Point q;

    public LineSegment(Point p, Point q) {
        this.p = p;
        this.q = q;
    }

    public void draw() {
        p.drawTo(q);
    }

    public String toString() {
        return String.format("Segment from %s to %s", p.toString(), q.toString());
    }
}