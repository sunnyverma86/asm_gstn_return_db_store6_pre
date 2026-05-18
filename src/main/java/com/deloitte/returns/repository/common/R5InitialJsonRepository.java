package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R5InitialJson;

@Repository
public interface R5InitialJsonRepository extends JpaRepository<R5InitialJson, Long> {

	boolean existsByDt(Date date);

}
