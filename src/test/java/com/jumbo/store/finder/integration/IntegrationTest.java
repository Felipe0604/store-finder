package com.jumbo.store.finder.integration;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;


   @MockBean
    private StoreRepository storeRepository;

    @BeforeEach
    void setup() throws IOException {
        String content = ResourceUtil.getFileResourceContent("/file/stores-test.json");
        StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
        when(this.storeRepository.findAll()).thenReturn(storeWrapper.getStores());
    }


    @Test
    @DisplayName("Integration Test: Get the closest stores from test file")
    void getStores() throws Exception{

        Double longitude = -70.594305;
        Double latitude = -33.4698388;

        MvcResult result = this.mockMvc.perform(
                get("/api/v1/store/finder/?longitude={longitude}&latitude={latitude}&stores=3",
                        longitude, latitude )).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        List<Store> responseStores = new ObjectMapper().readValue(content, new TypeReference<List<Store>>(){});

        Assertions.assertEquals(3, responseStores.size());
        Assertions.assertTrue(responseStores.stream().filter(sto -> sto.getCity().equals("Test1")).findFirst().isPresent());
        Assertions.assertTrue(responseStores.stream().filter(sto -> sto.getCity().equals("Test5")).findFirst().isPresent());
        Assertions.assertTrue(responseStores.stream().filter(sto -> sto.getCity().equals("Test6")).findFirst().isPresent());
    }

}
