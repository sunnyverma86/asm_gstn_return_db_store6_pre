package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.EwbPartaInitialJson;

@Repository
public interface EwbPartaInitialJsonRepository extends JpaRepository<EwbPartaInitialJson, Long> {
}
