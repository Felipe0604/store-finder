package com.jumbo.store.finder.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceCalculatorUtilTest {

    @Test
    void getHaversineDistanceTest() {
        Double longitude1 = 4.762433;
        Double latitude1 = 52.264417;

        Double longitude2 = 6.245829;
        Double latitude2 = 51.874272;

        Double earthRadius = 6371.0;
        Double distances1 = DistanceCalculatorUtil.getHaversineDistance(longitude1, latitude1,
                                                                       longitude2, latitude2, earthRadius);

        Double distances2 = DistanceCalculatorUtil.getHaversineDistance(longitude2, latitude2,
                                                                        longitude1, latitude1, earthRadius);

        Assertions.assertEquals(Math.round(110.28),Math.round(distances1));
        Assertions.assertEquals(distances2,distances1);
    }

    @Test
    void getHaversineDistanceNegativeLatitudeTest() {
        Double longitude1 = 40.689202777778;
        Double latitude1 = -74.044219444444;

        Double longitude2 = 38.889069444444;
        Double latitude2 = -77.034502777778;

        Double earthRadius = 6371.0;
        Double distances1 = DistanceCalculatorUtil.getHaversineDistance(longitude1, latitude1,
                longitude2, latitude2, earthRadius);

        Double distances2 = DistanceCalculatorUtil.getHaversineDistance(longitude2, latitude2,
                longitude1, latitude1, earthRadius);

        Assertions.assertEquals(Math.round(336.18),Math.round(distances1));
        Assertions.assertEquals(distances2,distances1);
    }

    @Test
    void getHaversineDistanceNegativeLongitudeTest() {
        Double longitude1 = -74.044219444444;
        Double latitude1 = 40.689202777778;

        Double longitude2 = -77.034502777778;
        Double latitude2 = 38.889069444444;

        Double earthRadius = 6371.0;
        Double distances1 = DistanceCalculatorUtil.getHaversineDistance(longitude1, latitude1,
                longitude2, latitude2, earthRadius);

        Double distances2 = DistanceCalculatorUtil.getHaversineDistance(longitude2, latitude2,
                longitude1, latitude1, earthRadius);

        Assertions.assertEquals(Math.round(324.53),Math.round(distances1));
        Assertions.assertEquals(distances2,distances1);
    }

    @Test
    void getHaversineDistanceSamePointTest() {
        Double longitude1 = 4.762433;
        Double latitude1 = 52.264417;

        Double longitude2 = 4.762433;
        Double latitude2 = 52.264417;

        Double earthRadius = 6371.0;
        Double distances1 = DistanceCalculatorUtil.getHaversineDistance(longitude1, latitude1,
                longitude2, latitude2, earthRadius);

        Double distances2 = DistanceCalculatorUtil.getHaversineDistance(longitude2, latitude2,
                longitude1, latitude1, earthRadius);

        Assertions.assertEquals(Math.round(0),Math.round(distances1));
        Assertions.assertEquals(distances2,distances1);
    }

    @Test
    void getHaversineDistanceSpecificCaseTest() {
        Double longitude1 = 0.0;
        Double latitude1 = 0.0;

        Double longitude2 = 180.0;
        Double latitude2 = 90.0;

        Double earthRadius = 6371.0;
        Double distances1 = DistanceCalculatorUtil.getHaversineDistance(longitude1, latitude1,
                longitude2, latitude2, earthRadius);

        Double distances2 = DistanceCalculatorUtil.getHaversineDistance(longitude2, latitude2,
                longitude1, latitude1, earthRadius);

        Assertions.assertEquals(Math.round(10008),Math.round(distances1));
        Assertions.assertEquals(distances2,distances1);
    }
}
