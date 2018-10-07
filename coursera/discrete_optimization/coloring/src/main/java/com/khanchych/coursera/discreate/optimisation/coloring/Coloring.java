package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Coloring {
    List<Integer> solve(Map<Integer, Set<Integer>> items);
    List<Integer> solve(ProblemInput input);
}
