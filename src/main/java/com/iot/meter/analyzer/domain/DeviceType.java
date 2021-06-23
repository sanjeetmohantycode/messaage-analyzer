package com.iot.meter.analyzer.domain;

public enum DeviceType {

    WM("WaterMeter"),
    EM("ElectricMeter"),
    GM("GasMeter");

    /**
     *  Type of devices which is currently supported by system.
     */
    @lombok.Getter
    private final String deviceType;

    DeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

}
