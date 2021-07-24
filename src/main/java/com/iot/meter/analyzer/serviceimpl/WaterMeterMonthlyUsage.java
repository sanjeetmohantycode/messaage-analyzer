package com.iot.meter.analyzer.serviceimpl;

import com.iot.meter.analyzer.service.MonthlyUsageService;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class WaterMeterMonthlyUsage implements MonthlyUsageService {

    @Override
    public BigDecimal monthlyConsumption(String orgId, String imei) {
        return null;
    }
}
