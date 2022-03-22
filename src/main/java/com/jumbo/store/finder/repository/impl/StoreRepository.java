package com.jumbo.store.finder.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.model.StoreWrapper;
import com.jumbo.store.finder.repository.IStoreRepository;
import com.jumbo.store.finder.util.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class StoreRepository implements IStoreRepository {

    public List<Store> findStores(){

       try{
           String content = ResourceUtil.getFileResourceContent("/file/stores.json");
           StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
           return storeWrapper.getStores();
       }catch (JsonProcessingException e){
           log.error(e.getMessage(), e);
       }

        return new ArrayList<>();
    }
}