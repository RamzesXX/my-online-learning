package com.khanchych.leetcode;

import java.util.Arrays;

/**
 * 11. Container With Most Water
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */
public class Task11 {

    public int[] solve(int[] dots) {
        int left = 0;
        int right = dots.length - 1;
        int v = Math.min(dots[left], dots[right]) * (right - left);
        int solutionLeft = left;
        int solutionRight = right;
        int solutionV = v;

        while (left < right) {
            if (dots[left] < dots[solutionLeft]) {
                left++;
                continue;
            }
            if (dots[right] < dots[solutionRight]) {
                right--;
                continue;
            }
            v = Math.min(dots[left], dots[right]) * (right - left);
            if (v > solutionV) {
                solutionLeft = left;
                solutionRight = right;
                solutionV = v;
            }
            if (dots[left] < dots[right]) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{solutionLeft, solutionRight, solutionV};
    }

    public int[] solve2(int[] dots) {
        int solutionLeft = 0;
        int solutionRight = dots.length - 1;
        int solutionV = Math.min(dots[solutionLeft], dots[solutionRight]) * (solutionRight - solutionLeft);
        int v;

        for (int i = 0; i < dots.length; i++) {
            for (int j = i+1; j < dots.length; j++) {
                v =  Math.min(dots[i], dots[j]) * (j - i);
                if (solutionV < v) {
                    solutionLeft = i;
                    solutionRight = j;
                    solutionV = v;
                }
            }
        }
        return new int[]{solutionLeft, solutionRight, solutionV};
    }

    public static void main(String[] args) {
        Task11 task = new Task11();
        int[] data;

        data = new int[]{500, 2, 50, 700, 1100, 100, 3};
        System.out.println("Initial array: " + Arrays.toString(data));
        System.out.println("Solution: " + Arrays.toString(task.solve(data)));
        System.out.println("Solution2: " + Arrays.toString(task.solve2(data)));

        data = new int[]{1, 2, 3, 4, 5};
        System.out.println("Initial array: " + Arrays.toString(data));
        System.out.println("Solution: " + Arrays.toString(task.solve(data)));
        System.out.println("Solution2: " + Arrays.toString(task.solve2(data)));

        data = new int[]{10, 3, 5, 2, 5, 9};
        System.out.println("Initial array: " + Arrays.toString(data));
        System.out.println("Solution: " + Arrays.toString(task.solve(data)));
        System.out.println("Solution2: " + Arrays.toString(task.solve2(data)));

        data = new int[]{2, 5, 80, 1, 6, 30};
        System.out.println("Initial array: " + Arrays.toString(data));
        System.out.println("Solution: " + Arrays.toString(task.solve(data)));
        System.out.println("Solution2: " + Arrays.toString(task.solve2(data)));
    }
}
