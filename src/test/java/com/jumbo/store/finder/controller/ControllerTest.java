package com.jumbo.store.finder.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.model.StoreWrapper;
import com.jumbo.store.finder.service.StoreService;
import com.jumbo.store.finder.util.ResourceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    private List<Store> stores;

    @BeforeEach
    void setup() throws IOException {
        String content = ResourceUtil.getFileResourceContent("/file/test-data.json");
        StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
        stores = storeWrapper.getStores();
    }

    @Test
    @DisplayName("Get the closest stores using endpoint")
    void getClosestStores() throws Exception {
        Double longitude = 4.615551;
        Double latitude = 51.778461;

        when(this.storeService.getClosestStores(longitude, latitude, 5)).thenReturn(this.stores);

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store/finder/longitude/{longitude}/latitude/{latitude}",
                        longitude, latitude )).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertNotNull(responseStores);
        Assertions.assertEquals(3, responseStores.size());
    }

    @Test
    @DisplayName("Get the closest stores using endpoint with query params")
    void getClosestStoresWithQueryParams() throws Exception {
        Double longitude = 4.615551;
        Double latitude = 51.778461;

        when(this.storeService.getClosestStores(longitude, latitude, 2)).thenReturn(
                this.stores.subList(0,2)
        );

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store/finder/?longitude={longitude}&latitude={latitude}&stores=2",
                        longitude, latitude )).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertNotNull(responseStores);
        Assertions.assertEquals(2, responseStores.size());
    }

    @Test
    @DisplayName("Get one closest store using endpoint")
    void getOneClosestStore() throws Exception {
        Double longitude = 4.615551;
        Double latitude = 51.778461;

        when(this.storeService.getClosestStores(longitude, latitude, 1)).thenReturn(
                this.stores.subList(0,1)
        );

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store/finder/longitude/{longitude}/latitude/{latitude}?stores=1",
                        longitude, latitude )).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertNotNull(responseStores);
        Assertions.assertEquals(1, responseStores.size());
    }

    @Test
    @DisplayName("Get all stores using endpoint")
    void getAllStores() throws Exception {

        when(this.storeService.getStores(null, null)).thenReturn(this.stores);

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store")).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertNotNull(responseStores);
        Assertions.assertEquals(3, responseStores.size());
    }

    @Test
    @DisplayName("Get store by position using endpoint")
    void getStoreByPosition() throws Exception {

        Double longitude = 4.615551;
        Double latitude = 51.778461;

        when(this.storeService.getStores(longitude, latitude))
                .thenReturn(this.stores.subList(0,1));

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store/longitude/{longitude}/latitude/{latitude}",
                        longitude, latitude )).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertNotNull(responseStores);
        Assertions.assertEquals(1, responseStores.size());
    }

    @Test
    @DisplayName("Get store by position using endpoint with query params")
    void getStoresByPositionWithQueryParams() throws Exception {

        Double longitude = 4.615551;
        Double latitude = 51.778461;

        when(this.storeService.getStores(longitude, latitude))
                .thenReturn(this.stores.subList(0,1));

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store?longitude={longitude}&latitude={latitude}",
                        longitude, latitude )).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertNotNull(responseStores);
        Assertions.assertEquals(1, responseStores.size());
    }
}
