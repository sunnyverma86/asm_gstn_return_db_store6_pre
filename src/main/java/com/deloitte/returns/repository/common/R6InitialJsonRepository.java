package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R6InitialJson;

@Repository
public interface R6InitialJsonRepository extends JpaRepository<R6InitialJson, Long> {

	boolean existsByDt(Date date);

}
