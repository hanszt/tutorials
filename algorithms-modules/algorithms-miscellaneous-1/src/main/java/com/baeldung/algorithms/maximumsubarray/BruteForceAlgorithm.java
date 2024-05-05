package com.baeldung.algorithms.maximumsubarray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BruteForceAlgorithm {

    private Logger logger = LoggerFactory.getLogger(BruteForceAlgorithm.class.getName());

    public int maxSubArray(int[] arr) {

        var size = arr.length;
        var maximumSubArraySum = Integer.MIN_VALUE;
        var start = 0;
        var end = 0;

        for (var left = 0; left < size; left++) {

            var runningWindowSum = 0;

            for (var right = left; right < size; right++) {
                runningWindowSum += arr[right];

                if (runningWindowSum > maximumSubArraySum) {
                    maximumSubArraySum = runningWindowSum;
                    start = left;
                    end = right;
                }
            }
        }
        logger.info("Found Maximum Subarray between {} and {}", start, end);
        return maximumSubArraySum;
    }

}
