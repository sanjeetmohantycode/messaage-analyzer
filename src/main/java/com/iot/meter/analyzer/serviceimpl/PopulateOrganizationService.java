package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.domain.ProcessedMessage;
import com.iot.meter.analyzer.dto.IncomingIOTMessage;
import com.iot.meter.analyzer.service.IOTMessageProcessService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Class populates organization info into the processed message.
 * IMEI number is used to get organization Information.
 */
@Component
@Order(2)
public class PopulateOrganizationService implements IOTMessageProcessService {

    @Override
    public void processIOTMessage(IncomingIOTMessage message, ProcessedMessage processedMessage) {
        String orgId = getOrganizationIdFromImei(message.getImei());
        processedMessage.setOrgId(orgId);
    }

    //TODO: implement organization implementation
    private String getOrganizationIdFromImei(String imei) {
        return "testOrganization";
    }
}
