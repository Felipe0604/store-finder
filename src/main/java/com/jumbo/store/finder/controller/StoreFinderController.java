package com.jumbo.store.finder.controller;

import com.jumbo.store.finder.model.Store;
import com.jumbo.store.finder.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Store finder controller.
 *
 * @version 1.0
 * @author Felipe Gonzalez
 */
@RestController
@RequestMapping(path = "/api/v1/stores")
public class StoreFinderController {

    private final StoreService storeService;

    public StoreFinderController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/finder/longitude/{longitude}/latitude/{latitude}")
    public ResponseEntity<List<Store>> getCloserStores(@PathVariable Double longitude,
                                                       @PathVariable Double latitude,
                                                       @RequestParam Optional<Integer> stores){

        List<Store> response = storeService.getCloserStores(longitude, latitude, stores.orElseGet(() -> 5));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping("/longitude/{longitude}/latitude/{latitude}")
    public ResponseEntity<List<Store>> getStoreByPosition(@PathVariable Double longitude,
                                                          @PathVariable Double latitude){
        List<Store> response = storeService.getStores(longitude, latitude);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping()
    public ResponseEntity<List<Store>> getStores(@RequestParam Optional<Double> longitude,
                                                 @RequestParam Optional<Double> latitude){

        List<Store> response = storeService.getStores(longitude.get(), latitude.get());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}



