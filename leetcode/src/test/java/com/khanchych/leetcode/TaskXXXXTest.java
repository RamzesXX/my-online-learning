package com.khanchych.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TaskXXXXTest {
    private Task11 task;

    @Before
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