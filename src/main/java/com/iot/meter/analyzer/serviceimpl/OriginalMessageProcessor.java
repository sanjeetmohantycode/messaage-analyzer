package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.domain.DeviceType;
import com.iot.meter.analyzer.domain.OriginalMessages;
import com.iot.meter.analyzer.domain.ProcessedMessage;
import com.iot.meter.analyzer.dto.IncomingIOTMessage;
import com.iot.meter.analyzer.repository.OriginalMessageRepository;
import com.iot.meter.analyzer.service.IOTMessageProcessService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class OriginalMessageProcessor implements IOTMessageProcessService {

    private final OriginalMessageRepository originalMessageRepository;

    @Autowired
    public OriginalMessageProcessor(OriginalMessageRepository originalMessageRepository) {
        this.originalMessageRepository = originalMessageRepository;
    }


    /**
     * Method processes all the messages from queue and create 2 messages
     * 1. Original Message
     * 2. Processes the messages and stores in processedMessage Table.
     * Adds extra fields as part of message enrichment.
     *
     * @param message Incoming message, message in queue
     */

    @Override
    public void processIOTMessage(IncomingIOTMessage message, ProcessedMessage processedMessage) {
        OriginalMessages originalMessages = createOriginalMessage(message);
        populateProcessedMessage(message, processedMessage);
        originalMessageRepository.save(originalMessages);
    }

    private void populateProcessedMessage(IncomingIOTMessage message, ProcessedMessage processedMessage) {
        processedMessage.setMessageId(UUID.randomUUID().toString());
        processedMessage.setImei(message.getImei());
        processedMessage.setMessageCount(message.getMessageCount());
        processedMessage.setMessageTimeStamp(message.getMessageTimeStamp());
        processedMessage.setDeviceType(DeviceType.valueOf(message.getDeviceType()));
    }

    private OriginalMessages createOriginalMessage(IncomingIOTMessage message) {
        return OriginalMessages.builder()
                .id(UUID.randomUUID().toString())
                .messageCount(message.getMessageCount())
                .messageTimeStamp(message.getMessageTimeStamp())
                .imei(message.getImei())
                .cableStatus(message.isCableStatus())
                .deviceType(DeviceType.valueOf(message.getDeviceType()))
                .remainingBattery(message.getRemainingBattery())
                .longitude(message.getLongitude())
                .latitude(message.getLatitude())
                .meterReading(message.getMeterReading())
                .build();
    }
}