package com.iot.meter.analyzer.domain;

import java.time.ZonedDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 *  This class is the domain model for all IOT messages,
 *  which gets stored in database for further processing.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class OriginalMessages implements Comparable<OriginalMessages>{

    @Id
    private String id;
    private DeviceType deviceType;
    private String imei;
    private long messageCount;
    private ZonedDateTime messageTimeStamp;
    private long meterReading;
    private boolean cableStatus;
    private float remainingBattery;

    private ZonedDateTime purgeDate;
    private double latitude;
    private double longitude;

    @Builder.Default
    private boolean processed = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OriginalMessages that = (OriginalMessages) o;
        return messageCount == that.messageCount && meterReading == that.meterReading && cableStatus == that.cableStatus && Float.compare(that.remainingBattery, remainingBattery) == 0 && Objects.equals(id, that.id) && deviceType == that.deviceType && Objects.equals(imei, that.imei) && Objects.equals(messageTimeStamp, that.messageTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceType, imei, messageCount, messageTimeStamp, meterReading, cableStatus, remainingBattery);
    }

    @Override
    public int compareTo(OriginalMessages originalMessages) {
        if(originalMessages.getMessageTimeStamp().isAfter(this.getMessageTimeStamp())) {
            return 1;
        } else {
            return -1;
        }
    }
}
