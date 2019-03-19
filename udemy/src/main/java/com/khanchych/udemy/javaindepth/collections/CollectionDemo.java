package com.khanchych.udemy.javaindepth.collections;

import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        Deque<Integer> llist = new ArrayDeque<>();
        Collection<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(3);
        list1.add(null);
        list1.remove(3);
        list1.remove(3);
        System.out.println("list1: " + list1);
    }
}
