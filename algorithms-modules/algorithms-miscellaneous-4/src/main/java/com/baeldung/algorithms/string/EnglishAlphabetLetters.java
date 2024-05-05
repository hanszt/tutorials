package com.baeldung.algorithms.string;

public class EnglishAlphabetLetters {

    public static boolean checkStringForAllTheLetters(String input) {
        var visited = new boolean[26];

        var index = 0;

        for (var id = 0; id < input.length(); id++) {
            if ('a' <= input.charAt(id) && input.charAt(id) <= 'z') {
                index = input.charAt(id) - 'a';
            } else if ('A' <= input.charAt(id) && input.charAt(id) <= 'Z') {
                index = input.charAt(id) - 'A';
            }
            visited[index] = true;
        }

        for (var id = 0; id < 26; id++) {
            if (!visited[id]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkStringForAllLetterUsingStream(String input) {
        var c = input.toLowerCase().chars().filter(ch -> ch >= 'a' && ch <= 'z').distinct().count();
        return c == 26;
    }

    public static void main(String[] args) {
        checkStringForAllLetterUsingStream("intit");
    }
}