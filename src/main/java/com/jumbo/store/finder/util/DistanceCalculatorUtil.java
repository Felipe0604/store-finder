package com.jumbo.store.finder.util;

/**
 * Utilities for calculate distances
 *
 * @author Felipe Gonzalez
 */
public final class DistanceCalculatorUtil {

    /**
     * Haversine Distance Calculator
     * @param longitude1 First Point Latitude
     * @param latitude1 First Point Longitude
     * @param longitude2 Second Point Latitude
     * @param latitude2 Second Point Longitude
     * @param sphereRadius Sphere Radius
     * @return Distance (Depending on Radius Distance)
     */
    public static Double getHaversineDistance(Double longitude1,
                                              Double latitude1,
                                              Double longitude2,
                                              Double latitude2,
                                              Double sphereRadius){
        Double diffLatitude = degreesToRadians(latitude2-latitude1);
        Double diffLongitude = degreesToRadians(longitude2-longitude1);
        Double a = Math.sin(diffLatitude/2) * Math.sin(diffLatitude/2) +
                   Math.cos(degreesToRadians(latitude1)) *
                   Math.cos(degreesToRadians(longitude2)) *
                   Math.sin(diffLongitude/2) * Math.sin(diffLongitude/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = sphereRadius * c;
        return distance;
    }

    /**
     * Convert Degrees To Radians
     * @param degrees Input Degrees
     * @return Output Radians
     */
    public static Double degreesToRadians(Double degrees){
        return degrees * (Math.PI/180);
    }
}
