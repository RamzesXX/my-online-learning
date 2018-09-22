package com.khanchych.coursera.discreate.optimisation.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is solver task about knapsack using dynamic programming
 * but the one is saving memory by using array for last table's row
 */

public class KnapsackDP_1 {

    private List<TableItem> tableRow;
    private List<Item> items;
    private int capacity;

    public KnapsackDP_1(List<Item> items, int capacity) {
        this.items = items;
        this.capacity = capacity;
        this.tableRow = Stream.iterate(1L, n -> n)
                                .map((element) -> new TableItem())
                                .limit(capacity + 1)
                                .collect(Collectors.toList());
        solve();
    }

    private TableItem updateTmpItem(Item item, int itemIndex, TableItem tmpItem, TableItem tableItem) {
        tmpItem.value = tableItem.value + item.getValue();
        tmpItem.weight = tableItem.weight + item.getWeight();
        tmpItem.itemIndexes.clear();
        tmpItem.itemIndexes.addAll(tableItem.itemIndexes);
        tmpItem.itemIndexes.add(itemIndex);

        return tmpItem;
    }

    protected void solve() {
        //We use tmp row for saving intermediate values
        List<TableItem> tmpRow = Stream.iterate(1L, n -> n)
            .map((element) -> new TableItem())
            .limit(capacity + 1)
            .collect(Collectors.toList());

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
                    updateTmpItem(item, itemIndex, tmpItem, itemAux);
                } else {
                    itemAux = tableRow.get(k - item.getWeight());
                    if (itemAux.value + item.getValue() > tableItem.value) {
                        updateTmpItem(item, itemIndex, tmpItem, itemAux);
                    }
                }
            }

            for (int k = item.getWeight(); k <= capacity; k++) {
                TableItem tableItem = tableRow.get(k);
                TableItem tmpItem = tmpRow.get(k);

                tableItem.value = tmpItem.value;
                tableItem.weight = tmpItem.weight;
                tableItem.itemIndexes.clear();
                tableItem.itemIndexes.addAll(tmpItem.itemIndexes);
            }
        }
    }

    public List<Integer> getSolution() {
        return tableRow.get(capacity).itemIndexes;
    }

    private static class TableItem {

        private int value;
        private int weight;
        private List<Integer> itemIndexes;

        TableItem() {
            this.itemIndexes = new ArrayList<>();
        }

        TableItem(int value, int weight) {
            this();
            this.value = value;
            this.weight = weight;
        }
    }
}
