package com.baeldung.algorithms.bubblesort;

public final class BubbleSort {

    private BubbleSort() {
    }

    static void bubbleSort(Integer[] arr) {
        int n = arr.length;
        int boundOuter = n - 1;
        for (int i = 0; i < boundOuter; i++) {
            int bound = n - i;
            for (int j = 1; j < bound; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    static void optimizedBubbleSort(Integer[] arr) {
        int i = 0, n = arr.length;

        boolean swapNeeded;
        while (i < n - 1) {
            swapNeeded = false;
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {

                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapNeeded = true;
                }
            }
            if (!swapNeeded) {
                break;
            }
            i++;
        }
    }
}
