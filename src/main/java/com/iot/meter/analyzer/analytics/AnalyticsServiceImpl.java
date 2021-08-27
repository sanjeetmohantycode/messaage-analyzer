package com.iot.meter.analyzer.analytics;

import com.iot.meter.analyzer.domain.DailyConsumption;
import com.iot.meter.analyzer.dto.response.MeterUsage;
import com.iot.meter.analyzer.dto.response.MeterUsageResponse;

import com.iot.meter.analyzer.repository.DailyConsumptionRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    DailyConsumptionRepository dailyConsumptionRepository;

    @Override
    public Mono<MeterUsageResponse> findCurrentMonthUsageBasedOnOrgId(String orgId) {

        List<MeterUsage> meterUsageList = dailyConsumptionRepository.findByOrgId(orgId)
                .map(this::generateMeterUsageResponse)
                .onErrorReturn(MeterUsage.builder().build())
                .log()
                .collectList()
                .block();

        return Mono.just(MeterUsageResponse.builder().orgId(orgId).meterUsageList(meterUsageList).build());
    }

    private MeterUsage generateMeterUsageResponse(DailyConsumption dailyConsumption) {
        return MeterUsage.builder()
                .imeiNumber(dailyConsumption.getImei())
                .unitsConsumedInCurrentCycle(dailyConsumption.getDailyUnitsConsumed())
                .startDateOfCycle(dailyConsumption.getStartDateOfCycle()).build();
    }

    @Override
    public Mono<MeterUsageResponse> findLastSixMonthUsageBasedOnOrgId(String orgId) {
        return null;
    }
}
