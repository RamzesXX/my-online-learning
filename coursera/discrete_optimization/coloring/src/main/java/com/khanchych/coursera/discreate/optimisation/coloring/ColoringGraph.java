package com.khanchych.coursera.discreate.optimisation.coloring;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColoringGraph implements Coloring {
    private static final Integer WITHOUT_COLOR = -1;

    private Map<Integer, Set<Integer>> items;

    @Override
    public List<Integer> solve(ProblemInput input) {
        return solve(input.getItems());
    }

    @Override
    public List<Integer> solve(final Map<Integer, Set<Integer>> items) {
        Set<Integer> usedColors = new HashSet<>();
        Set<Integer> usedByAdjacentNodesColors = new HashSet<>();
        List<Integer> orderedNodes;
        List<Integer> nodesColors = Stream.iterate(WITHOUT_COLOR, n -> WITHOUT_COLOR)
                .limit(items.size())
                .collect(Collectors.toList());

        this.items = items;
        orderedNodes = orderNodesByDegree();
        usedColors.add(0);
        for (Integer node : orderedNodes) {
            int nodeColor;
            Set<Integer> availableColors = new HashSet<>(usedColors);
            usedByAdjacentNodesColors.clear();
            for (Integer adjacentNode : items.get(node)) {
                if (!WITHOUT_COLOR.equals(nodesColors.get(adjacentNode))) {
                    usedByAdjacentNodesColors.add(nodesColors.get(adjacentNode));
                }
            }
            availableColors.removeAll(usedByAdjacentNodesColors);
            if (availableColors.isEmpty()) {
                nodeColor = usedColors.size();
                usedColors.add(nodeColor);
            } else {
                nodeColor = Collections.min(availableColors);
            }

            nodesColors.set(node, nodeColor);

//            System.out.print("\"" + (usedColors.size()) + " \\n");
//            System.out.print(Util.getSolutionString(nodesColors));
//            System.out.println("\",");
        }

        return nodesColors;
    }

    private List<Integer> orderNodesByDegree() {
        return Stream.iterate(0, n -> n + 1)
                .limit(items.size())
                .sorted(Comparator.comparingInt(element -> -items.get(element).size()))
                .collect(Collectors.toList());
    }

    private List<Integer> orderNodesByBFS() {
        List<Integer> orderedItems = new ArrayList<>();
        Set<Integer> unprocessed = new HashSet<>(items.keySet());
        while (!unprocessed.isEmpty()) {
            int element = unprocessed.iterator().next();
            process(element, unprocessed, orderedItems);
        }

        return orderedItems;
    }

    private void process(int startElement, Set<Integer> unprocessed, List<Integer> orderedItems) {
        Queue<Integer> toProcess = new LinkedList<>();

        toProcess.add(startElement);
        while (!toProcess.isEmpty()) {
            int element = toProcess.remove();
            if (unprocessed.contains(element)) {
                List<Integer> children = new ArrayList<>(items.get(element));
                children.sort(Comparator.comparingInt(item -> -items.get(item).size()));
                toProcess.addAll(children);
                unprocessed.remove(element);
                orderedItems.add(element);
            }
        }
    }
}
