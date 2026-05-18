package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R1aInitialJson;

@Repository
public interface R1aInitialJsonRepository extends JpaRepository<R1aInitialJson, Long> {

	boolean existsByDt(Date date);

}
