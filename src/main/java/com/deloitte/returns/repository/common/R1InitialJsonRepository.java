package com.deloitte.returns.repository.common;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.BaseJsonEntity;
import com.deloitte.returns.entity.R1InitialJson;

@Repository
public interface R1InitialJsonRepository extends JpaRepository<R1InitialJson, Long> {

	boolean existsByDt(Date date);

	//List<? extends BaseJsonEntity> findByTop30IsProcessIsFalseOrderByIdDesc();

}
