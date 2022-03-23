package com.jumbo.store.finder.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
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
     * Get file content from resources folder
     *
     * @param pathFile File Path
     * @return File Content
     */
    public static String getFileResourceContent(String pathFile) {
        try{
            // Get Resource content
            URL url = ResourceUtil.class.getResource(pathFile);

            // Validate if the file exists
            if(url != null){
                Path path = Paths.get(url.toURI());
                return Files.readString(path, StandardCharsets.UTF_8);
            } else {
                throw new FileNotFoundException();
            }
        }catch (URISyntaxException | IOException e){
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
