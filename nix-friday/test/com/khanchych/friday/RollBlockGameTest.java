package com.khanchych.friday;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RollBlockGameTest {
    private RollBlockGame game;


    @Test
    public void shortestPathShouldBeFoundForFeasibleCase() {
        String actualPath;

        game = new RollBlockGame(5, 5);
        actualPath = game.getPath(new Position(0, 0), new Position(4, 4));
        assertEquals("Right -> Right -> Down -> Down", actualPath);

        actualPath = game.getPath(new Position(0, 0), new Position(0, 1));
        assertEquals("Right -> Down -> Left", actualPath);
    }

    @Test
    public void shouldNotExistPathIfFinishIsNotFeasible() {
        String actualPath;

        game = new RollBlockGame(5, 5);
        actualPath = game.getPath(new Position(0, 0), new Position(2, 2));
        assertEquals("", actualPath);
    }


}
