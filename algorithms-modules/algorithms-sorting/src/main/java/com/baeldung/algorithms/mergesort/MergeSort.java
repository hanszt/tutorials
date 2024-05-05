package com.baeldung.algorithms.mergesort;

public class MergeSort {

    public static void main(String[] args) {
        var a = new int[]{5, 1, 6, 2, 3, 4};
        mergeSort(a, a.length);
        for (var i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        var mid = n / 2;
        var l = new int[mid];
        var r = new int[n - mid];

        for (var i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (var i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {

            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }

        }

        while (i < left) {
            a[k++] = l[i++];
        }

        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
