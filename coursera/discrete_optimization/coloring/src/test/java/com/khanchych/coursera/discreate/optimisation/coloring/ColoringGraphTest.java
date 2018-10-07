package com.khanchych.coursera.discreate.optimisation.coloring;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ColoringGraphTest {
    private Coloring coloring;

    @Before
    public void setUp() {
        coloring = new ColoringGraph();
    }

    @Test
    public void solveFromFile() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_20_1");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
    }

}

