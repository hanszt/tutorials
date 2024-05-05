package com.baeldung.algorithms.shellsort;

public class ShellSort {

    public static void sort(int arrayToSort[]) {
        var n = arrayToSort.length;

        for (var gap = n / 2; gap > 0; gap /= 2) {
            for (var i = gap; i < n; i++) {
                var key = arrayToSort[i];
                var j = i;
                while (j >= gap && arrayToSort[j - gap] > key) {
                    arrayToSort[j] = arrayToSort[j - gap];
                    j -= gap;
                }
                arrayToSort[j] = key;
            }
        }
    }
}
