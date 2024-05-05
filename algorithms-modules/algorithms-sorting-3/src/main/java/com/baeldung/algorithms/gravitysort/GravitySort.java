package com.baeldung.algorithms.gravitysort;

public final class GravitySort {

    private GravitySort() {
    }

    private static int findMax(int[] A) {
        var max = A[0];
        for (var i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    private static boolean[][] setupAbacus(int[] A, int m) {
        var abacus = new boolean[A.length][m];
        for (var i = 0; i < abacus.length; i++) {
            var number = A[i];
            for (var j = 0; j < abacus[0].length && j < number; j++) {
                abacus[A.length - 1 - i][j] = true;
            }
        }
        return abacus;
    }

    private static void dropBeads(boolean[][] abacus, int[] A, int m) {
        for (var i = 1; i < A.length; i++) {
            for (var j = m - 1; j >= 0; j--) {
                if (abacus[i][j]) {
                    var x = i;
                    while (x > 0 && !abacus[x - 1][j]) {
                        var temp = abacus[x - 1][j];
                        abacus[x - 1][j] = abacus[x][j];
                        abacus[x][j] = temp;
                        x--;
                    }
                }
            }
        }
    }

    private static void transformToList(boolean[][] abacus, int[] A) {
        var index = 0;
        for (var i = abacus.length - 1; i >= 0; i--) {
            var beads = 0;
            for (var j = 0; j < abacus[0].length && abacus[i][j]; j++) {
                beads++;
            }
            A[index++] = beads;
        }
    }

    public static void sort(int[] A) {
        var m = findMax(A);
        var abacus = setupAbacus(A, m);
        dropBeads(abacus, A, m);
        // transform abacus into sorted list
        transformToList(abacus, A);
    }
}
