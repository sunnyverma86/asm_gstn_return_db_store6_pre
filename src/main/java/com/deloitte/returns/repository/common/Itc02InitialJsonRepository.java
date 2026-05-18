package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Itc02InitialJson;

@Repository
public interface Itc02InitialJsonRepository extends JpaRepository<Itc02InitialJson, Long> {

	boolean existsByDt(Date date);

}
