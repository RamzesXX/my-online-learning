package com.khanchych.leetcode;

public class BinarySearchTreeFromPreorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            add(root, preorder[i]);
        }

        return root;
    }

    private void add(TreeNode root, int number) {
        if (root.val > number) {
            if (root.left == null) {
                root.left = new TreeNode(number);
            } else {
                add(root.left, number);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(number);
            } else {
                add(root.right, number);
            }
        }
    }
}
