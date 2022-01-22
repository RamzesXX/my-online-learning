package com.khanchych.leetcode;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length -1;
        int maxVolume = 0;
        while (l<r) {
            maxVolume = Math.max(maxVolume, (r-l) * Math.min(height[l], height[r]));
            if (height[l] <  height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return maxVolume;
    }
}
