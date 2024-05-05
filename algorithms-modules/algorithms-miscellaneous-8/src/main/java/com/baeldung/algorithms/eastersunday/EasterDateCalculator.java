package com.baeldung.algorithms.eastersunday;

import java.time.LocalDate;

public class EasterDateCalculator {
    
    LocalDate computeEasterDateWithGaussAlgorithm(int year) {
        var a = year % 19;
        var b = year % 4;
        var c = year % 7;
        var k = year / 100;
        var p = (13 + 8 * k) / 25;
        var q = k / 4;
        var M = (15 - p + k - q) % 30;
        var N = (4 + k - q) % 7;
        var d = (19 * a + M) % 30;
        var e = (2 * b + 4 * c + 6 * d + N) % 7;
        
        if (d==29 && e == 6) {
            return LocalDate.of(year, 4, 19);
        } else if (d==28 && e==6 && ((11*M + 11)%30 < 10)) {
            return LocalDate.of(year, 4, 18);
        }

        var H = 22 + d + e;
        if (H <= 31) {
            return LocalDate.of(year, 3, H);
        }
        return LocalDate.of(year, 4, H-31);
    }
    
    LocalDate computeEasterDateWithButcherMeeusAlgorithm(int year) {
        var a = year % 19;
        var b = year / 100;
        var c = year % 100;
        var d = b / 4;
        var e = b % 4;
        var f = (b + 8) / 25;
        var g = (b - f + 1) / 3;
        var h = (19 * a + b - d - g + 15) % 30;
        var i = c / 4;
        var k = c % 4;
        var l = (2 * e + 2 * i - h - k + 32) % 7;
        var m = (a + 11 * h + 22 * l) / 451;
        var t = h + l - 7 * m + 114;
        var n = t / 31;
        var o = t % 31;
        return LocalDate.of(year, n, o+1);
    }

    LocalDate computeEasterDateWithConwayAlgorithm(int year) {
        var s = year / 100;
        var t = year % 100;
        var a = t / 4;
        var p = s % 4;
        var x = (9 - 2 * p) % 7;
        var y = (x + t + a) % 7;
        var g = year % 19;
        var G = g + 1;
        var b = s / 4;
        var r = 8 * (s + 11) / 25;
        var C = -s + b + r;
        var d = (11 * G + C) % 30;
        d = (d + 30) % 30;
        var h = (551 - 19 * d + G) / 544;
        var e = (50 - d - h) % 7;
        var f = (e + y) % 7;
        var R = 57 - d - f - h;
        
        if (R <= 31) {
            return LocalDate.of(year, 3, R);
        }
        return LocalDate.of(year, 4, R-31);
    }
    
}
