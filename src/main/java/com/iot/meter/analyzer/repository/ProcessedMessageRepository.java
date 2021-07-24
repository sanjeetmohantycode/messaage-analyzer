package com.iot.meter.analyzer.repository;

import com.iot.meter.analyzer.domain.ProcessedMessage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProcessedMessageRepository extends ReactiveCrudRepository<ProcessedMessage, String> {

    Mono<ProcessedMessage> findByImei(String s);
}
