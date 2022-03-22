package com.jumbo.store.finder.strategy;

import com.jumbo.store.finder.model.Store;

import java.util.List;

/**
 * Common interface for all Finder strategies.
 *
 * @author Felipe Gonzalez
 */
public interface SearchStrategy {
    List<Store> getStores(List<Store> storeList);
}