package com.iot.meter.analyzer.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public interface MonthlyUsageService {
    public BigDecimal monthlyConsumption(String orgId,String imei);
}
