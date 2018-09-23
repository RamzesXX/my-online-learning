package com.khanchych.coursera.discreate.optimisation.knapsack;

import java.util.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class KnapsackDPTest {
    private List<Item> items;
    private int capacity;
    private Knapsack knapsack;

    @Before
    public void setUp() throws Exception {
        items = Arrays.asList(
            new Item(1, 2),
            new Item(1, 2),
            new Item(1, 2),
            new Item(10, 5),
            new Item(10, 5),
            new Item(13, 8),
            new Item(7, 3)
        );
        capacity = 10;
        knapsack = new KnapsackDP_1();
    }

    @Test
    @Ignore
    public void solve() {
        System.out.println(knapsack.solve(items, capacity));
    }

    @Test
    public void solveFromFile() {
        ProblemInput input = getProblemFromFile("ks_19_0");

        System.out.println(knapsack.solve(input));
    }

    @Test
    public void getSolution() {
    }

    private ProblemInput getProblemFromFile(String fileName){
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        int capacity;
        List<Item> items = new ArrayList<>();

        scanner.nextInt();
        capacity = scanner.nextInt();

        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            int weight = scanner.nextInt();

            items.add(new Item(value, weight));
        }

        return new ProblemInput(items, capacity);
    }
}

