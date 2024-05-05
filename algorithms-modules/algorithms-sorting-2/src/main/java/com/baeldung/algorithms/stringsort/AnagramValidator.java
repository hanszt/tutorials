package com.baeldung.algorithms.stringsort;

import java.util.Arrays;

public class AnagramValidator {

    public static boolean isValid(String text, String anagram) {
        text = prepare(text);
        anagram = prepare(anagram);

        var sortedText = sort(text);
        var sortedAnagram = sort(anagram);

        return sortedText.equals(sortedAnagram);
    }

    private static String sort(String text) {
        var chars = prepare(text).toCharArray();

        Arrays.sort(chars);
        return new String(chars);
    }

    private static String prepare(String text) {
        return text.toLowerCase()
            .trim()
            .replaceAll("\\s+", "");
    }
}