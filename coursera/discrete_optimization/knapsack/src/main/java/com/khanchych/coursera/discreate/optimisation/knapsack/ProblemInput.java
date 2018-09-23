package com.khanchych.coursera.discreate.optimisation.knapsack;

import java.util.List;

public class ProblemInput {
    private int capacity;

    private List<Item> items;

    public ProblemInput(List<Item> items, int capacity) {
        this.capacity = capacity;
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }
}
