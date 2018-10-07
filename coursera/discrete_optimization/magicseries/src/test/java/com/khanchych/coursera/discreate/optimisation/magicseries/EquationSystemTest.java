package com.khanchych.coursera.discreate.optimisation.magicseries;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquationSystemTest {

    EquationSystem system;
    @Before
    public void setUp() throws Exception {
        system = new EquationSystem(4);
    }

    @Test
    public void process() {
        system.getCurrentValues().set(0, 1);
        system.process();
    }
}