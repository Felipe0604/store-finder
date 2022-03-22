package com.jumbo.store.finder.strategy.impl;

import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.strategy.SearchStrategy;;

import java.util.*;
import java.util.stream.Collectors;

public class ExactStoreStrategy implements SearchStrategy {

    private final Double longitude;
    private final Double latitude;


    public ExactStoreStrategy(Double longitude,
                                Double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public List<Store> getStores(List<Store> stores){
        return stores.stream().filter(store ->
                (this.longitude != null ? store.getLongitude() == this.longitude : true) &&
                (this.latitude != null ? store.getLatitude() == this.latitude : true))
                .collect(Collectors.toList());
    }
}
