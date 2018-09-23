package com.khanchych.coursera.discreate.optimisation.knapsack;


public class Item {

    private int value;
    private int weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{" + "value=" + value + ", weight=" + weight + '}';
    }
}