package com.iot.meter.analyzer.service;

public interface OrganizationService {

    /**
     * Service gets the Organization ID from organization component.
     *
     * @param imei : Unique imei number for the organization.
     */
    public String getOrganization(String imei);
}
