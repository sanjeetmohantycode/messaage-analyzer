package com.iot.meter.analyzer.analytics;

import com.iot.meter.analyzer.dto.response.MeterUsageResponse;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface AnalyticsService {
    //OwnerId and OrgId will be same for retail users.
    //For large organizations also orgId remains same.
    Optional<MeterUsageResponse> findCurrentMonthUsageBasedOnOrgId(String orgId);

    Optional<MeterUsageResponse> findLastSixMonthUsageBasedOnOrgId(String orgId);

}
