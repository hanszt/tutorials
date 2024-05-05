package com.baeldung.algorithms.latlondistance;

public class VincentyDistance {

    // Constants for WGS84 ellipsoid model of Earth
    private static final double SEMI_MAJOR_AXIS_MT = 6378137;
    private static final double SEMI_MINOR_AXIS_MT = 6356752.314245;
    private static final double FLATTENING = 1 / 298.257223563;
    private static final double ERROR_TOLERANCE = 1e-12;

    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        var U1 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(latitude1)));
        var U2 = Math.atan((1 - FLATTENING) * Math.tan(Math.toRadians(latitude2)));

        var sinU1 = Math.sin(U1);
        var cosU1 = Math.cos(U1);
        var sinU2 = Math.sin(U2);
        var cosU2 = Math.cos(U2);

        var longitudeDifference = Math.toRadians(longitude2 - longitude1);
        double previousLongitudeDifference;

        double sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;

        do {
            sinSigma = Math.sqrt(Math.pow(cosU2 * Math.sin(longitudeDifference), 2) +
                    Math.pow(cosU1 * sinU2 - sinU1 * cosU2 * Math.cos(longitudeDifference), 2));
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * Math.cos(longitudeDifference);
            sigma = Math.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * Math.sin(longitudeDifference) / sinSigma;
            cosSqAlpha = 1 - Math.pow(sinAlpha, 2);
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
            if (Double.isNaN(cos2SigmaM)) {
                cos2SigmaM = 0;
            }
            previousLongitudeDifference = longitudeDifference;
            var C = FLATTENING / 16 * cosSqAlpha * (4 + FLATTENING * (4 - 3 * cosSqAlpha));
            longitudeDifference = Math.toRadians(longitude2 - longitude1) + (1 - C) * FLATTENING * sinAlpha *
                    (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2))));
        } while (Math.abs(longitudeDifference - previousLongitudeDifference) > ERROR_TOLERANCE);

        var uSq = cosSqAlpha * (Math.pow(SEMI_MAJOR_AXIS_MT, 2) - Math.pow(SEMI_MINOR_AXIS_MT, 2)) / Math.pow(SEMI_MINOR_AXIS_MT, 2);

        var A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        var B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        var deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2)) -
                B / 6 * cos2SigmaM * (-3 + 4 * Math.pow(sinSigma, 2)) * (-3 + 4 * Math.pow(cos2SigmaM, 2))));

        var distanceMt = SEMI_MINOR_AXIS_MT * A * (sigma - deltaSigma);
        return distanceMt / 1000;
    }
}