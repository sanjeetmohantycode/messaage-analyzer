package com.iot.meter.analyzer.components;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class OrganizationImeiMapService {

    /**
     * Below method loads data from device manage component
     * It maintains the map of Imei number and Organization Id.
     */
    @PostConstruct
    public void loadDataFromDeviceManger() {
        //TODO: rest-template to consume data from device manager
        // Maintain the data for each service and it should load on startup
        // Also update the map when a new record comes in or a record get delete
        // .
    }
}
