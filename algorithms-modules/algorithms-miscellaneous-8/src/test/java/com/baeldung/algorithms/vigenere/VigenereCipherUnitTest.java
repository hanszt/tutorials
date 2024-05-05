package com.baeldung.algorithms.vigenere;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VigenereCipherUnitTest {

    @Test
    void encodeBaeldung() {
        var cipher = new VigenereCipher();
        var output = cipher.encode("BAELDUNG", "HELLO");

        Assertions.assertEquals("JFQXSCSS", output);
    }

    @Test
    void encodeBaeldungMixedCharacters() {
        var cipher = new VigenereCipher("JQFVHPWORZSLNMKYCGBUXIEDTA");
        var output = cipher.encode("BAELDUNG", "HELLO");

        Assertions.assertEquals("DERDPTZV", output);
    }

    @Test
    void encodeArticleTitle() {
        var cipher = new VigenereCipher();
        var output = cipher.encode("VIGENERE CIPHER IN JAVA", "BAELDUNG");

        Assertions.assertEquals("XJLQRZFL EJUTIM WU LBAM", output);
    }

    @Test
    void encodeArticleTitleMoreCharacters() {
        var cipher = new VigenereCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
        var output = cipher.encode("VIGENERE CIPHER IN JAVA", "BAELDUNG");

        Assertions.assertEquals("XJLQRZELBDNALZEGKOEVEPO", output);
    }

    @Test
    void decodeBaeldung() {
        var cipher = new VigenereCipher();
        var output = cipher.decode("JFQXSCSS", "HELLO");

        Assertions.assertEquals("BAELDUNG", output);
    }

    @Test
    void decodeBaeldungMixedCharacters() {
        var cipher = new VigenereCipher("JQFVHPWORZSLNMKYCGBUXIEDTA");
        var output = cipher.decode("DERDPTZV", "HELLO");

        Assertions.assertEquals("BAELDUNG", output);
    }

    @Test
    void decodeArticleTitleMoreCharacters() {
        var cipher = new VigenereCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
        var output = cipher.decode("XJLQRZELBDNALZEGKOEVEPO", "BAELDUNG");

        Assertions.assertEquals("VIGENERE CIPHER IN JAVA", output);
    }

    @Test
    void encodeDecodeBaeldung() {
        var cipher = new VigenereCipher();

        var input = "BAELDUNG";
        var key = "HELLO";

        var encoded = cipher.encode(input, key);
        var decoded = cipher.decode(encoded, key);

        Assertions.assertEquals(input, decoded);
    }
}
