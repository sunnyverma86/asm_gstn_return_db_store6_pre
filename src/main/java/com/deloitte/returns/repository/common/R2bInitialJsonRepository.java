package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R2bInitialJson;

@Repository
public interface R2bInitialJsonRepository extends JpaRepository<R2bInitialJson, Long> {

	boolean existsByDt(Date date);

}
