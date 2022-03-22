package com.jumbo.store.finder.strategy;

import com.jumbo.store.finder.exception.EmptyDataBaseException;
import com.jumbo.store.finder.exception.NoDataFoundException;
import com.jumbo.store.finder.model.Store;

import java.util.List;

/**
 * Main strategy Class
 *
 * @author Felipe Gonzalez
 */
public class Finder {

    private SearchStrategy strategy;

    public Finder(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Store> getStores(List<Store> storeList) {

        if(storeList == null || storeList.isEmpty()){
            throw new EmptyDataBaseException("Connection problem or empty repository");
        }

        List<Store> result = this.strategy.getStores(storeList);

        if(result == null || result.isEmpty()){
            throw new NoDataFoundException("No Stores available");
        }

        return result;
    }
}