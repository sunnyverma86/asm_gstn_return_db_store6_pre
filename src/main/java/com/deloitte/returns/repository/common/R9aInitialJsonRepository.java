package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R9aInitialJson;

@Repository
public interface R9aInitialJsonRepository extends JpaRepository<R9aInitialJson, Long> {

	boolean existsByDt(Date date);

}
