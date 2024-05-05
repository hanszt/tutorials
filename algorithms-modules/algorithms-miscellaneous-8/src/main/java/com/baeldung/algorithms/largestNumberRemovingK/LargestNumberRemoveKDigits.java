package com.baeldung.algorithms.largestNumberRemovingK;

import java.util.Stack;

public class LargestNumberRemoveKDigits {
    public static int findLargestNumberUsingArithmetic(int num, int k) {
        for (var j = 0; j < k; j++) {

            var result = 0;
            var i = 1;

            while (num / i > 0) {
                var temp = (num / (i * 10))
                           * i
                           + (num % i);
                i *= 10;

                result = Math.max(result, temp);
            }
            num = result;
        }

        return num;
    }

    public static int findLargestNumberUsingStack(int num, int k) {
        var numStr = Integer.toString(num);
        var length = numStr.length();

        if (k == length) {
            return 0;
        }

        var stack = new Stack<Character>();

        for (var i = 0; i < length; i++) {
            var digit = numStr.charAt(i);

            while (k > 0 && !stack.isEmpty() && stack.peek() < digit) {
                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        var result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        return Integer.parseInt(result.toString());
    }
}
