package com.baeldung.algorithms.selectionsort;

public class SelectionSort {

    public static void sortAscending(final int[] arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            var minElementIndex = i;
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[minElementIndex] > arr[j]) {
                    minElementIndex = j;
                }
            }

            if (minElementIndex != i) {
                var temp = arr[i];
                arr[i] = arr[minElementIndex];
                arr[minElementIndex] = temp;
            }
        }
    }

    public static void sortDescending(final int[] arr) {
        for (var i = 0; i < arr.length - 1; i++) {
            var maxElementIndex = i;
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[maxElementIndex] < arr[j]) {
                    maxElementIndex = j;
                }
            }

            if (maxElementIndex != i) {
                var temp = arr[i];
                arr[i] = arr[maxElementIndex];
                arr[maxElementIndex] = temp;
            }
        }
    }
}