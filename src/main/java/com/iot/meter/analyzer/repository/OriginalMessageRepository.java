package com.iot.meter.analyzer.repository;

import com.iot.meter.analyzer.domain.OrgIOTMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginalMessageRepository extends JpaRepository<OrgIOTMessage, String> {
}
