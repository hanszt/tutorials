package com.baeldung.algorithms.mergeintervals;

import static java.lang.Integer.max;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeOverlappingIntervals {

    public List<Interval> doMerge(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(interval -> interval.start));
        var merged = new ArrayList<Interval>();
        merged.add(intervals.get(0));

        intervals.forEach(interval -> {
            var lastMerged = merged.get(merged.size() - 1);
            if (interval.start <= lastMerged.end){
                lastMerged.setEnd(max(interval.end, lastMerged.end));
            } else {
                merged.add(interval);
            }
        });

        return merged;
    }
}