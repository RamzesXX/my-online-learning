package com.khanchych.coursera.discreate.optimisation.coloring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Util {
    public static ProblemInput getProblemFromFile(String fileName) throws IOException {
        int nodes;
        int edges;
        Map<Integer, List<Integer>> items = new HashMap<>();

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                Scanner scanner = new Scanner(bufferedReader)
        ) {
            nodes = scanner.nextInt();
            edges = scanner.nextInt();

            while (scanner.hasNext()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();

                addEdge(items, from, to);
                addEdge(items, to, from);
            }
        }

        return new ProblemInput(nodes, edges, items);
    }

    private static void addEdge(Map<Integer, List<Integer>> items, int from, int to) {
        items.compute(from, (key, oldValue) -> {
            if (oldValue == null) {
                oldValue = new ArrayList<>();
            }

            oldValue.add(to);

            return oldValue;
        });
    }
}
