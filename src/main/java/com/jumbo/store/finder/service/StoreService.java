package com.jumbo.store.finder.service;

import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.repository.impl.StoreRepository;
import com.jumbo.store.finder.strategy.impl.ClosestStoresStrategy;
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
     * Get the closest stores to point
     *
     * @param longitude Point Longitude
     * @param latitude Point Latitude
     * @param numberOfStores Number of stores to search
     * @return List of Stores
     */
    public List<Store> getClosestStores(Double longitude,
                                        Double latitude,
                                        Integer numberOfStores){
        log.info("Get the closest stores");

        // Build Strategy
        SearchStrategy strategy = new ClosestStoresStrategy(longitude, latitude, numberOfStores);

        // Run Strategy
        List<Store> stores = this.storeRepository.findAll();

        return new Finder(strategy).getStores(stores);
    }

    /**
     * Get store by latitude, longitude, or all
     *
     * @param longitude Longitude from point (Optional)
     * @param latitude Latitude from point (Optional)
     * @return List of Stores
     */
    public List<Store> getStores(Double longitude, Double latitude){
        log.info("Get stores");

        // Build Strategy
        SearchStrategy strategy = new ExactStoreStrategy(longitude, latitude);

        // Run Strategy
        List<Store> stores = this.storeRepository.findAll();
        return new Finder(strategy).getStores(stores);
    }

}
