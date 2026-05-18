package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R9InitialJson;

@Repository
public interface R9InitialJsonRepository extends JpaRepository<R9InitialJson, Long> {

	boolean existsByDt(Date date);

}
