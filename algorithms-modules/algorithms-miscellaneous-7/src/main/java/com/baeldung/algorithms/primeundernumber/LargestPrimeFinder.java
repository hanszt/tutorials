package com.baeldung.algorithms.primeundernumber;

import java.util.Arrays;

public class LargestPrimeFinder {

    public static int findByBruteForce(int n) {
        for (var i = n - 1; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // Return -1 if no prime number is found
    }

    public static boolean isPrime(int number) {
        for (var i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int findBySieveOfEratosthenes(int n) {
        var isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (var p = 2; p * p < n; p++) {
            if (isPrime[p]) {
                for (var i = p * p; i < n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        for (var i = n - 1; i >= 2; i--) {
            if (isPrime[i]) {
                return i;
            }
        }
        return -1;
    }
}
