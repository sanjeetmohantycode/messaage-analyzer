package com.iot.meter.analyzer.dto.response;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeterUsage {
    String imeiNumber;
    BigDecimal unitsConsumedInCurrentCycle;
}
