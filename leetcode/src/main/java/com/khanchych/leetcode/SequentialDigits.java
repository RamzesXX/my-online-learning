package com.khanchych.leetcode;

import java.util.LinkedList;
import java.util.List;

public class SequentialDigits {
    private static final int MAX_SEQUENTIAL = 123456789;
    private static final int[] POW_TEN = {1, 10, 100, 1000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000};

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new LinkedList<>();
        int lowLength = lengthOfNumber(low);
        int highLength = lengthOfNumber(high);

        for (int l = lowLength; l <= highLength; l++) {
            for(int s = 8; s >= l-1; s--) {
                int number = getSequentialDigits(s, l);
                if (low > number) {
                    continue;
                }
                if (high < number) {
                    break;
                }

                result.add(number);
            }
        }
        return result;
    }

    //cut of 'length' digits within MAX_SEQUENTIAL starting from 'startPos'
    private int getSequentialDigits(int startPos, int length) {
        if (startPos + 1 < length) {
            throw new RuntimeException();
        }
        return  (MAX_SEQUENTIAL % POW_TEN[startPos + 1]) / POW_TEN[startPos - length + 1];
    }

    private int lengthOfNumber(int number) {
        for(int i = 0; i < POW_TEN.length; i++) {
            if (number <= POW_TEN[i]) {
                return i;
            }
        }

        return POW_TEN.length;
    }
}
