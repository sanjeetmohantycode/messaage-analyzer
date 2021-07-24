package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.domain.ProcessedMessage;
import com.iot.meter.analyzer.dto.IncomingIOTMessage;
import com.iot.meter.analyzer.repository.ProcessedMessageRepository;
import com.iot.meter.analyzer.service.IOTMessageProcessService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Order(3)
@Slf4j
public class UnitsCalculatorService implements IOTMessageProcessService {

    @Autowired
    ProcessedMessageRepository processedMessageRepository;

    @Override
    public void processIOTMessage(IncomingIOTMessage message, ProcessedMessage newProcessedMessage) {

        // Step 1 : get record from database
        Mono<ProcessedMessage> processedMessageDB = processedMessageRepository.findByImei(message.getImei());

        // Step 2 : update the total units consumed field
        processedMessageDB.subscribe(processMessageExisting -> updateMeterReadingAndCalculateUnits(processMessageExisting, message));
    }

    private void updateMeterReadingAndCalculateUnits(ProcessedMessage existingRecordInDb, IncomingIOTMessage message) {

        //If message is invalid, calculation will be skipped.
        if (!validateMessage(existingRecordInDb, message)) return;

        BigDecimal existingUnitsConsumed = existingRecordInDb.getUnitsConsumedTillDate();
        BigDecimal updatedUnitsConsumedTillDate =
                existingUnitsConsumed.add(new BigDecimal((message.getMeterReading() - existingRecordInDb.getMeterReading())));
        existingRecordInDb.setUnitsConsumedTillDate(updatedUnitsConsumedTillDate);
    }

    /**
     * Validate messages in db.
     *
     * @param existingRecordInDb
     * @param message
     * @return true if message is valid or else false.
     */
    private boolean validateMessage(ProcessedMessage existingRecordInDb, IncomingIOTMessage message) {

        boolean isMessageValid = true;

        if (!validateIfMessageIsOldOrDuplicate(existingRecordInDb, message)) {
            isMessageValid = false;
        }
        if (!validateIfMeterReadingIsGood(existingRecordInDb, message)) {
            isMessageValid = false;
        }

        return isMessageValid;
    }

    /**
     * @param existingRecordInDb Old processed message present in database.
     * @param message            Incoming IOT message.
     * @return true if message is valid otherwise false
     */
    private boolean validateIfMeterReadingIsGood(ProcessedMessage existingRecordInDb, IncomingIOTMessage message) {
        long unitsConsumed = (message.getMeterReading() - existingRecordInDb.getMeterReading());
        if (unitsConsumed <= 0) {
            log.error("Meter Reading is not correct.");
            log.error(message.toString());
            return false;
        }
        return true;
    }

    /**
     * Method evaluates if any old dated message has arrived / if any duplicate message has arrived.
     *
     * @param existingRecordInDB Old processed message present in database.
     * @param message            Incoming IOT message.
     * @return return true if the message is valid.
     */
    private boolean validateIfMessageIsOldOrDuplicate(ProcessedMessage existingRecordInDB, IncomingIOTMessage message) {
        if (existingRecordInDB.getMessageTimeStamp().isAfter(message.getMessageTimeStamp())) {
            log.warn("old dated message has arrived, so ignoring the message from calculation");
            log.warn(message.toString());
            return false;
        }

        if (existingRecordInDB.getMessageTimeStamp().isEqual(message.getMessageTimeStamp())) {
            log.error("Processing duplicate message should be checked thoroughly.");
            log.error(message.toString());
            return false;
        }

        return true;
    }
}
