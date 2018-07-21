package com.khanchych.fridaytasks;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

//A[0]<A[1]>A[2]<A[3]>A[4]…
public class SortArrayTest {

    private SortArray sortArray;

    @Before
    public void setUp() throws Exception {
        sortArray = new SortArray();
    }

    @Test
    public void sort() {
        int[] origin = {1, 5, 6, 3, 8, 2, 9, 7, 4};
        int[] expected = {1, 3, 2, 5, 4, 7, 6, 9, 8};

        assertArrayEquals(expected, sortArray.sort(origin));
    }
}