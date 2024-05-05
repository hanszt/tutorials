package com.baeldung.algorithms.string;

import java.util.HashSet;
import java.util.Set;

public class SubstringPalindrome {

    public Set<String> findAllPalindromesUsingCenter(String input) {
        final Set<String> palindromes = new HashSet<>();
        if (input == null || input.isEmpty()) {
            return palindromes;
        }
        if (input.length() == 1) {
            palindromes.add(input);
            return palindromes;
        }
        for (var i = 0; i < input.length(); i++) {
            palindromes.addAll(findPalindromes(input, i, i + 1));
            palindromes.addAll(findPalindromes(input, i, i));
        }
        return palindromes;
    }

    private Set<String> findPalindromes(String input, int low, int high) {
        Set<String> result = new HashSet<>();
        while (low >= 0 && high < input.length() && input.charAt(low) == input.charAt(high)) {
            result.add(input.substring(low, high + 1));
            low--;
            high++;
        }
        return result;
    }

    public Set<String> findAllPalindromesUsingBruteForceApproach(String input) {
        Set<String> palindromes = new HashSet<>();
        if (input == null || input.isEmpty()) {
            return palindromes;
        }
        if (input.length() == 1) {
            palindromes.add(input);
            return palindromes;
        }
        for (var i = 0; i < input.length(); i++) {
            for (var j = i + 1; j <= input.length(); j++) {
                if (isPalindrome(input.substring(i, j))) {
                    palindromes.add(input.substring(i, j));
                }
            }
        }
        return palindromes;
    }

    private boolean isPalindrome(String input) {
        var plain = new StringBuilder(input);
        var reverse = plain.reverse();
        return (reverse.toString()).equals(input);
    }

    public Set<String> findAllPalindromesUsingManachersAlgorithm(String input) {
        Set<String> palindromes = new HashSet<>();
        var formattedInput = "@" + input + "#";
        char inputCharArr[] = formattedInput.toCharArray();
        int max;
        int radius[][] = new int[2][input.length() + 1];
        for (var j = 0; j <= 1; j++) {
            radius[j][0] = max = 0;
            var i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - max - 1] == inputCharArr[i + j + max]) {
                    max++;
                }
                radius[j][i] = max;
                var k = 1;
                while ((radius[j][i - k] != max - k) && (k < max)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], max - k);
                    k++;
                }
                max = Math.max(max - k, 0);
                i += k;
            }
        }
        for (var i = 1; i <= input.length(); i++) {
            for (var j = 0; j <= 1; j++) {
                for (max = radius[j][i]; max > 0; max--) {
                    palindromes.add(input.substring(i - max - 1, max + j + i - 1));
                }
            }
        }
        return palindromes;
    }
}
