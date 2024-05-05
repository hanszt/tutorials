package com.baeldung.algorithms.ga.annealing;

public record City(int x, int y) {

    public double distanceToCity(City city) {
        var x = Math.abs(this.x - city.x);
        var y = Math.abs(this.y - city.y);
        return Math.sqrt(x * x + y * y);
    }

}
