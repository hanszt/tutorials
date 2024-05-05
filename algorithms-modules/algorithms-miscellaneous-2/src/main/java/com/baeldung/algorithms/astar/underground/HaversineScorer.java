package com.baeldung.algorithms.astar.underground;

import com.baeldung.algorithms.astar.Scorer;

public class HaversineScorer implements Scorer<Station> {
    @Override
    public double computeCost(Station from, Station to) {
        var R = 6372.8; // In kilometers

        var dLat = Math.toRadians(to.getLatitude() - from.getLatitude());
        var dLon = Math.toRadians(to.getLongitude() - from.getLongitude());
        var lat1 = Math.toRadians(from.getLatitude());
        var lat2 = Math.toRadians(to.getLatitude());

        var a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        var c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
