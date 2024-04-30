package com.baeldung.algorithms.kthlargest;

import java.util.Arrays;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public final class FindKthLargest {

    private final RandomGenerator random;

    public FindKthLargest(RandomGenerator random) {
        this.random = random;
    }

    public int findKthLargestBySorting(final int[] arr, final int k) {
        Arrays.sort(arr);
        final var targetIndex = arr.length - k;
        return arr[targetIndex];
    }

    public int findKthLargestBySortingDesc(final int[] arr, final int k) {
        Arrays.sort(arr);
        reverse(arr);
        return arr[k - 1];
    }

    public int findKthElementByQuickSelect(final int[] arr, final int left, final int right, final int k) {
        if (k >= 0 && k <= right - left + 1) {
            final var pos = partition(arr, left, right);
            if (pos - left == k) {
                return arr[pos];
            }
            if (pos - left > k) {
                return findKthElementByQuickSelect(arr, left, pos - 1, k);
            }
            return findKthElementByQuickSelect(arr, pos + 1, right, k - pos + left - 1);
        }
        return 0;
    }

    public int findKthElementByQuickSelectWithIterativePartition(final int[] arr, final int left, final int right, final int k) {
        if (k >= 0 && k <= right - left + 1) {
            final var pos = partitionIterative(arr, left, right);
            if (pos - left == k) {
                return arr[pos];
            }
            if (pos - left > k) {
                return findKthElementByQuickSelectWithIterativePartition(arr, left, pos - 1, k);
            }
            return findKthElementByQuickSelectWithIterativePartition(arr, pos + 1, right, k - pos + left - 1);
        }
        return 0;
    }

    private int partition(final int[] arr, final int left, final int right) {
        final int pivot = arr[right];

        final var leftArr = IntStream.range(left, right)
                .filter(i -> arr[i] < pivot)
                .map(i -> arr[i])
                .toArray();

        final var rightArr = IntStream.range(left, right)
                .filter(i -> arr[i] > pivot)
                .map(i -> arr[i])
                .toArray();

        final var leftArraySize = leftArr.length;
        System.arraycopy(leftArr, 0, arr, left, leftArraySize);
        arr[leftArraySize + left] = pivot;
        System.arraycopy(rightArr, 0, arr, left + leftArraySize + 1, rightArr.length);

        return left + leftArraySize;
    }

    private int partitionIterative(final int[] arr, final int left, final int right) {
        final int pivot = arr[right];
        int i = left;
        for (var j = left; j <= right - 1; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    public int findKthElementByRandomizedQuickSelect(final int[] arr, final int left, final int right, final int k) {
        if (k >= 0 && k <= right - left + 1) {
            final var pos = randomPartition(arr, left, right);
            if (pos - left == k) {
                return arr[pos];
            }
            if (pos - left > k) {
                return findKthElementByRandomizedQuickSelect(arr, left, pos - 1, k);
            }
            return findKthElementByRandomizedQuickSelect(arr, pos + 1, right, k - pos + left - 1);
        }
        return 0;
    }

    private int randomPartition(final int[] arr, final int left, final int right) {
        final var n = right - left + 1;
        final var pivot = random.nextInt(n);
        swap(arr, left + pivot, right);
        return partition(arr, left, right);
    }

    private static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int j = array.length - i - 1;
            swap(array, i, j);
        }
    }

    private static void swap(final int[] arr, final int n1, final int n2) {
        final int temp = arr[n2];
        arr[n2] = arr[n1];
        arr[n1] = temp;
    }
}
