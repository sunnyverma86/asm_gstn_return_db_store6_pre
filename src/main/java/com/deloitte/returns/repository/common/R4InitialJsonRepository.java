package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R4InitialJson;

@Repository
public interface R4InitialJsonRepository extends JpaRepository<R4InitialJson, Long> {

	boolean existsByDt(Date date);

}
