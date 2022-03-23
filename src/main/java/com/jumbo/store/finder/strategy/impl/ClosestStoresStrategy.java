package com.jumbo.store.finder.strategy.impl;

import com.jumbo.store.finder.model.Pair;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.strategy.SearchStrategy;
import com.jumbo.store.finder.util.DistanceCalculatorUtil;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Get the closest stores to point (Strategy)
 *
 * @version 1.0
 * @author Felipe Gonzalez
 */
public class ClosestStoresStrategy implements SearchStrategy {

    private final Double longitude;
    private final Double latitude;
    private final Integer numberOfStores;

    private final PriorityQueue<Pair<Double, Store>> sortedResult;
    private final Double sphereRadius;

    public ClosestStoresStrategy(Double longitude,
                                 Double latitude,
                                 Integer numberOfStores){
        this.longitude = longitude;
        this.latitude = latitude;
        this.numberOfStores = numberOfStores;

        // Init queue in reverse order in order to get the biggest value in the first position.
        this.sortedResult = new PriorityQueue<>(Collections.reverseOrder());

        // Earth Radius
        this.sphereRadius = 6371.0;
    }

    /**
     * Get Stores (Override strategy action)
     *
     * @param stores List of stores from repository
     * @return Filtered list of stores
     */
    public List<Store> getStores(List<Store> stores){
        Double lonStore;
        Double latStore;
        Double distance;
        Pair<Double, Store> storeDistance;

        for(Store store : stores){
            lonStore = store.getLongitude();
            latStore = store.getLatitude();

            // Data Ignored
            if(lonStore == null || latStore == null){
                continue;
            }

            // Get distance from Haversine formula
            distance = DistanceCalculatorUtil.getHaversineDistance(lonStore, latStore,
                    this.longitude, this.latitude, this.sphereRadius);

            // Generate Pair with distance (key) and store (Value)
            storeDistance = new Pair<>(distance, store);


            if(this.sortedResult.size() < this.numberOfStores){
                // Add stores until have a complete list (Depending on numberOfStores)
                this.sortedResult.offer(storeDistance);
            } else if(!this.sortedResult.isEmpty() && this.sortedResult.peek().getKey() > distance) {
                // Replace the store with the largest distance
                this.sortedResult.poll();
                this.sortedResult.offer(storeDistance);
            }
        }

        // Convert sorted queue to list
        return this.sortedResult.stream().map(Pair::getValue).collect(Collectors.toList());
    }
}
