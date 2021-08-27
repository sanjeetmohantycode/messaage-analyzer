package com.iot.meter.analyzer.analytics;

import com.iot.meter.analyzer.dto.response.MeterUsageResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface AnalyticsService {
    //OwnerId and OrgId will be same for retail users.
    //For large organizations also orgId remains same.
    Mono<MeterUsageResponse> findCurrentMonthUsageBasedOnOrgId(String orgId);

    Mono<MeterUsageResponse> findLastSixMonthUsageBasedOnOrgId(String orgId);

}
