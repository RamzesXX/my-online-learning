package com.khanchych.friday;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseSchedulingFeasibilityTest {
    private CourseSchedulingFeasibility courseScheduling;

    @Before
    public void setUp() throws Exception {
        courseScheduling = new CourseSchedulingFeasibility();
    }

    @Test
    public void canFinish() {
        assertFalse(courseScheduling.canFinish(2, new int[][] {{1,0},{0,1}}));
        assertFalse(courseScheduling.canFinish(5, new int[][] {{0,1},{1,2},{2,0}}));
        assertTrue(courseScheduling.canFinish(2, new int[][] {{1,0}}));
        assertTrue(courseScheduling.canFinish(3, new int[][] {{1,0}, {2,0}}));
        assertTrue(courseScheduling.canFinish(3, new int[][] {{0,1}, {0,2}, {1,2}}));
    }
}
