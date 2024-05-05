package com.baeldung.algorithms.gradientdescent;

import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.pow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradientDescentUnitTest {

    @Test
    void givenFunction_whenStartingPointIsOne_thenLocalMinimumIsFound() {
        var res = GradientDescent.findLocalMinimum(x -> abs(pow(x, 3)) - (3 * pow(x, 2)) + x, 1);

        assertTrue(res > 1.78);
        assertTrue(res < 1.84);
    }
}
