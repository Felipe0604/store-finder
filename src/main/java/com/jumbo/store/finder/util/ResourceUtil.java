package com.jumbo.store.finder.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.finder.model.StoreWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utilities for resources folder management
 *
 * @author Felipe Gonzalez
 */
@Slf4j
public final class ResourceUtil {

    /**
     * Get File Content From Resource Folder
     * @param pathFile File Path
     * @return File Content
     */
    public static String getFileResourceContent(String pathFile) {
        try{
            URL url = ResourceUtil.class.getResource(pathFile);
            Path path = Paths.get(url.toURI());
            return Files.readString(path, StandardCharsets.UTF_8);
        }catch (URISyntaxException | IOException e){
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
