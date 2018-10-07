package com.khanchych.coursera.discreate.optimisation.magicseries;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MagicSeriesTest {

    MagicSeries magicSeries;

    @Before
    public void setUp() throws Exception {
        magicSeries = new MagicSeries();
    }

    @Test
    public void checkIfSeriesMagic() {
        assertFalse(magicSeries.checkIfSeriesIsMagic(new int[5]));
        assertTrue(magicSeries.checkIfSeriesIsMagic(new int[] {2, 1, 2, 0, 0}));
        assertTrue(magicSeries.checkIfSeriesIsMagic(new int[] {1, 2, 1, 0}));
    }

    @Test
    public void solve() {
    }
}