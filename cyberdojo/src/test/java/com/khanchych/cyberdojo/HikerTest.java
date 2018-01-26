package com.khanchych.cyberdojo;

import static org.junit.Assert.*;
import org.junit.Test;

public class HikerTest {
    @Test
    public void testCombinedNumber1() {
        String expected = "95021";
        String actual = Hiker.answer(new Integer[]{50, 2, 1, 9});
        assertEquals(expected, actual);
    }

    @Test
    public void testCombinedNumber2() {
        String expected = "56550";
        String actual = Hiker.answer(new Integer[]{5, 50, 56});
        assertEquals(expected, actual);
    }

    @Test
    public void testCombinedNumber3() {
        String expected = "42423420";
        String actual = Hiker.answer(new Integer[]{420, 42, 423});
        assertEquals(expected, actual);
    }


}
