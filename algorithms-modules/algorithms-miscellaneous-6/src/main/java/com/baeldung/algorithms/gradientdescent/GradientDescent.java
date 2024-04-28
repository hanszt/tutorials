package com.baeldung.algorithms.gradientdescent;

import java.util.function.DoubleUnaryOperator;

public final class GradientDescent {

    private static final double PRECISION = 0.000001;

    private GradientDescent() {
    }

    public static double findLocalMinimum(DoubleUnaryOperator f, double initialX) {
        double stepCoefficient = 0.1;
        double previousStep = 1.0;
        double currentX = initialX;
        double previousX = initialX;
        double previousY = f.applyAsDouble(previousX);
        int iter = 100;

        currentX += stepCoefficient * previousY;

        while (previousStep > PRECISION && iter > 0) {
            iter--;
            double currentY = f.applyAsDouble(currentX);
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
