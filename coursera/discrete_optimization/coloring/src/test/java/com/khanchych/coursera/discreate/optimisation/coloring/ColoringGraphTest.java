package com.khanchych.coursera.discreate.optimisation.coloring;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ColoringGraphTest {
    private Coloring coloring;

    @Before
    public void setUp() {
        coloring = new ColoringGraph();
    }

    @Test
    public void solveFromFileTest1() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_50_3");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 8);
    }

    @Test
    public void solveFromFileTest2() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_70_7");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 20);
    }

    @Test
    public void solveFromFileTest3() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_100_5");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 21);
    }

    @Test
    public void solveFromFileTest4() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_250_9");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 95);
    }

    @Test
    public void solveFromFileTest5() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_500_1");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 18);
    }

    @Test
    public void solveFromFileTest6() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/gc_1000_5");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 124);
    }

    @Test
    public void solveFromFileTestMy1() throws IOException {
        ProblemInput input = Util.getProblemFromFile("data/my_5");
        List<Integer> solution = coloring.solve(input);

        System.out.println(Util.getAmountOfColors(solution));
        System.out.println(Util.getSolutionString(solution));
        assertTrue(Util.getAmountOfColors(solution) <= 5);
    }

}

