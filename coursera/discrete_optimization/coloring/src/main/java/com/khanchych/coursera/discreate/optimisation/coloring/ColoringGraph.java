package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColoringGraph implements Coloring {
    @Override
    public List<Integer> solve(ProblemInput input) {
        return solve(input.getItems());
    }

    @Override
    public List<Integer> solve(final Map<Integer, Set<Integer>> items) {
        Set<Integer> usedColors = new HashSet<>();
        Set<Integer> usedByAdjacentNodesColors = new HashSet<>();

        List<Integer> nodesColors = Stream.iterate(0, n -> 0)
                .limit(items.size())
                .collect(Collectors.toList());
        List<Integer> orderedNodes = Stream.iterate(0, n -> n + 1)
                .limit(items.size())
                .sorted(Comparator.comparingInt(element -> items.get(element).size()))
                .collect(Collectors.toList());

        usedColors.add(0);
        for (Integer node : orderedNodes) {
            usedByAdjacentNodesColors.clear();
            for (Integer adjacentNode : items.get(node)) {
                usedByAdjacentNodesColors.add(nodesColors.get(adjacentNode));
            }
            if (usedByAdjacentNodesColors.contains(nodesColors.get(node))) {
                Set<Integer> availableColors = new HashSet<>(usedColors);
                int nodeColor;

                availableColors.removeAll(usedByAdjacentNodesColors);
                if (availableColors.isEmpty()) {
                    nodeColor = usedColors.size();
                    usedColors.add(nodeColor);
                } else {
                    nodeColor = availableColors.iterator().next();
                }
                nodesColors.set(node, nodeColor);
            }
            System.out.print("\"" + (usedColors.size()) + " \\n");
            System.out.print(Util.getSolutionString(nodesColors));
            System.out.println("\",");
        }

        return nodesColors;
    }

}
