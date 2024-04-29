package com.baeldung.algorithms.ga.annealing;

public record City(int x, int y) {

    public double distanceToCity(City city) {
        int x = Math.abs(this.x - city.x);
        int y = Math.abs(this.y - city.y);
        return Math.sqrt(x * x + y * y);
    }

}
