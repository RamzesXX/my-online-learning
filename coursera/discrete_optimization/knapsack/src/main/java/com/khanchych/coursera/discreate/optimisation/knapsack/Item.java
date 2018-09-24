package com.khanchych.coursera.discreate.optimisation.knapsack;


public class Item {
    private int index;
    private int value;
    private int weight;

    public Item(int index, int value, int weight) {
        this.index = index;
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return '{' + "index: " + index + ", value: " + value + ", weight: " + weight + '}';
    }
}