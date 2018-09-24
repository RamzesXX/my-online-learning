package com.khanchych.coursera.discreate.optimisation.knapsack.dp;

import com.khanchych.coursera.discreate.optimisation.knapsack.Item;
import com.khanchych.coursera.discreate.optimisation.knapsack.Knapsack;
import com.khanchych.coursera.discreate.optimisation.knapsack.ProblemInput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is solver task about knapsack using dynamic programming
 * but the one is saving memory by using array for last table's row
 */

public class KnapsackDP_1 implements Knapsack {
    private List<Item> items;
    private int capacity;

    @Override
    public List<Item> solve(ProblemInput input) {
        return solve(input.getItems(), input.getCapacity());
    }

    @Override
    public List<Item> solve(List<Item> items, int capacity) {
        List<TableItem> tableRow = Stream.iterate(new TableItem(), (element) -> new TableItem())
            .limit(capacity + 1)
            .collect(Collectors.toList());

        //We use tmp row for saving intermediate values
        List<TableItem> tmpRow = Stream.iterate(new TableItem(), (element) -> new TableItem())
            .limit(capacity + 1)
            .collect(Collectors.toList());

        this.items = items;
        this.capacity = capacity;
        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            Item item = items.get(itemIndex);
            // if we have capacity to take item with index itemIndex then we do
            // otherwise we should make decision
            // we check if value of full capacity without current item greater than
            // value of capacity - item.weight  + item.value

            for (int k = item.getWeight(); k <= capacity; k++) {
                TableItem itemAux;
                TableItem tableItem = itemAux = tableRow.get(k);
                TableItem tmpItem = tmpRow.get(k);

                if (tableItem.weight + item.getWeight() <= k) {
                    updateTmpItem(item, tmpItem, itemAux);
                } else {
                    itemAux = tableRow.get(k - item.getWeight());
                    if (itemAux.value + item.getValue() > tableItem.value) {
                        updateTmpItem(item, tmpItem, itemAux);
                    }
                }
            }

            for (int k = item.getWeight(); k <= capacity; k++) {
                TableItem tableItem = tableRow.get(k);
                TableItem tmpItem = tmpRow.get(k);

                tableItem.value = tmpItem.value;
                tableItem.weight = tmpItem.weight;
                tableItem.items.clear();
                tableItem.items.addAll(tmpItem.items);
            }
        }

        return tableRow.get(capacity).items;
    }

    private TableItem updateTmpItem(Item item, TableItem tmpItem, TableItem tableItem) {
        tmpItem.value = tableItem.value + item.getValue();
        tmpItem.weight = tableItem.weight + item.getWeight();
        tmpItem.items.clear();
        tmpItem.items.addAll(tableItem.items);
        tmpItem.items.add(item);

        return tmpItem;
    }

    private static class TableItem {

        private int value;
        private int weight;
        private List<Item> items;

        TableItem() {
            this.items = new ArrayList<>();
        }

        TableItem(int value, int weight) {
            this();
            this.value = value;
            this.weight = weight;
        }
    }
}
