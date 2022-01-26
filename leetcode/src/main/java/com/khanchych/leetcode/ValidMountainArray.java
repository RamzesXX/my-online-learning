package com.khanchych.leetcode;

public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {
        int i = 0;

        while(i < arr.length - 1) {
            if (arr[i] == arr[i + 1]) {
                return false;
            }
            if (arr[i] > arr[i + 1]) {
                break;
            }
            i++;
        }
        if (i == 0 || i == arr.length - 1) {
            return false;
        }

        while (i < arr.length - 1) {
            if (arr[i] == arr[i + 1] || arr[i] < arr[i + 1]) {
                return false;
            }
            i++;
        }

        return true;
    }
}
