package com.iot.meter.analyzer.service;

import com.iot.meter.analyzer.dto.IncomingMessageForMeterDTO;
import org.springframework.stereotype.Service;

/**
 * Process incoming messages from IOT devices
 * persist them into DB
 * Generate alert Based on device type
 */
@Service
public interface MeterMessageProcessService {
    public void processOriginalMessage(IncomingMessageForMeterDTO message);
}
