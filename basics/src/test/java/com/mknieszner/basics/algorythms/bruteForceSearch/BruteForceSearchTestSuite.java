package com.mknieszner.basics.algorythms.bruteForceSearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BruteForceSearchTestSuite {

    @Test
    public void bruteForceSearchTest() {
        double startX = -1;
        double endX = 2;
        double dx = 0.1;

        double result = BruteForceSearch.bruteForceSearch(-1, 2, dx, (x) -> -1 * (x - 1) * (x - 1) + 2);
        assertEquals(2, result, 0.01);
    }


}
