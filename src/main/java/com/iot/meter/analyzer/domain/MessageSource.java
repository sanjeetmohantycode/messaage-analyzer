package com.iot.meter.analyzer.domain;

public enum MessageSource {
    MQTT_HUB("Message via MQTT"),
    REST_CALL("Message via rest");


    @lombok.Getter
    private final String messageSourceName;

    MessageSource(String messageSourceName) {
        this.messageSourceName = messageSourceName;
    }
}
