package com.khanchych.fridaytasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class MOUCacheTest {

    MOUCache<Integer, Integer> cache;

    @Before
    public void setUp() throws Exception {
        this.cache = new MOUCache<>(3);
    }

    @Test
    public void getPut() {
        assertNull(cache.get(1));
        cache.put(1, 1);
        assertEquals(Integer.valueOf(1), cache.get(1));
        cache.put(2, 2);
        assertEquals(Integer.valueOf(1), cache.get(1));
        assertEquals(Integer.valueOf(2), cache.get(2));
        assertNull(cache.get(3));
        cache.put(3, 3);
        assertEquals(Integer.valueOf(3), cache.get(3));
        cache.put(4, 4);
        assertNull(cache.get(1));
        cache.put(5, 5);
        assertNull(cache.get(2));
        cache.put(6, 6);
        assertEquals(Integer.valueOf(6), cache.get(6));

    }

}