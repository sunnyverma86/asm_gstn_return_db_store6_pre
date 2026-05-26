package com.deloitte.returns.repository.log;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.Itc02InitialJson;

@Repository
public interface Itc02InitialJsonRepository extends JpaRepository<Itc02InitialJson, Long> {

	boolean existsByDt(Date date);

}
