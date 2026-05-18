package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R9cInitialJson;

@Repository
public interface R9cInitialJsonRepository extends JpaRepository<R9cInitialJson, Long> {

	boolean existsByDt(Date date);

}
