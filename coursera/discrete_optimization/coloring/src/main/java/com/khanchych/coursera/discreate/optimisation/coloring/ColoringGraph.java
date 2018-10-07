package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColoringGraph implements Coloring {
    @Override
    public List<Integer> solve(ProblemInput input) {
        return solve(input.getItems());
    }

    @Override
    public List<Integer> solve(final Map<Integer, List<Integer>> items) {
        int maxColorIndex = 0;
        List<Integer> nodeColors = Stream.iterate(0, n -> maxColorIndex)
                .limit(items.size())
                .collect(Collectors.toList());
        List<Integer> orderedNodes = Stream.iterate(0, n -> n + 1)
                .limit(items.size())
                .sorted(Comparator.comparingInt(element -> items.get(element).size()))
                .collect(Collectors.toList());

        for(Integer node: orderedNodes) {

        }

        return nodeColors;
    }

}
