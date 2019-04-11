package com.khanchych.udemy.javaindepth.collections;

import java.util.Arrays;
import java.util.List;

public class ArraysDemo {
    void test(String... a) {
        System.out.println(a.getClass());
    }
    public static void main(String[] args) {
        List<String> list = Arrays.asList("first", "second", "third");
    }
}
