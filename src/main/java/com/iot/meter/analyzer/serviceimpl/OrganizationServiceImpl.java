package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.service.OrganizationService;
import org.springframework.stereotype.Component;

/**
 * Class populates organization info into the processed message.
 * IMEI number is used to get organization Information.
 */
@Component
public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public String getOrganization(String imei) {
        // TODO implement the method to get the organization from Organization component.
        // TODO Maintain hashmap which will keep all the (IMEI:Organization) in memory.
        // TODO Another method should be there which will maintain the map
        // TODO Also it should load the map on startup.
        return "testOrganization";
    }
}


