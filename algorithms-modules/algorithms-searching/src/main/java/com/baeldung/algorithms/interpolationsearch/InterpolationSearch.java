package com.baeldung.algorithms.interpolationsearch;

public class InterpolationSearch {

    public static int interpolationSearch(int[] data, int item) {

        var highEnd = (data.length - 1);
        var lowEnd = 0;

        while (item >= data[lowEnd] && item <= data[highEnd] && lowEnd <= highEnd) {

            var probe = lowEnd + (highEnd - lowEnd) * (item - data[lowEnd]) / (data[highEnd] - data[lowEnd]);

            if (highEnd == lowEnd) {
                if (data[lowEnd] == item) {
                    return lowEnd;
                } else {
                    return -1;
                }
            }

            if (data[probe] == item) {
                return probe;
            }

            if (data[probe] < item) {
                lowEnd = probe + 1;
            } else {
                highEnd = probe - 1;
            }
        }
        return -1;
    }

}
