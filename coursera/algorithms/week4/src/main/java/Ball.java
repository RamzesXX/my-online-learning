import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    private double x, y; // position
    private double vx, vy; // velocity
    private final double radius; // radius

    public Ball(double x, double y, double vx, double vy, double radius) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public Ball() {
        this(0.5, 0.5, 0.0, 0.0, 0.0001);
    }

    public void move(double dt) {
        if ((x + vx * dt < radius) || (x + vx * dt > 1.0 - radius)) {
            vx = -vx;
        }
        if ((y + vy * dt < radius) || (y + vy * dt > 1.0 - radius)) {
            vy = -vy;
        }
        x = x + vx * dt;
        y = y + vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(x, y, radius);
    }
}
