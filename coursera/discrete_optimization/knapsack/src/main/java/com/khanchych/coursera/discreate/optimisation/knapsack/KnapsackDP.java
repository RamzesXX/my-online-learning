package com.khanchych.coursera.discreate.optimisation.knapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is solver task about knapsack using dynamic programming
 */

public class KnapsackDP implements Knapsack {

    @Override
    public List<Item> solve(ProblemInput input) {
        return solve(input.getItems(), input.getCapacity());
    }

    @Override
    public List<Item> solve(List<Item> items, int capacity) {
        Table<WorkingTableItem> workingTable = new Table<>();

        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            Item item = items.get(itemIndex);
            // if we have capacity to take item with index itemIndex then we do
            // otherwise we should make decision
            // we check if value of full capacity without current item greater than
            // value of capacity - item.weight  + item.value

            for (int k = 0; k <= capacity; k++) {
                WorkingTableItem prevTableItem =
                    itemIndex > 0 ? workingTable.getItem(itemIndex - 1, k) : new WorkingTableItem();
                WorkingTableItem tableItem = new WorkingTableItem();

                if (prevTableItem.weight + item.getWeight() <= k) {
                    tableItem.value = item.getValue() + prevTableItem.value;
                    tableItem.weight = item.getWeight() + prevTableItem.weight;
                } else {
                    WorkingTableItem itemAux = workingTable.getItem(itemIndex - 1, k - item.getWeight());
                    if (itemAux != null && (itemAux.value + item.getValue() > prevTableItem.value)) {
                        tableItem.value = item.getValue() + itemAux.value;
                        tableItem.weight = item.getWeight() + itemAux.weight;
                    } else {
                        tableItem = prevTableItem;
                    }
                }

                workingTable.setItem(itemIndex, k, tableItem);
            }
        }

        return getSolution(workingTable, items, capacity);
    }

    private List<Item> getSolution(Table<WorkingTableItem> workingTable, List<Item> items, int capacity) {
        List<Item> solution = new ArrayList<>();
        int k = workingTable.getItem(items.size() - 1, capacity).weight;
        for (int i = items.size() - 1; i >= 0; i--) {
            int prevValue = i > 0 ? workingTable.getItem(i - 1, k).value : 0;
            int curValue = workingTable.getItem(i, k).value;

            if (prevValue < curValue) {
                k -= items.get(i).getWeight();
                solution.add(items.get(i));
            }
            if (k <= 0) {
                return solution;
            }
        }

        return solution;
    }

    private static class Table<T> {

        private Map<String, T> workingTable;

        Table() {
            this.workingTable = new HashMap<>();
        }

        T getItem(int index, int capacity) {
            return workingTable.get(index + "_" + capacity);
        }

        void setItem(int index, int capacity, T tableItem) {
            workingTable.put(index + "_" + capacity, tableItem);
        }
    }

    private static class WorkingTableItem {

        private int value;
        private int weight;

        WorkingTableItem() {
        }

        WorkingTableItem(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}
