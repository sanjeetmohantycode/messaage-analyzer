package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.domain.DailyConsumption;
import com.iot.meter.analyzer.dto.IncomingIOTMessage;
import com.iot.meter.analyzer.repository.DailyConsumptionRepository;
import com.iot.meter.analyzer.service.IOTMessageProcessService;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Slf4j
public class UnitsCalculatorService implements IOTMessageProcessService {

    @Autowired
    DailyConsumptionRepository dailyConsumptionRepository;


    @Override
    public void processIOTMessage(IncomingIOTMessage message) {

        dailyConsumptionRepository.findByImei(message.getImei())
                .ifPresentOrElse(record -> updateMeterReadingAndCalculateUnits(record,message),
                        () -> new RuntimeException("Remove this, this just a placeholder"));

    }

    private void updateMeterReadingAndCalculateUnits(DailyConsumption dailyConsumption, IncomingIOTMessage message) {

        //If message is invalid, calculation will be skipped.
        if (!validateMessage(dailyConsumption, message)) return;

        BigDecimal existingUnitsConsumed = dailyConsumption.getUnitsConsumedTillDate();
        BigDecimal updatedUnitsConsumedTillDate =
                existingUnitsConsumed.add(new BigDecimal((message.getMeterReading() - dailyConsumption.getMeterReading())));
        dailyConsumption.setUnitsConsumedTillDate(updatedUnitsConsumedTillDate);
    }

    /**
     * Validate messages in db.
     *
     * @param existingRecordInDb record present in DB
     * @param message IOTMessage to be validated
     * @return true if message is valid or else false.
     */
    private boolean validateMessage(DailyConsumption existingRecordInDb, IncomingIOTMessage message) {

        boolean isMessageValid = validateIfMessageIsOldOrDuplicate(existingRecordInDb, message);

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
    private boolean validateIfMeterReadingIsGood(DailyConsumption existingRecordInDb, IncomingIOTMessage message) {
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
    private boolean validateIfMessageIsOldOrDuplicate(DailyConsumption existingRecordInDB, IncomingIOTMessage message) {
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
