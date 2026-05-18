package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R10InitialJson;

@Repository
public interface R10InitialJsonRepository extends JpaRepository<R10InitialJson, Long> {

	boolean existsByDt(Date date);

}
