package com.iot.meter.analyzer.repository;

import com.iot.meter.analyzer.domain.DailyConsumption;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyConsumptionRepository extends JpaRepository<DailyConsumption, String> {

    Optional<DailyConsumption> findByImei(String imei);

    Optional<List<DailyConsumption>> findByOrgId(String orgId);
}
