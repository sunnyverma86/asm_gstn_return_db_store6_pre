package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.R8InitialJson;

@Repository
public interface R8InitialJsonRepository extends JpaRepository<R8InitialJson, Long> {

	boolean existsByDt(Date date);

}
