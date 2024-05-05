package com.baeldung.algorithms.bynumber;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.baeldung.algorithms.bynumber.NaturalOrderComparators.comparingByNumbersInString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NaturalOrderComparatorsUnitTest {

    @Test
    void givenSimpleStringsContainingIntsAndDoubles_whenSortedByRegex_checkSortingCorrect() {
        var testStrings = Stream.of("a1", "b3", "c4", "d2.2", "d2.4", "d2.3d")
                .sorted(comparingByNumbersInString)
                .toList();

        assertEquals(List.of("a1", "d2.2", "d2.3d", "d2.4", "b3", "c4"), testStrings);


    }

    @Test
    void givenSimpleStringsContainingIntsAndDoublesWithAnInvalidNumber_whenSortedByRegex_checkSortingCorrect() {
        var testStrings = Stream.of("a1", "b3", "c4", "d2.2", "d2.4", "d2.3.3d")
                .sorted(comparingByNumbersInString)
                .toList();

        assertEquals(List.of("d2.3.3d", "a1", "d2.2", "d2.4", "b3", "c4"), testStrings);


    }

    @Test
    void givenAllForseenProblems_whenSortedByRegex_checkSortingCorrect() {
        var testStrings = Stream.of("a1", "b3", "c4", "d2.2", "d2.f4", "d2.3.3d")
                .sorted(comparingByNumbersInString)
                .toList();

        assertEquals(List.of("d2.3.3d", "a1", "d2.2", "d2.f4", "b3", "c4"), testStrings);


    }

    @Test
    void givenComplexStringsContainingSeparatedNumbers_whenSortedByRegex_checkNumbersCondensedAndSorted() {
        var testStrings = Stream.of("a1b2c5", "b3ght3.2", "something65.thensomething5") //125, 33.2, 65.5
                .sorted(comparingByNumbersInString)
                .toList();

        assertEquals(List.of("b3ght3.2", "something65.thensomething5", "a1b2c5"), testStrings);

    }

    @Test
    void givenStringsNotContainingNumbers_whenSortedByRegex_checkOrderNotChanged() {
        final var input = List.of("c", "a", "d", "e");

        var testStrings = input.stream()
                .sorted(comparingByNumbersInString)
                .toList();

        assertEquals(input, testStrings);
    }
}