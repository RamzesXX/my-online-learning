package com.khanchych.coursera.discreate.optimisation.knapsack;

import com.khanchych.coursera.discreate.optimisation.knapsack.bb.KnapsackBB;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapsackTest {
    private List<Item> items;
    private int capacity;
    private Knapsack knapsack;

    @Before
    public void setUp() throws Exception {
        items = Arrays.asList(
                new Item(0, 1, 2),
                new Item(1, 1, 2),
                new Item(2, 1, 2),
                new Item(3, 10, 5),
                new Item(4, 10, 5),
                new Item(5, 13, 8),
                new Item(6, 7, 3)
        );
        capacity = 10;
        knapsack = new KnapsackBB();
    }

    @Test
    @Ignore
    public void solve() {
        System.out.println(knapsack.solve(items, capacity));
    }

    @Test
    public void solveFromFile() {
        ProblemInput input = getProblemFromFile("tmp.data");

        System.out.println(knapsack.solve(input));
    }


    private ProblemInput getProblemFromFile(String fileName) {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        int capacity;
        int index = 0;
        List<Item> items = new ArrayList<>();

        scanner.nextInt();
        capacity = scanner.nextInt();

        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            int weight = scanner.nextInt();

            items.add(new Item(index++, value, weight));
        }

        return new ProblemInput(items, capacity);
    }
}

