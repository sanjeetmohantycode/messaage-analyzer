package com.iot.meter.analyzer.analytics;

import com.iot.meter.analyzer.dto.response.MeterUsageResponse;
import com.iot.meter.analyzer.repository.DailyConsumptionRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    DailyConsumptionRepository dailyConsumptionRepository;


    @Override
    public Optional<MeterUsageResponse> findCurrentMonthUsageBasedOnOrgId(String orgId) {
        return Optional.empty();
    }

    @Override
    public Optional<MeterUsageResponse> findLastSixMonthUsageBasedOnOrgId(String orgId) {
        return Optional.empty();
    }
}
