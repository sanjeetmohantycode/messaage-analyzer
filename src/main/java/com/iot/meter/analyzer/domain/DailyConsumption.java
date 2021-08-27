package com.iot.meter.analyzer.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class DailyConsumption {

    @Id
    String messageId;
    private DeviceType deviceType;
    private String imei;
    private long messageCount;
    private ZonedDateTime messageTimeStamp;
    private Long meterReading;

    private String orgId;

    private BigDecimal unitsConsumedTillDate;
    private ZonedDateTime inceptionDate;

    private BigDecimal dailyUnitsConsumed;
    private ZonedDateTime startDateOfCycle;

    private String freeText1;
    private String freeText2;
    private String freeText3;
    private String freeText4;
    private String freeText5;

    private Boolean freeBoolean1;
    private Boolean freeBoolean2;
    private Boolean freeBoolean3;
    private Boolean freeBoolean4;
    private Boolean freeBoolean5;

    private BigDecimal freeBigDecimal1;
    private BigDecimal freeBigDecimal2;
    private BigDecimal freeBigDecimal3;
    private BigDecimal freeBigDecimal4;
    private BigDecimal freeBigDecimal5;
}
