package com.iot.meter.analyzer.dto.response;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeterUsageResponse {

    String orgId;
    List<MeterUsage> meterUsageList;
    ZonedDateTime startDateOfCycle;
}
