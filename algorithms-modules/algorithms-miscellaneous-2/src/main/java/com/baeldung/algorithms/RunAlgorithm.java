package com.baeldung.algorithms;

import java.util.Random;
import java.util.Scanner;

import com.baeldung.algorithms.slope_one.SlopeOne;

public final class RunAlgorithm {

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            System.out.println("1 - Slope One");
            System.out.println("2 - Dijkstra");
            var decision = in.nextInt();
            final var random = new Random();
            switch (decision) {
                case 1 -> SlopeOne.slopeOne(3, random);
                case 2 -> System.out.println("Please run the DijkstraAlgorithmLongRunningUnitTest.");
                default -> System.out.println("Unknown option");
            }
        }
    }
}
