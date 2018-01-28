import edu.princeton.cs.algs4.StdDraw;

public class BouncingBalls {
    public static void main(String[] args) {
//        int N = Integer.parseInt(args[0]);
        int n = 100;
        Ball[] balls = new Ball[n];
        for (int i = 0; i < n; i++)
            balls[i] = new Ball(0.5, 0.5,  Math.sin(i/Math.PI)/20,  Math.cos(i/Math.PI)/20,  0.01);
        while (true) {
            StdDraw.clear();
            for (int i = 0; i < n; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}
