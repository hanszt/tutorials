package com.baeldung.algorithms.movingaverages;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovingAverageWithStreamBasedApproachUnitTest {

    @Test
    public void whenEmptyDataIsPassed_shouldReturnZero() {
        double[] data = {};
        var windowSize = 3;
        double expectedAverage = 0;
        var calculator = new MovingAverageWithStreamBasedApproach(windowSize);
        var actualAverage = calculator.calculateAverage(data);
        assertEquals(expectedAverage, actualAverage);
    }

    @Test
    public void whenValidDataIsPassed_shouldReturnCorrectAverage() {
        double[] data = {10, 20, 30, 40, 50};
        var windowSize = 3;
        double expectedAverage = 40;
        var calculator = new MovingAverageWithStreamBasedApproach(windowSize);
        var actualAverage = calculator.calculateAverage(data);
        assertEquals(expectedAverage, actualAverage);
    }

    @Test
    public void whenValidDataIsPassedWithLongerWindowSize_shouldReturnCorrectAverage() {
        double[] data = {10, 20, 30, 40, 50};
        var windowSize = 5;
        double expectedAverage = 30;
        var calculator = new MovingAverageWithStreamBasedApproach(windowSize);
        var actualAverage = calculator.calculateAverage(data);
        assertEquals(expectedAverage, actualAverage);
    }
}