package com.baeldung.algorithms.inoutsort;

public class InOutSort {

    public static int[] reverseInPlace(int A[]) {
        var n = A.length;
        for (var i = 0; i < n / 2; i++) {
            var temp = A[i];
            A[i] = A[n - 1 - i];
            A[n - 1 - i] = temp;
        }

        return A;
    }

    public static int[] reverseOutOfPlace(int A[]) {
        var n = A.length;
        var B = new int[n];
        for (var i = 0; i < n; i++) {
            B[n - i - 1] = A[i];
        }

        return B;
    }
}
