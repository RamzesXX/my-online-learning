package com.khanchych.leetcode;

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }


    private int maxDepth(TreeNode node, int level) {
        if (node == null) {
            return level;
        }
        level++;
        return Math.max(maxDepth(node.left, level), maxDepth(node.right, level));
    }
}
