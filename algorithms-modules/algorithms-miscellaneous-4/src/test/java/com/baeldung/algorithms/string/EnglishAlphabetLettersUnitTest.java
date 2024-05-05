package com.baeldung.algorithms.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnglishAlphabetLettersUnitTest {

    @Test
    void givenString_whenContainsAllCharacter_thenTrue() {
        var input = "Farmer jack realized that big yellow quilts were expensive";
        Assertions.assertTrue(EnglishAlphabetLetters.checkStringForAllTheLetters(input));
    }

    @Test
    void givenString_whenContainsAllCharacter_thenUsingStreamExpectTrue() {
        var input = "Farmer jack realized that big yellow quilts were expensive";
        Assertions.assertTrue(EnglishAlphabetLetters.checkStringForAllLetterUsingStream(input));
    }

}
