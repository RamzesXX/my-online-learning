package com.khanchych.udemy.javaindepth;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SPEED = 10;

    private static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x &&
                    y == coord.y;
        }
    }

    private final int SIZE;
    private final int SPEED;
    private final int MAX_X_ALLOWED;
    private final int MAX_Y_ALLOWED;
    private final int MIN_X_ALLOWED;
    private final int MIN_Y_ALLOWED;
    private int speedCounter;
    private boolean isGameFinished;
    private Coord direction;
    private Deque<Coord> body;

    Snake() {
        this(DEFAULT_SIZE, MAX_SPEED);
    }

    Snake(int size, int speed) {
        this.SIZE = size;
        this.SPEED = speed;
        this.MIN_X_ALLOWED = 1;
        this.MIN_Y_ALLOWED = 1;
        this.MAX_X_ALLOWED = SIZE;
        this.MAX_Y_ALLOWED = SIZE;
        newGame();
    }

    public void newGame() {
        direction = new Coord(0, 1);
        speedCounter = SPEED;
        body = new LinkedList<>();
        body.add(new Coord(MIN_X_ALLOWED, MIN_Y_ALLOWED));
        body.add(new Coord(MIN_X_ALLOWED + 1, MIN_Y_ALLOWED));
        body.add(new Coord(MIN_X_ALLOWED + 2, MIN_Y_ALLOWED));
        isGameFinished = false;
    }

    public void changeDirection(Coord newDirection) {
        direction = newDirection;
    }

    public void tick() {
        if (--speedCounter <= 0 && !isGameFinished) {
            Coord currentHead = body.getFirst();
            Coord newHead = new Coord(currentHead.x + direction.x, currentHead.y + direction.y);

            body.pollLast();
            if (newHead.x < MIN_X_ALLOWED || newHead.x > MAX_X_ALLOWED ||
                    newHead.y < MIN_Y_ALLOWED || newHead.y > MAX_Y_ALLOWED) {
                isGameFinished = true;
                return;
            }
            if (body.contains(newHead)) {
                isGameFinished = true;
                return;
            }
            body.addFirst(newHead);
            speedCounter = SPEED;
        }
    }

    public String print() {
        StringBuilder field = new StringBuilder(SIZE * (SIZE - 1));
        for (int i = 0; i <= SIZE * (SIZE + 1); i++) {
            field.append(i % (1 + SIZE) == 0 ? "\n" : ".");
        }
        for (Coord bodyPart : body) {
            int pos = bodyPart.x + (bodyPart.y - 1) * (SIZE + 1);
            field.replace(pos, pos + 1, "*");
        }
        return field.toString();
    }

    public static void main(String[] args) {
        Snake snake = new Snake(10, 1);
        System.out.println(snake.print());
        snake.tick();
        System.out.println(snake.print());
        snake.tick();
        System.out.println(snake.print());
        snake.tick();
        System.out.println(snake.print());
    }
}
