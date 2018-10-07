package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.List;
import java.util.Map;

public interface Coloring {
    List<Integer> solve(Map<Integer, List<Integer>> items);
    List<Integer> solve(ProblemInput input);
}
