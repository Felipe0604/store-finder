package com.jumbo.store.finder.util;

/**
 * Utilities for calculate distances
 *
 * @author Felipe Gonzalez
 */
public final class DistanceCalculatorUtil {

    /**
     * Haversine Distance Calculator
     *
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
        // Degrees To Radians constant
        Double p = (Math.PI/180);

        // Haversine Formula
        var a = 0.5 - Math.cos((latitude2 - latitude1) * p)/2 +
                      Math.cos(latitude1 * p) * Math.cos(latitude2 * p) *
                        (1 - Math.cos((longitude2 - longitude1) * p))/2;

        // Get distance by sphere radius
        return 2 * sphereRadius * Math.asin(Math.sqrt(a));
    }

}
