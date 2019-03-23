package com.khanchych.udemy.javaindepth.collections;

import java.util.*;

public class CollectionDemo {
    private static void comparatorsPlayground() {
        Comparator<String> comparator;

        comparator = Comparator.comparing(String::length, Integer::compareTo);
        comparator = comparator.thenComparing(String::compareTo);
    }

    private static void hashmapPlayground() {
        Map<String, String> map = new HashMap<>();
    }

    private static void immutableKeysPlayground() {
        List<Integer> list = new ArrayList<>();
        Map<List<Integer>, String> map = new HashMap<>();
        list.add(1);
        map.put(list, "1");
        System.out.println(list.hashCode());
        list.add(2);
        System.out.println(list.hashCode());
    }

    private static void linkedHashMapPlayground() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "1");
        System.out.println(map);
        map.remove(1);
    }

    private static void lruCachePlayground() {
        Map<Integer, String> map = new LruCache<>(2);
        System.out.println(map);
        map.put(1, "1");
        System.out.println(map);
        map.put(2, "2");
        System.out.println(map);
        map.put(3, "3");
        System.out.println(map);
    }

    public static void main(String[] args) {
//        arrayDequePlayground();
//        comparatorsPlayground();
//        immutableKeysPlayground();
//        linkedHashMapPlayground();
        lruCachePlayground();
    }

    private static void arrayDequePlayground() {
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

class LruCache<K, V> extends LinkedHashMap<K, V> {
    private int size;

    public LruCache(int size) {
        super(size, .75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size < size();
    }
}