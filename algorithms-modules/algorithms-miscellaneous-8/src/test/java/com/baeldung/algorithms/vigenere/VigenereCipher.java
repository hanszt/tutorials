package com.baeldung.algorithms.vigenere;

public class VigenereCipher {
    private final String characters;

    public VigenereCipher() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public VigenereCipher(String characters) {
        this.characters = characters;
    }

    public String encode(String input, String key) {
        var result = "";

        var keyPosition = 0;
        for (var c : input.toCharArray()) {
            var k = key.charAt(keyPosition % key.length());

            var charIndex = characters.indexOf(c);
            var keyIndex = characters.indexOf(k);

            if (charIndex >= 0) {
                if (keyIndex >= 0) {
                    var newCharIndex = (charIndex + keyIndex + 1) % characters.length();
                    c = characters.charAt(newCharIndex);

                }

                keyPosition++;
            }

            result += c;
        }

        return result;
    }

    public String decode(String input, String key) {
        var result = "";

        var keyPosition = 0;
        for (var c : input.toCharArray()) {
            var k = key.charAt(keyPosition % key.length());

            var charIndex = characters.indexOf(c);
            var keyIndex = characters.indexOf(k);

            if (charIndex >= 0) {
                if (keyIndex >= 0) {
                    var newCharIndex = charIndex - keyIndex - 1;
                    if (newCharIndex < 0) {
                        newCharIndex = characters.length() + newCharIndex;
                    }
                    c = characters.charAt(newCharIndex);

                }

                keyPosition++;
            }

            result += c;
        }

        return result;
    }

}
