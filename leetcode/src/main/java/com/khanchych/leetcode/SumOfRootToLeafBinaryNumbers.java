package com.khanchych.leetcode;

public class SumOfRootToLeafBinaryNumbers {
    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root, 0);
    }


    private int sumRootToLeaf(TreeNode node, int production) {
        production = production * 2 + node.val;
        if (isLeaf(node)) {
            return production;
        }
        int sum = 0;
        if (node.left != null) {
            sum +=  sumRootToLeaf(node.left, production);
        }
        if (node.right != null) {
            sum +=  sumRootToLeaf(node.right, production);
        }
        return sum;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
