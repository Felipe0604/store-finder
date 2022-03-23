package com.jumbo.store.finder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.model.StoreWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResourceUtilTest {

    @Test
    void getFileResourceContentTest()throws JsonProcessingException {
        String content = ResourceUtil.getFileResourceContent("/file/test-data.json");
        StoreWrapper storeWrapper = new ObjectMapper().readValue(content, StoreWrapper.class);
        Assertions.assertNotNull(storeWrapper);
        Assertions.assertNotNull(storeWrapper.getStores());
        Assertions.assertEquals(storeWrapper.getStores().size(), 3);
    }

    @Test
    void getFileResourceContentWithNoFileTest() {
        String content = ResourceUtil.getFileResourceContent("/file/no-file.json");
        Assertions.assertNull(content);
    }
}
