package com.khanchych.leetcode;

/**
 * Cousins in Binary Tree
 * User Accepted: 2327
 * User Tried: 2533
 * Total Accepted: 2384
 * Total Submissions: 4430
 * Difficulty: Easy
 * <p>
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Task993 {

    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode[] nodes = new TreeNode[100];
        int[] levels = new int[105];
        int index = 0;
        int lastIndex = index;
        int ix = -1, iy = -1;
        nodes[index] = root;
        levels[index] = 1;

        while (nodes[index] != null) {
            TreeNode node = nodes[index];

            if (node.left != null && node.right != null &&
                    ((x == node.left.val && y == node.right.val) ||
                            (y == node.left.val && x == node.right.val))) {
                return false;
            }

            if (x == node.val) {
                ix = levels[index];
            }

            if (y == node.val) {
                iy = levels[index];
            }

            if (ix > 0 && iy > 0) {
                return ix == iy;
            }

            if (node.left != null) {
                nodes[++lastIndex] = node.left;
                levels[lastIndex] = levels[index] + 1;
            }

            if (node.right != null) {
                nodes[++lastIndex] = node.right;
                levels[lastIndex] = levels[index] + 1;
            }
            index++;
        }

        return false;
    }
}
