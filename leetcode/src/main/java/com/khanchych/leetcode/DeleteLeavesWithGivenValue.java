package com.khanchych.leetcode;

public class DeleteLeavesWithGivenValue {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left = removeLeafNodes(root.left, target);
        }

        if (root.right != null) {
            root.right = removeLeafNodes(root.right, target);
        }

        if (isLeaf(root) && root.val == target)  {
            return null;
        }

        return root;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
