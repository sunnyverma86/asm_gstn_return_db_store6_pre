package com.deloitte.returns.repository.log;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.R98aInitialJson;

@Repository
public interface R98aInitialJsonRepository extends JpaRepository<R98aInitialJson, Long> {

	boolean existsByDt(Date date);

}
