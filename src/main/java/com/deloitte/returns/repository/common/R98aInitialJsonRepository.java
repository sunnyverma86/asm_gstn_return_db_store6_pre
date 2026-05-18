package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R98aInitialJson;

@Repository
public interface R98aInitialJsonRepository extends JpaRepository<R98aInitialJson, Long> {

	boolean existsByDt(Date date);

}
