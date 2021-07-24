package com.iot.meter.analyzer.service;

import com.iot.meter.analyzer.domain.ProcessedMessage;
import com.iot.meter.analyzer.dto.IncomingIOTMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Process incoming messages from IOT devices
 * persist them into DB
 * Generate alert Based on device type
 */
@Service
public interface IOTMessageProcessService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void processIOTMessage(IncomingIOTMessage message, ProcessedMessage processedMessage);
}
