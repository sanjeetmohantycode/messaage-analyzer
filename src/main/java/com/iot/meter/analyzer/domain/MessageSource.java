package com.iot.meter.analyzer.domain;

public enum MessageSource {
    IOT_HUB("Message From IOT Device");

    @lombok.Getter
    private final String messageSourceName;

    MessageSource(String messageSourceName) {
        this.messageSourceName = messageSourceName;
    }
}
