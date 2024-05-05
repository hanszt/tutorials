package com.baeldung.algorithms.checktargetsum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckTargetSum {
    public boolean isTargetSumExistNaive(int[] nums, int target) {
        for (var i = 0; i < nums.length; i++) {
            for (var j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isTargetSumExistSorted(int[] nums, int target) {
        Arrays.sort(nums);

        var start = 0;
        var end = nums.length - 1;

        while (start < end) {
            var sum = nums[start] + nums[end];

            if (sum == target) {
                return true;
            }

            if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        return false;
    }

    public boolean isTargetSumExistHashSet(int[] nums, int target) {
        Set<Integer> hashSet = new HashSet<>();

        for (var num : nums) {
            var diff = target - num;

            if (hashSet.contains(diff)) {
                return true;
            }

            hashSet.add(num);
        }

        return false;
    }
}
