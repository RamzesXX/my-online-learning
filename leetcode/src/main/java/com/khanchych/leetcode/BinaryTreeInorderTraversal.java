package com.khanchych.leetcode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        return inorderTraversal(root, list);
    }

    private List<Integer> inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return list;
        }
        list = inorderTraversal(node.left, list);
        list.add(node.val);
        list = inorderTraversal(node.right, list);

        return list;
    }
}
