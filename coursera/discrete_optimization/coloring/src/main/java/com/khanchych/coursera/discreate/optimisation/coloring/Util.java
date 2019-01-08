package com.khanchych.coursera.discreate.optimisation.coloring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static int getAmountOfColors(List<Integer> solution) {
        return 1 + Collections.max(solution);
    }

    public static String getSolutionString(List<Integer> solution) {
        return solution.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static ProblemInput getProblemFromFile(String fileName) throws IOException {
        int nodes;
        int edges;
        Map<Integer, Set<Integer>> items;


        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                Scanner scanner = new Scanner(bufferedReader)
        ) {
            nodes = scanner.nextInt();
            edges = scanner.nextInt();
            items = Stream.iterate(0, n -> n + 1)
                    .limit(nodes)
                    .collect(Collectors.toMap(key -> key, key -> new HashSet<>()));

            while (scanner.hasNext()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();

                items.get(from).add(to);
                items.get(to).add(from);
            }
        }

        return new ProblemInput(nodes, edges, items);
    }
}
