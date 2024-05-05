package com.baeldung.algorithms.stringsort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class SortStringUnitTest {

    @Test
    void givenString_whenSort_thenSorted() {
        var abcd = "bdca";
        var chars = abcd.toCharArray();

        Arrays.sort(chars);
        var sorted = new String(chars);

        assertThat(sorted).isEqualTo("abcd");
    }

    @Test
    void givenString_whenSortJava8_thenSorted() {
        var sorted = "bdca".chars()
            .sorted()
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        assertThat(sorted).isEqualTo("abcd");
    }
}
