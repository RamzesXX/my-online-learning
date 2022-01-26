package com.khanchych.leetcode;

public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int highestLeft = height[left];
        int highestRight = height[right];
        int trappedWater = 0;
        while (left <= right) {
            if (height[left] < height[right]) {
                trappedWater += Math.max(0, highestLeft - height[left]);
                highestLeft = Math.max(highestLeft, height[left]);
                left++;
            } else {
                trappedWater += Math.max(0, highestRight - height[right]);
                highestRight = Math.max(highestRight, height[right]);
                right--;
            }
        }
        return trappedWater;
    }

}
