package com.jumbo.store.finder.controller;

import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

/**
 * Store finder controller.
 *
 * @version 1.0
 * @author Felipe Gonzalez
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/store")
public class StoreFinderController {

    private final StoreService storeService;

    public StoreFinderController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Get the closest stores to point.
     *
     * @param longitude Point Longitude.
     * @param latitude Point Latitude.
     * @param stores Number of stores to search.
     * @return List of Stores
     */
    @GetMapping("/finder/longitude/{longitude}/latitude/{latitude}")
    public ResponseEntity<List<Store>> getClosestStores(@PathVariable Double longitude,
                                                        @PathVariable Double latitude,
                                                        @RequestParam Optional<Integer> stores){
        log.info("Called via url /store/finder/longitude/{longitude}/latitude/{latitude}", longitude, latitude);

        List<Store> response = storeService.getClosestStores(longitude, latitude, stores.orElseGet(() -> 5));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    /**
     * Get the closest stores to point using query params.
     *
     * @param longitude Point Longitude.
     * @param latitude Point Latitude.
     * @param stores Number of stores to search.
     * @return List of Stores
     */
    @GetMapping("/finder")
    public ResponseEntity<List<Store>> getClosestStoresWithQueryParams(@RequestParam Double longitude,
                                                                       @RequestParam Double latitude,
                                                                       @RequestParam Optional<Integer> stores){
        log.info("Called via url /store/finder?longitude={longitude}&latitude={latitude}", longitude, latitude);

        List<Store> response = storeService.getClosestStores(longitude, latitude, stores.orElseGet(() -> 5));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    /**
     * Get stores by position.
     *
     * @param longitude Point Longitude.
     * @param latitude Point Latitude.
     * @return List of Stores
     */
    @GetMapping("/longitude/{longitude}/latitude/{latitude}")
    public ResponseEntity<List<Store>> getStoreByPosition(@PathVariable Double longitude,
                                                          @PathVariable Double latitude){
        log.info("Called via url /store/longitude/{longitude}/latitude/{latitude}", longitude, latitude);

        List<Store> response = storeService.getStores(longitude, latitude);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    /**
     * Get stores by position or all.
     *
     * @param longitude Point Longitude (Optional).
     * @param latitude Point Latitude (Optional).
     * @return List of Stores
     */
    @GetMapping()
    public ResponseEntity<List<Store>> getStores(@RequestParam Optional<Double> longitude,
                                                 @RequestParam Optional<Double> latitude){
        log.info("Called via url /store");

        List<Store> response = storeService.getStores(longitude.orElseGet(() -> null), latitude.orElseGet(() -> null));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}



