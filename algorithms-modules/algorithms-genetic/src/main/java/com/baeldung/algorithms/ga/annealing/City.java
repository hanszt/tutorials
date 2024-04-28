package com.baeldung.algorithms.ga.annealing;

public final class City {

    private final int x;
    private final int y;

    public City() {
        this.x = (int) (Math.random() * 500);
        this.y = (int) (Math.random() * 500);
    }

    public double distanceToCity(City city) {
        int x = Math.abs(this.x - city.x);
        int y = Math.abs(this.y - city.y);
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

}
