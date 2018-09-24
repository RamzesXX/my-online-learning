package com.khanchych.coursera.discreate.optimisation.knapsack.bb;

import com.khanchych.coursera.discreate.optimisation.knapsack.Item;
import com.khanchych.coursera.discreate.optimisation.knapsack.Knapsack;
import com.khanchych.coursera.discreate.optimisation.knapsack.ProblemInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is solver task about knapsack using branch and bound
 */

public class KnapsackBB implements Knapsack {

    private List<Item> items;
    private List<Item> bestSolution;
    private int bestValue;
    private int capacity;

    @Override
    public List<Item> solve(ProblemInput input) {
        return solve(input.getItems(), input.getCapacity());
    }

    @Override
    public List<Item> solve(List<Item> originItems, int capacity) {
        List<Boolean> itemAvailability;
        int estimation = 0;

        this.items = new ArrayList<>(originItems);
        itemAvailability = Stream.iterate(true, n -> n)
                .limit(items.size())
                .collect(Collectors.toList());

        this.capacity = capacity;

        items.sort(Comparator.comparingDouble(item -> -1.0 * item.getValue() / item.getWeight()));
        bestSolution = new ArrayList<>();
        bestValue = 0;
        calcBestValue(0, itemAvailability, new NodeData(0, capacity));

        return bestSolution;
    }

    private void calcBestValue(int itemIndex, List<Boolean> itemAvailability, NodeData data) {
        if (itemIndex >= items.size() || calcEstimation(itemAvailability) < bestValue) {
            return;
        }

        Item item = items.get(itemIndex);

        if (data.capacity >= item.getWeight()) {
            if (data.value + item.getValue() > bestValue) {
                bestSolution.clear();
                bestValue = data.value + item.getValue();
                for (int i = 0; i <= itemIndex; i++) {
                    if (itemAvailability.get(i)) {
                        bestSolution.add(items.get(i));
                    }
                }
            }

            calcBestValue(itemIndex + 1, itemAvailability,
                    new NodeData(data.value + item.getValue(), data.capacity - item.getWeight()));
        }

        itemAvailability.set(itemIndex, false);
        calcBestValue(itemIndex + 1, itemAvailability, new NodeData(data.value, data.capacity));
        itemAvailability.set(itemIndex, true);
    }

    private int calcEstimation(List<Boolean> itemAvailability) {
        int accumulatedWeight = 0;
        int accumulatedValue = 0;

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (!itemAvailability.get(i)) {
                continue;
            }

            if (accumulatedWeight + item.getWeight() < capacity) {
                accumulatedValue += item.getValue();
                accumulatedWeight += item.getWeight();
            } else {
                float k = 1.0f * (capacity - accumulatedWeight) / item.getWeight();

                accumulatedValue += k * item.getValue();
                accumulatedWeight += k * item.getWeight();
                break;
            }
        }

        return accumulatedValue;
    }

    private static class NodeData {
        int value;
        int capacity;

        public NodeData(int value, int capacity) {
            this.value = value;
            this.capacity = capacity;
        }

        public int getValue() {
            return value;
        }

        public int getCapacity() {
            return capacity;
        }
    }

}
