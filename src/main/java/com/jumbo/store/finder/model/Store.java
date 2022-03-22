package com.jumbo.store.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Store Model
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {
    private String city;
    private String postalCode;
    private String street;
    private String street2;
    private String street3;
    private String addressName;
    private String uuid;
    private Double longitude;
    private Double latitude;
    private Integer complexNumber;
    private Boolean showWarningMessage;
    private String todayOpen;
    private String locationType;
    private Boolean collectionPoint;
    private Integer sapStoreID;
    private String todayClose;
}
