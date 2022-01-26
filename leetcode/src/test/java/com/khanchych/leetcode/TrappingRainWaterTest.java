package com.khanchych.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrappingRainWaterTest {
    private TrappingRainWater trappingRainWater;

    @BeforeEach
    void setUp() {
        this.trappingRainWater = new TrappingRainWater();
    }

    @Test
    void trap() {
        assertEquals(6, trappingRainWater.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

