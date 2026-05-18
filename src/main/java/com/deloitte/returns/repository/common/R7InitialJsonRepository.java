package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R7InitialJson;

@Repository
public interface R7InitialJsonRepository extends JpaRepository<R7InitialJson, Long> {

	boolean existsByDt(Date date);

}
