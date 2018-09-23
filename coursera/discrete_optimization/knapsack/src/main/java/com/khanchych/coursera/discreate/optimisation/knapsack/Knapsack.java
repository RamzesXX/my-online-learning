package com.khanchych.coursera.discreate.optimisation.knapsack;

import java.util.List;

public interface Knapsack {
    List<Item> solve(List<Item> items, int capacity);
    List<Item> solve(ProblemInput input);
}
