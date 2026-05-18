package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Cmp08InitialJson;

@Repository
public interface Cmp08InitialJsonRepository extends JpaRepository<Cmp08InitialJson, Long> {

	boolean existsByDt(Date date);

}
