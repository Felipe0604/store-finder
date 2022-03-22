package com.jumbo.store.finder.strategy.impl;

import com.jumbo.store.finder.model.Pair;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.strategy.SearchStrategy;
import com.jumbo.store.finder.util.DistanceCalculatorUtil;

import java.util.*;
import java.util.stream.Collectors;

public class CloserStoresStrategy implements SearchStrategy {

    private final Double longitude;
    private final Double latitude;
    private final Integer numberOfStores;
    private final PriorityQueue<Pair<Double, Store>> sortedResult;
    private final Double sphereRadius;

    public CloserStoresStrategy(Double longitude,
                                Double latitude,
                                Integer numberOfStores){
        this.sortedResult = new PriorityQueue<>(Collections.reverseOrder());
        this.longitude = longitude;
        this.latitude = latitude;
        this.numberOfStores = numberOfStores;
        this.sphereRadius = 6371.0; // Earth Radius
    }

    public List<Store> getStores(List<Store> stores){
        Double lonStore;
        Double latStore;
        Double distance;
        Pair<Double, Store> storeDistance;

        for(Store store : stores){
            lonStore = store.getLongitude();
            latStore = store.getLatitude();
            distance = DistanceCalculatorUtil.getHaversineDistance(lonStore, latStore,
                    this.longitude, this.latitude, this.sphereRadius);
            storeDistance = new Pair<>(distance, store);

            if(this.sortedResult.size() < this.numberOfStores){
                this.sortedResult.offer(storeDistance);
            } else if(!this.sortedResult.isEmpty() && this.sortedResult.peek().getKey() > distance) {
                this.sortedResult.poll();
                this.sortedResult.offer(storeDistance);
            }
        }
        return this.sortedResult.stream().map(Pair::getValue).collect(Collectors.toList());
    }
}
