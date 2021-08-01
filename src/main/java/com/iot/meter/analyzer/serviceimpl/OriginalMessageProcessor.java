package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.domain.DeviceType;
import com.iot.meter.analyzer.domain.OriginalMessages;
import com.iot.meter.analyzer.domain.DailyConsumption;
import com.iot.meter.analyzer.dto.IncomingIOTMessage;
import com.iot.meter.analyzer.repository.OriginalMessageRepository;
import com.iot.meter.analyzer.service.IOTMessageProcessService;
import com.iot.meter.analyzer.service.OrganizationService;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Original Message processor just stores the message received from IOT server
 * and passes the consumption object for further processing.
 */

@Component
@Order(1)
public class OriginalMessageProcessor implements IOTMessageProcessService {

    private final OriginalMessageRepository originalMessageRepository;

    private final OrganizationService organizationService;

    @Autowired
    public OriginalMessageProcessor(OriginalMessageRepository originalMessageRepository, OrganizationService organizationService) {
        this.originalMessageRepository = originalMessageRepository;
        this.organizationService = organizationService;
    }


    /**
     * Method processes all the messages from queue and create 2 messages
     * 1. Original Message: Processes the messages received from IOT
     * Adds extra fields as part of message enrichment.
     * such as : When the message was picked up for processing etc.
     *
     * @param message Incoming message, message in queue
     */

    @Override
    public void processIOTMessage(IncomingIOTMessage message) {
        OriginalMessages originalMessages = createOriginalMessage(message);
        originalMessageRepository.save(originalMessages);
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
                .messageProcessingStartTime(ZonedDateTime.now())
                .orgId(organizationService.getOrganization(message.getImei()))
                .build();
    }
}