package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.List;
import java.util.Map;

public class ProblemInput {
    private int nodes;
    private int edges;
    private Map<Integer, List<Integer>> items;

    public ProblemInput(int nodes, int edges, Map<Integer, List<Integer>> items) {
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

    public Map<Integer, List<Integer>> getItems() {
        return items;
    }
}
