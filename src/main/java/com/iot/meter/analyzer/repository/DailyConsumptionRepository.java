package com.iot.meter.analyzer.repository;

import com.iot.meter.analyzer.domain.DailyConsumption;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DailyConsumptionRepository extends ReactiveCrudRepository<DailyConsumption, String> {

    Mono<DailyConsumption> findByImei(String s);
}
