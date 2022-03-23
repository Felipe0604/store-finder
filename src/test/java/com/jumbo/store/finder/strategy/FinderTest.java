package com.jumbo.store.finder.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.exception.EmptyDataBaseException;
import com.jumbo.store.finder.exception.NoDataFoundException;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.model.StoreWrapper;
import com.jumbo.store.finder.strategy.impl.ClosestStoresStrategy;
import com.jumbo.store.finder.strategy.impl.ExactStoreStrategy;
import com.jumbo.store.finder.util.ResourceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FinderTest {

    private List<Store> stores;

    @BeforeEach
    void setup() throws IOException {
        String content = ResourceUtil.getFileResourceContent("/file/test-data.json");
        StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
        stores = storeWrapper.getStores();
    }

    @Test
    @DisplayName("Get all stores (ExactStoreStrategy)")
    void getAllStores() {
        SearchStrategy strategy = new ExactStoreStrategy(null, null);
        List<Store> stores = new Finder(strategy).getStores(this.stores);

        Assertions.assertNotNull(stores);
        Assertions.assertEquals(3, stores.size());
    }


    @Test
    @DisplayName("Get specific store (ExactStoreStrategy)")
    void getSpecificStore() {
        SearchStrategy strategy = new ExactStoreStrategy(4.615551, 51.778461);
        List<Store> stores = new Finder(strategy).getStores(this.stores);

        Assertions.assertNotNull(stores);
        Assertions.assertEquals(1, stores.size());
    }

    @Test
    @DisplayName("Get all the closest stores to point (ExactStoreStrategy)")
    void getClosestStoresToPoint() {
        ClosestStoresStrategy strategy = new ClosestStoresStrategy(4.0, 51.0, 2);
        List<Store> stores = new Finder(strategy).getStores(this.stores);

        Assertions.assertNotNull(stores);
        Assertions.assertEquals(2, stores.size());
    }

    @Test
    @DisplayName("Throw exception when the store is not found")
    void getStoreNotFoundTest() {
        SearchStrategy strategy = new ExactStoreStrategy(-73.0, -34.0);
        NoDataFoundException exception = Assertions.assertThrows(NoDataFoundException.class, () -> {
            new Finder(strategy).getStores(this.stores);
        });
        Assertions.assertEquals("No Stores available", exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception when there is no content available")
    void getNoContentAvailableTest() {
        SearchStrategy strategy = new ExactStoreStrategy(-70.61920, -33.48641);
        EmptyDataBaseException exception = Assertions.assertThrows(EmptyDataBaseException.class, () -> {
            new Finder(strategy).getStores(new ArrayList<>());
        });
        Assertions.assertEquals("Connection problem or empty repository", exception.getMessage());
    }

}
