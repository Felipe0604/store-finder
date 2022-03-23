package com.jumbo.store.finder.strategy.impl;

import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.strategy.SearchStrategy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Get stores by position (Strategy)
 *
 * @version 1.0
 * @author Felipe Gonzalez
 */
public class ExactStoreStrategy implements SearchStrategy {

    private final Double longitude;
    private final Double latitude;

    public ExactStoreStrategy(Double longitude,
                              Double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Get Stores (Override strategy action)
     *
     * @param stores List of stores from repository
     * @return Filtered list of stores
     */
    public List<Store> getStores(List<Store> stores){
        return stores.stream().filter(store ->
                (this.longitude != null ?  Float.compare(this.getRound(store.getLongitude()), this.getRound(this.longitude)) == 0 : true) &&
                (this.latitude != null ?  Float.compare(this.getRound(store.getLatitude()), this.getRound(this.latitude)) == 0 : true))
                .collect(Collectors.toList());
    }

    /**
     * Get Round for compare close values
     * @param value Double Value
     * @return Round Value
     */
    private Long getRound(Double value){
        return Math.round(value * 10000);
    }
}
