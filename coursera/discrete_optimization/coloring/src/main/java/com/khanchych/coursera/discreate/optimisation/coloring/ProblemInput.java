package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.Map;
import java.util.Set;

public class ProblemInput {
    private int nodes;
    private int edges;
    private Map<Integer, Set<Integer>> items;

    public ProblemInput(int nodes, int edges, Map<Integer, Set<Integer>> items) {
        this.nodes = nodes;
        this.edges = edges;
        this.items = items;
    }

    public int getNodes() {
        return nodes;
    }

    public int getEdges() {
        return edges;
    }

    public Map<Integer, Set<Integer>> getItems() {
        return items;
    }
}
