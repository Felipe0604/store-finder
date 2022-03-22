package com.jumbo.store.finder.service;

import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.repository.impl.StoreRepository;
import com.jumbo.store.finder.strategy.impl.CloserStoresStrategy;
import com.jumbo.store.finder.strategy.Finder;
import com.jumbo.store.finder.strategy.SearchStrategy;
import com.jumbo.store.finder.strategy.impl.ExactStoreStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Store service
 *
 * @version 1.0
 * @author Felipe Gonzalez
 */
@Slf4j
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Get closer stores from point
     * @param longitude Longitude from point
     * @param latitude Latitude from point
     * @param numberOfStores Number of stores to search
     * @return List of Stores
     */
    public List<Store> getCloserStores(Double longitude,
                                       Double latitude,
                                       Integer numberOfStores){
        log.info("Get closer stores");

        SearchStrategy strategy = new CloserStoresStrategy(longitude, latitude, numberOfStores);
        List<Store> stores = this.storeRepository.findStores();
        return new Finder(strategy).getStores(stores);
    }


    /**
     * Get store by latitude, longitude, or all
     * @param longitude Longitude from point (Optional)
     * @param latitude Latitude from point (Optional)
     * @return List of Stores
     */
    public List<Store> getStores(Double longitude, Double latitude){

        log.info("Get stores");

        SearchStrategy strategy = new ExactStoreStrategy(longitude, latitude);
        List<Store> stores = this.storeRepository.findStores();
        return new Finder(strategy).getStores(stores);
    }

}
