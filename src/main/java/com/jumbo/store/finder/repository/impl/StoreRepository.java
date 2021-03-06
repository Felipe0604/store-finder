package com.jumbo.store.finder.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.model.StoreWrapper;
import com.jumbo.store.finder.repository.FileStoreRepository;
import com.jumbo.store.finder.util.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * File Repository for Store Data
 *
 * @version 1.0
 * @author Felipe Gonzalez
 */
@Slf4j
@Repository
public class StoreRepository implements FileStoreRepository<Store> {

    public List<Store> findAll(){

       try{
           String content = ResourceUtil.getFileResourceContent( "/file/stores.json");
           if(content != null){
               StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
               return storeWrapper.getStores();
           } else {
               throw new FileNotFoundException();
           }
       }catch (IOException  e){
           log.error(e.getMessage(), e);
       }

        return new ArrayList<>();
    }
}