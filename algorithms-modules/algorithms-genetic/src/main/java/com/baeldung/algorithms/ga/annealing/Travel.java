package com.baeldung.algorithms.ga.annealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public final class Travel {

    private List<City> travel = new ArrayList<>();
    private List<City> previousTravel = new ArrayList<>();

    public Travel(int numberOfCities, IntFunction<City> generateCity) {
        for (var i = 0; i < numberOfCities; i++) {
            travel.add(generateCity.apply(i));
        }
    }

    public void swapCities(RandomGenerator randomGenerator) {
        var a = randomGenerator.nextInt(travel.size());
        var b = randomGenerator.nextInt(travel.size());
        previousTravel = new ArrayList<>(travel);
        var x = travel.get(a);
        var y = travel.get(b);
        travel.set(a, y);
        travel.set(b, x);
    }

    public void revertSwap() {
        travel = previousTravel;
    }

    public City getCity(int index) {
        return travel.get(index);
    }

    public int getDistance() {
        return IntStream.range(0, travel.size())
                .map(this::getDistance)
                .sum();
    }

    private int getDistance(int index) {
        final var start = getCity(index);
        final var destination = index + 1 < travel.size() ? getCity(index + 1) : getCity(0);
        return (int) start.distanceToCity(destination);
    }

}
