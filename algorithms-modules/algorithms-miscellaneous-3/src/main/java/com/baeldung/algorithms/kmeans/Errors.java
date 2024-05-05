package com.baeldung.algorithms.kmeans;

import java.util.List;
import java.util.Map;

/**
 * Encapsulates methods to calculates errors between centroid and the cluster members.
 */
public class Errors {

    public static double sse(Map<Centroid, List<Record>> clustered, Distance distance) {
        double sum = 0;
        for (var entry : clustered.entrySet()) {
            var centroid = entry.getKey();
            for (var record : entry.getValue()) {
                var d = distance.calculate(centroid.getCoordinates(), record.getFeatures());
                sum += Math.pow(d, 2);
            }
        }

        return sum;
    }
}
