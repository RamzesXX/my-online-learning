package com.khanchych.coursera.discreate.optimisation.coloring;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ColoringTest {
    private Coloring coloring;

    @Before
    public void setUp() throws Exception {
        coloring = new ColoringGraph();
    }

    @Test
    @Ignore
    public void solve() {
    }

    @Test
    public void solveFromFile() throws IOException {
        ProblemInput input = Util.getProblemFromFile("resources/gc_4_1");

        System.out.println(coloring.solve(input));
    }

}

