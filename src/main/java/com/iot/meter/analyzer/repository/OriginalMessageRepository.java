package com.iot.meter.analyzer.repository;

import com.iot.meter.analyzer.domain.OriginalMessages;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginalMessageRepository extends ReactiveCrudRepository<OriginalMessages, String> {
}
