package com.baeldung.algorithms.relativelyprime;

import static com.baeldung.algorithms.relativelyprime.RelativelyPrime.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RelativelyPrimeUnitTest {

    @Test
    void givenNonRelativelyPrimeNumbers_whenCheckingIteratively_shouldReturnFalse() {

        var result = iterativeRelativelyPrime(45, 35);
        assertThat(result).isFalse();
    }

    @Test
    void givenRelativelyPrimeNumbers_whenCheckingIteratively_shouldReturnTrue() {

        var result = iterativeRelativelyPrime(500, 501);
        assertThat(result).isTrue();
    }

    @Test
    void givenNonRelativelyPrimeNumbers_whenCheckingRecursively_shouldReturnFalse() {

        var result = recursiveRelativelyPrime(45, 35);
        assertThat(result).isFalse();
    }

    @Test
    void givenRelativelyPrimeNumbers_whenCheckingRecursively_shouldReturnTrue() {

        var result = recursiveRelativelyPrime(500, 501);
        assertThat(result).isTrue();
    }

    @Test
    void givenNonRelativelyPrimeNumbers_whenCheckingUsingBigIntegers_shouldReturnFalse() {

        var result = bigIntegerRelativelyPrime(45, 35);
        assertThat(result).isFalse();
    }

    @Test
    void givenRelativelyPrimeNumbers_whenCheckingBigIntegers_shouldReturnTrue() {

        var result = bigIntegerRelativelyPrime(500, 501);
        assertThat(result).isTrue();
    }
}
