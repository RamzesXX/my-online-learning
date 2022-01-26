package com.khanchych.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TaskXXXXTest {
    private Task11 task;

    @BeforeEach
    public void setUp() throws Exception {
        this.task = new Task11();
    }

    @Test
    public void solve() {
        int[] input1 = {3,2,50, 100,3};
        int[] input2 = {};
        int[] input3 = {};
        int[] input4 = {};

        assertArrayEquals(task.solve(input1), new int[] {0,2,2});
    }
}
