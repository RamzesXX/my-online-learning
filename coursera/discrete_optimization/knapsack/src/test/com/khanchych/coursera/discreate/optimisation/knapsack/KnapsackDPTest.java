package com.khanchych.coursera.discreate.optimisation.knapsack;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class KnapsackDPTest {
    private List<Item> items;
    private int capacity;

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
    }

    @Test
    public void solve() {
        KnapsackDP_1 knapsackDP = new KnapsackDP_1(items, capacity);

        System.out.println(knapsackDP.getSolution());
    }

    @Test
    public void getSolution() {
    }
}