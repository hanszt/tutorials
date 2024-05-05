package com.baeldung.algorithms.printtriangles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PrintTriangleExamplesUnitTest {

    private static Object[][] rightTriangles() {
        var expected0 = "";

        var expected2 = "*" + System.lineSeparator()
                        + "**" + System.lineSeparator();

        var expected5 = "*" + System.lineSeparator()
                        + "**" + System.lineSeparator()
                        + "***" + System.lineSeparator()
                        + "****" + System.lineSeparator()
                        + "*****" + System.lineSeparator();

        var expected7 = "*" + System.lineSeparator()
                        + "**" + System.lineSeparator()
                        + "***" + System.lineSeparator()
                        + "****" + System.lineSeparator()
                        + "*****" + System.lineSeparator()
                        + "******" + System.lineSeparator()
                        + "*******" + System.lineSeparator();
        
        return new Object[][] {
            { 0, expected0 },
            { 2, expected2 },
            { 5, expected5 },
            { 7, expected7 }
        };
    }

    @ParameterizedTest
    @MethodSource("rightTriangles")
    void whenPrintARightTriangleIsCalled_ThenTheCorrectStringIsReturned(int nrOfRows, String expected) {
        var actual = PrintTriangleExamples.printARightTriangle(nrOfRows);

        assertEquals(expected, actual);
    }
    
    private static Object[][] isoscelesTriangles() {
        var expected0 = "";

        var expected2 = " *" + System.lineSeparator()
                        + "***" + System.lineSeparator();

        var expected5 = "    *" + System.lineSeparator()
                        + "   ***" + System.lineSeparator()
                        + "  *****" + System.lineSeparator()
                        + " *******" + System.lineSeparator()
                        + "*********" + System.lineSeparator();

        var expected7 = "      *" + System.lineSeparator()
                        + "     ***" + System.lineSeparator()
                        + "    *****" + System.lineSeparator()
                        + "   *******" + System.lineSeparator()
                        + "  *********" + System.lineSeparator()
                        + " ***********" + System.lineSeparator()
                        + "*************" + System.lineSeparator();
        
        return new Object[][] {
            { 0, expected0 },
            { 2, expected2 },
            { 5, expected5 },
            { 7, expected7 }
        };
    }

    @ParameterizedTest
    @MethodSource("isoscelesTriangles")
    void whenPrintAnIsoscelesTriangleIsCalled_ThenTheCorrectStringIsReturned(int nrOfRows, String expected) {
        var actual = PrintTriangleExamples.printAnIsoscelesTriangle(nrOfRows);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("isoscelesTriangles")
    public void whenPrintAnIsoscelesTriangleUsingStringUtilsIsCalled_ThenTheCorrectStringIsReturned(int nrOfRows, String expected) {
        var actual = PrintTriangleExamples.printAnIsoscelesTriangleUsingStringUtils(nrOfRows);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("isoscelesTriangles")
    public void whenPrintAnIsoscelesTriangleUsingSubstringIsCalled_ThenTheCorrectStringIsReturned(int nrOfRows, String expected) {
        var actual = PrintTriangleExamples.printAnIsoscelesTriangleUsingSubstring(nrOfRows);

        assertEquals(expected, actual);
    }

}
