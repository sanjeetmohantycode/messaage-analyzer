package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.domain.DeviceType;
import com.iot.meter.analyzer.domain.OriginalMessages;
import com.iot.meter.analyzer.dto.IncomingMessageForMeterDTO;
import com.iot.meter.analyzer.repository.OriginalMessageRepository;
import com.iot.meter.analyzer.service.MeterMessageProcessService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OriginalMessageProcessorServiceImpl implements MeterMessageProcessService {

    private final OriginalMessageRepository originalMessageRepository;

    @Autowired
    public OriginalMessageProcessorServiceImpl(OriginalMessageRepository originalMessageRepository) {
        this.originalMessageRepository = originalMessageRepository;
    }


    @Override
    public void processOriginalMessage(IncomingMessageForMeterDTO message) {

        OriginalMessages originalMessages = new OriginalMessages();
        originalMessages.setId(UUID.randomUUID().toString());
        originalMessages.setMessageCount(message.getMessageCount());
        originalMessages.setMessageTimeStamp(message.getMessageTimeStamp());
        originalMessages.setImei(message.getImei());
        originalMessages.setCableStatus(message.isCableStatus());
        originalMessages.setDeviceType(DeviceType.valueOf(message.getDeviceType()));
        originalMessages.setRemainingBattery(message.getRemainingBattery());

        originalMessageRepository.save(originalMessages);
    }
}
