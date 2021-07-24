package com.iot.meter.analyzer.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
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
public class ProcessedMessage {

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

    private BigDecimal monthlyUnitsConsumed;
    private ZonedDateTime monthBeginDate;

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

    BigDecimal freeBigDecimal1;
    BigDecimal freeBigDecimal2;
    BigDecimal freeBigDecimal3;
    BigDecimal freeBigDecimal4;
    BigDecimal freeBigDecimal5;
}
