package com.khanchych.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task993Test {
    private Task993 task993;

    @BeforeEach
    public void setUp() throws Exception {
        task993 = new Task993();
    }

    @Test
    public void isCousins() {
        TreeNode root = createTree(new Integer[]{1, 2, 3, null, 4, null, 5}, 0);
        assertTrue(task993.isCousins(root, 5, 4));
    }

    private TreeNode createTree(Integer[] array, int index) {
        TreeNode node = new TreeNode(array[index]);
        int childIndex = 2*index + 1;

        if (childIndex < array.length) {
            if (array[childIndex] != null) {
                node.left = createTree(array, childIndex);
            }

            childIndex++;
            if (array[childIndex] != null) {
                node.right = createTree(array, childIndex);
            }
        }
        return node;
    }
}

