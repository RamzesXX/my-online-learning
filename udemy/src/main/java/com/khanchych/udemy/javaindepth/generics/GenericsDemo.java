package com.khanchych.udemy.javaindepth.generics;

import java.util.Arrays;
import java.util.List;

public class GenericsDemo {
    public static void main(String[] args) {
        Container<String> stringStore = new Store<>();
        Container<Integer> integerStore = new Store<>();
        Container<List<Integer>> listOfIntegerStore = new Store<>();
        listOfIntegerStore.set(Arrays.asList(1,2,3));


    }
}

interface Container<T> {
    T get();
    void set(T value);
}

class Store<T> implements Container<T> {
    private T value;
    static <S> S getS() {
        S s = null;

        return s;
    }
    public T get() {
        return value;
    }

    public void set(T value){
        this.value = value;
    }

}
