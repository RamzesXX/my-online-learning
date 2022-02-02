package com.khanchych.leetcode;

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        for(int level = triangle.size() - 1; level > 0; level--) {
            List<Integer> upper = triangle.get(level - 1);
            List<Integer> lower = triangle.get(level);
            for(int pos = 0; pos < upper.size(); pos++) {
                upper.set(pos, upper.get(pos) + Math.min(lower.get(pos), lower.get(pos+1)));
            }
        }

        return triangle.get(0).get(0);
    }
}
