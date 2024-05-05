package com.baeldung.algorithms.slope_one;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Slope One algorithm implementation
 */
public final class SlopeOne {

    private static final Map<Item, Map<Item, Double>> diff = new HashMap<>();
    private static final Map<Item, Map<Item, Integer>> freq = new HashMap<>();
    private static final Map<User, Map<Item, Double>> outputData = new HashMap<>();

    public static void slopeOne(int numberOfUsers, Random random) {
        var inputData = InputData.initializeData(numberOfUsers, random);
        System.out.println("Slope One - Before the Prediction\n");
        buildDifferencesMatrix(inputData);
        System.out.println("\nSlope One - With Predictions\n");
        predict(inputData);
    }

    /**
     * Based on the available data, calculate the relationships between the
     * items and number of occurrences
     *
     * @param data existing user data and their items' ratings
     */
    private static void buildDifferencesMatrix(Map<User, Map<Item, Double>> data) {
        for (final var user : data.values()) {
            for (var e : user.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<>());
                    freq.put(e.getKey(), new HashMap<>());
                }
                for (var e2 : user.entrySet()) {
                    var oldCount = 0;
                    if (freq.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = freq.get(e.getKey()).get(e2.getKey());
                    }
                    var oldDiff = 0.0;
                    if (diff.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = diff.get(e.getKey()).get(e2.getKey());
                    }
                    var observedDiff = e.getValue() - e2.getValue();
                    freq.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    diff.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (var j : diff.keySet()) {
            for (var i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i);
                int count = freq.get(j).get(i);
                diff.get(j).put(i, oldValue / count);
            }
        }
        printData(data);
    }

    /**
     * Based on existing data predict all missing ratings. If prediction is not
     * possible, the value will be equal to -1
     *
     * @param data existing user data and their items' ratings
     */
    private static void predict(Map<User, Map<Item, Double>> data) {
        var uPred = new HashMap<Item, Double>();
        var uFreq = new HashMap<Item, Integer>();
        for (var j : diff.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (var e : data.entrySet()) {
            for (var j : e.getValue().keySet()) {
                for (var k : diff.keySet()) {
                    var predictedValue = diff.get(k).get(j) + e.getValue().get(j);
                    var finalValue = predictedValue * freq.get(k).get(j);
                    uPred.put(k, uPred.get(k) + finalValue);
                    uFreq.put(k, uFreq.get(k) + freq.get(k).get(j));
                }
            }
            var clean = new HashMap<Item, Double>();
            for (var j : uPred.keySet()) {
                if (uFreq.get(j) > 0) {
                    clean.put(j, uPred.get(j) / uFreq.get(j));
                }
            }
            for (var j : InputData.items) {
                if (e.getValue().containsKey(j)) {
                    clean.put(j, e.getValue().get(j));
                } else if (!clean.containsKey(j)) {
                    clean.put(j, -1.0);
                }
            }
            outputData.put(e.getKey(), clean);
        }
        printData(outputData);
    }

    private static void printData(Map<User, Map<Item, Double>> data) {
        for (var user : data.keySet()) {
            System.out.println(user.userName() + ":");
            print(data.get(user));
        }
    }

    private static void print(Map<Item, Double> hashMap) {
        NumberFormat formatter = new DecimalFormat("#0.000");
        for (var j : hashMap.keySet()) {
            System.out.println(" " + j.itemName() + " --> " + formatter.format(hashMap.get(j).doubleValue()));
        }
    }

}
