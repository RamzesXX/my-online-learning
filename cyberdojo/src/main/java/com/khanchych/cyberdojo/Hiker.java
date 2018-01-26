package com.khanchych.cyberdojo;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hiker {

    public static String answer(Integer[] inputArray) {
        Comparator comparator = (Comparator<String>) (first, second) -> {
            int i = 0;

            if (first.equals(second)) {
                return 0;
            }

            while (i < Math.min(first.length(), second.length())) {
                if (first.charAt(i) < second.charAt(i)) {
                    return +1;
                }

                if (first.charAt(i) > second.charAt(i)) {
                    return -1;
                }

                i++;
            }

            if (i < first.length()) {
                return first.charAt(i) < first.charAt(0) ? +1 : -1;
            }

            return second.charAt(i) >= second.charAt(0) ? +1 : -1;
        };

        String inputList = (String) Stream.of(inputArray)
                .map(String::valueOf)
                .sorted(comparator)
                .collect(Collectors.joining(""));

        return inputList;
    }

    public static void main(String[] args) {
        System.out.println(
                answer(new Integer[]{50, 2, 1, 9, 36, 23, 3456, 4564, 2, 65, 73, 3, 9, 0, 6, 4, 2,
                        45345, 35435, 353, 534, 534535, 35353}));
    }

}