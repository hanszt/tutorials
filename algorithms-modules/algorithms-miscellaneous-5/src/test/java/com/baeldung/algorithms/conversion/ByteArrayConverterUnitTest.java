package com.baeldung.algorithms.conversion;

import org.apache.commons.codec.DecoderException;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ByteArrayConverterUnitTest {

    private HexStringConverter hexStringConverter;

    @BeforeEach
    public void setup() {
        hexStringConverter = new HexStringConverter();
    }

    @Test
    void shouldEncodeByteArrayToHexStringUsingBigIntegerToString() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        if (hexString.charAt(0) == '0') {
            hexString = hexString.substring(1);
        }
        var output = hexStringConverter.encodeUsingBigIntegerToString(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldEncodeByteArrayToHexStringUsingBigIntegerStringFormat() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.encodeUsingBigIntegerStringFormat(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldDecodeHexStringToByteArrayUsingBigInteger() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.decodeUsingBigInteger(hexString);
        assertArrayEquals(bytes, output);
    }

    @Test
    void shouldEncodeByteArrayToHexStringUsingCharacterConversion() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.encodeHexString(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldDecodeHexStringToByteArrayUsingCharacterConversion() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.decodeHexString(hexString);
        assertArrayEquals(bytes, output);
    }

    @Test
    void shouldDecodeHexToByteWithInvalidHexCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            hexStringConverter.hexToByte("fg");
        });
    }

    @Test
    void shouldEncodeByteArrayToHexStringDataTypeConverter() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.encodeUsingDataTypeConverter(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldDecodeHexStringToByteArrayUsingDataTypeConverter() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.decodeUsingDataTypeConverter(hexString);
        assertArrayEquals(bytes, output);
    }

    @Test
    void shouldEncodeByteArrayToHexStringUsingGuava() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.encodeUsingGuava(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldDecodeHexStringToByteArrayUsingGuava() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.decodeUsingGuava(hexString);
        assertArrayEquals(bytes, output);
    }

    @Test
    void shouldEncodeByteArrayToHexStringUsingApacheCommons() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.encodeUsingApacheCommons(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldDecodeHexStringToByteArrayUsingApacheCommons() throws DecoderException {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.decodeUsingApacheCommons(hexString);
        assertArrayEquals(bytes, output);
    }

    @Test
    void shouldEncodeByteArrayToHexStringUsingHexFormat() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.encodeUsingHexFormat(bytes);
        assertThat(output, IsEqualIgnoringCase.equalToIgnoringCase(hexString));
    }

    @Test
    void shouldDecodeHexStringToByteArrayUsingHexFormat() {
        var bytes = getSampleBytes();
        var hexString = getSampleHexString();
        var output = hexStringConverter.decodeUsingHexFormat(hexString);
        assertArrayEquals(bytes, output);
    }

    private String getSampleHexString() {
        return "0af50c0e2d10";
    }

    private byte[] getSampleBytes() {
        return new byte[]{10, -11, 12, 14, 45, 16};
    }

}
