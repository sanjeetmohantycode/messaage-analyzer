package com.iot.meter.analyzer.domain;

public enum DeviceType {

    WM("WaterMeter"),
    EM("ElectricMeter"),
    GM("GasMeter"),
    PET_TRACKER("PetTracker"),
    VEHICLE_TRACKER("VehicleTracker");

    /**
     *  Type of devices which is currently supported by system.
     */
    @lombok.Getter
    private final String deviceTypeName;

    DeviceType(String deviceType) {
        this.deviceTypeName = deviceType;
    }

}
