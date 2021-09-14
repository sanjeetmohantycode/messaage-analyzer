package com.iot.meter.analyzer.domain;

import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

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
public class OrgIOTMessage implements Comparable<OrgIOTMessage>{

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

    private MessageSource messageSource;

    private ZonedDateTime messageProcessingStartTime;
    private ZonedDateTime messageProcessingEndTime;

    @Builder.Default
    private boolean processed = false;

    private String orgId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrgIOTMessage that = (OrgIOTMessage) o;
        return messageCount == that.messageCount && meterReading == that.meterReading && cableStatus == that.cableStatus && Float.compare(that.remainingBattery, remainingBattery) == 0 && Objects.equals(id, that.id) && deviceType == that.deviceType && Objects.equals(imei, that.imei) && Objects.equals(messageTimeStamp, that.messageTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceType, imei, messageCount, messageTimeStamp, meterReading, cableStatus, remainingBattery);
    }

    @Override
    public int compareTo(OrgIOTMessage orgIOTMessage) {
        if(orgIOTMessage.getMessageTimeStamp().isAfter(this.getMessageTimeStamp())) {
            return 1;
        } else {
            return -1;
        }
    }
}
