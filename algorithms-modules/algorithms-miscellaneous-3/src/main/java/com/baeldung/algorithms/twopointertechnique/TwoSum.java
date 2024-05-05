package com.baeldung.algorithms.twopointertechnique;

public class TwoSum {

    public boolean twoSum(int[] input, int targetValue) {

        var pointerOne = 0;
        var pointerTwo = input.length - 1;

        while (pointerOne < pointerTwo) {
            var sum = input[pointerOne] + input[pointerTwo];

            if (sum == targetValue) {
                return true;
            } else if (sum < targetValue) {
                pointerOne++;
            } else {
                pointerTwo--;
            }
        }

        return false;
    }

    public boolean twoSumSlow(int[] input, int targetValue) {

        for (var i = 0; i < input.length; i++) {
            for (var j = 1; j < input.length; j++) {
                if (input[i] + input[j] == targetValue) {
                    return true;
                }
            }
        }

        return false;
    }

}
