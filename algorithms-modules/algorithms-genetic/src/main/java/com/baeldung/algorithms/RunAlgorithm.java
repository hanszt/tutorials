package com.baeldung.algorithms;

import com.baeldung.algorithms.ga.annealing.City;
import com.baeldung.algorithms.ga.annealing.SimulatedAnnealing;
import com.baeldung.algorithms.ga.annealing.Travel;
import com.baeldung.algorithms.ga.ant_colony.AntColonyOptimization;
import com.baeldung.algorithms.ga.binary.SimpleGeneticAlgorithm;

import java.util.Random;
import java.util.Scanner;

public final class RunAlgorithm {

	public static final Random RANDOM_GENERATOR = new Random();

	public static void main(String[] args) {
		try (var in = new Scanner(System.in)) {
			System.out.println("Run algorithm:");
			System.out.println("1 - Simulated Annealing");
			System.out.println("2 - Simple Genetic Algorithm");
			System.out.println("3 - Ant Colony");
            var decision = in.nextInt();
			switch (decision) {
				case 1:
					final var startingTemperature = 10;
					final var numberOfIterations = 10000;
					final var coolingRate = 0.9995;
					final var travel = new Travel(10, i -> {
						final var x = RANDOM_GENERATOR.nextInt(500);
						final var y = RANDOM_GENERATOR.nextInt(500);
						return new City(x, y);
					});
					final var annealing = new SimulatedAnnealing(RANDOM_GENERATOR, travel)
							.simulateAnnealing(startingTemperature, numberOfIterations, coolingRate);
					System.out.println("Optimized distance for travel: " + annealing);
					break;
				case 2:
					final var target = "1011000100000100010000100000100111001000000100000100000000001111";
                    var ga = new SimpleGeneticAlgorithm(RANDOM_GENERATOR, target);
					final var result = ga.runAlgorithm(50);
					System.out.println(String.join("\n", result));
					break;
				case 3:
					final var nrOfCities = 21;
                    var antColony = new AntColonyOptimization(nrOfCities, RANDOM_GENERATOR);
					antColony.startAntOptimization();
					break;
				default:
					System.out.println("Unknown option");
					break;
			}
		}
	}
}
