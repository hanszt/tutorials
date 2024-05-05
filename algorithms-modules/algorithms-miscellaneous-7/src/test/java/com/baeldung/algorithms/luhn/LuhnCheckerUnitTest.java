package com.baeldung.algorithms.luhn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LuhnCheckerUnitTest {

    @Test
    void whenCardNumberDoesMeetLuhnCriteria_thenCheckLuhnReturnsTrue() {
        var cardNumber = "8649";
        var result = LuhnChecker.checkLuhn(cardNumber);
        assertTrue(result);
    }
    
    @Test
    void whenCardNumberDoesNotMeetLuhnCriteria_thenCheckLuhnReturnsFalse() {
        var cardNumber = "8642";
        var result = LuhnChecker.checkLuhn(cardNumber);
        assertFalse(result);
    }
    
    @Test
    void whenCardNumberHasNoSecondDigits_thenCheckLuhnCalculatesCorrectly() {
        var cardNumber = "0505050505050505";
        var result = LuhnChecker.checkLuhn(cardNumber);
        assertTrue(result);
    }

    @Test
    void whenCardNumberHasSecondDigits_thenCheckLuhnCalculatesCorrectly() {
        var cardNumber = "75757575757575";
        var result = LuhnChecker.checkLuhn(cardNumber);
        assertTrue(result);
    }
    
    @Test
    void whenDoubleAndSumDigitsIsCalled_thenOutputIsCorrect() {
        assertEquals(0,LuhnChecker.doubleAndSumDigits(0));
        assertEquals(2,LuhnChecker.doubleAndSumDigits(1));
        assertEquals(4, LuhnChecker.doubleAndSumDigits(2));
        assertEquals(6, LuhnChecker.doubleAndSumDigits(3));
        assertEquals(8, LuhnChecker.doubleAndSumDigits(4));
        assertEquals(1, LuhnChecker.doubleAndSumDigits(5));
        assertEquals(3, LuhnChecker.doubleAndSumDigits(6));
        assertEquals(5, LuhnChecker.doubleAndSumDigits(7));
        assertEquals(7, LuhnChecker.doubleAndSumDigits(8));
        assertEquals(9, LuhnChecker.doubleAndSumDigits(9));
    }
    
    @Test
    void whenCardNumberNonNumeric_thenCheckLuhnReturnsFalse() {
        var cardNumber = "test";
        var result = LuhnChecker.checkLuhn(cardNumber);
        assertFalse(result);
    }
    
    @Test
    void whenCardNumberIsNull_thenCheckLuhnReturnsFalse() {
        String cardNumber = null;
        var result = LuhnChecker.checkLuhn(cardNumber);
        assertFalse(result);
    }
}
