package com.khanchych.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TargetIndicesAfterSortingArray {
    public List<Integer> targetIndices(int[] nums, int target) {
        int numberOfSmaller = 0;
        int numberOfGreater = 0;
        for(int num: nums) {
            if (num > target) {
                numberOfGreater++;
            } else
            if (num < target) {
                numberOfSmaller++;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i=numberOfSmaller;i<nums.length - numberOfGreater;i++) {
            list.add(i);
        }
        return list;
    }
}
