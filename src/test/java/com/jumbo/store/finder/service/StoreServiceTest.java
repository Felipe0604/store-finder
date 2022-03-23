package com.jumbo.store.finder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.model.StoreWrapper;
import com.jumbo.store.finder.repository.impl.StoreRepository;
import com.jumbo.store.finder.util.ResourceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class StoreServiceTest {

    @MockBean
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;

    private List<Store> stores;

    @BeforeEach
    void setup() throws IOException {
        String content = ResourceUtil.getFileResourceContent("/file/test-data.json");
        StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
        stores = storeWrapper.getStores();
    }

    @Test
    @DisplayName("Test Get all stores")
    void getStores() {
        when(this.storeRepository.findAll()).thenReturn(this.stores);
        List<Store> result = this.storeService.getStores(null, null);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Test Get specific store")
    void getSpecificStore(){
        when(this.storeRepository.findAll()).thenReturn(this.stores);
        List<Store> result = this.storeService.getStores(4.615551, 51.778461);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Test Get specific store by latitude")
    void getSpecificStoreByLatitude() {
        when(this.storeRepository.findAll()).thenReturn(this.stores);
        List<Store> result = this.storeService.getStores(null, 51.778461);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Test Get specific store by longitude")
    void getSpecificStoreByLongitude() {
        when(this.storeRepository.findAll()).thenReturn(this.stores);
        List<Store> result = this.storeService.getStores(4.615551, null);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Test Get all the closest stores to point")
    void getClosestStoresToPointTest() {
        when(this.storeRepository.findAll()).thenReturn(this.stores);
        List<Store> result = this.storeService.getClosestStores(4.0, 51.0, 2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }
}
