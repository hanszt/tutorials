package com.baeldung.algorithms.latlondistance;

public class EquirectangularApproximation {

    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        var lat1Rad = Math.toRadians(lat1);
        var lat2Rad = Math.toRadians(lat2);
        var lon1Rad = Math.toRadians(lon1);
        var lon2Rad = Math.toRadians(lon2);

        var x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        var y = (lat2Rad - lat1Rad);
        var distance = Math.sqrt(x * x + y * y) * EARTH_RADIUS;

        return distance;
    }
}