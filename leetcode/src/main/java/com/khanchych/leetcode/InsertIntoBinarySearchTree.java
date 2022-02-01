package com.khanchych.leetcode;

public class InsertIntoBinarySearchTree {
    public static class TreeNode {
        int val;
        InsertIntoBinarySearchTree.TreeNode left;
        InsertIntoBinarySearchTree.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, InsertIntoBinarySearchTree.TreeNode left, InsertIntoBinarySearchTree.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        return add(root, val);
    }

    public TreeNode add(TreeNode root, int val) {
        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                add(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                add(root.right, val);
            }
        }

        return root;
    }
}
