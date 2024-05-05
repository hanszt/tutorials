package com.baeldung.algorithms.gradientdescent;

import java.util.function.DoubleUnaryOperator;

public final class GradientDescent {

    private static final double PRECISION = 0.000001;

    private GradientDescent() {
    }

    public static double findLocalMinimum(DoubleUnaryOperator f, double initialX) {
        var stepCoefficient = 0.1;
        var previousStep = 1.0;
        var currentX = initialX;
        var previousX = initialX;
        var previousY = f.applyAsDouble(previousX);
        var iter = 100;

        currentX += stepCoefficient * previousY;

        while (previousStep > PRECISION && iter > 0) {
            iter--;
            var currentY = f.applyAsDouble(currentX);
            if (currentY > previousY) {
                stepCoefficient = -stepCoefficient / 2;
            }
            previousX = currentX;
            currentX += stepCoefficient * previousY;
            previousY = currentY;
            previousStep = StrictMath.abs(currentX - previousX);
        }
        return currentX;
    }

}
