package com.jumbo.store.finder.strategy;

import com.jumbo.store.finder.exception.EmptyDataBaseException;
import com.jumbo.store.finder.exception.NoDataFoundException;
import com.jumbo.store.finder.model.Store;

import java.util.List;

/**
 * Main Finder strategy Class
 *
 * @author Felipe Gonzalez
 */
public class Finder {

    private SearchStrategy strategy;

    public Finder(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get Store list depending on the strategy
     *
     * @param stores List of stores from repository
     * @return Filtered Store list
     */
    public List<Store> getStores(List<Store> stores) {

        // Verify data from repository
        if(stores == null || stores.isEmpty()){
            throw new EmptyDataBaseException("Connection problem or empty repository");
        }

        List<Store> result = this.strategy.getStores(stores);

        // Verify filtered store list
        if(result == null || result.isEmpty()){
            throw new NoDataFoundException("No Stores available");
        }

        return result;
    }
}