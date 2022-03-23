package com.jumbo.store.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Store Wrapper model
 * Only for file reader
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreWrapper {
    List<Store> stores;
}
