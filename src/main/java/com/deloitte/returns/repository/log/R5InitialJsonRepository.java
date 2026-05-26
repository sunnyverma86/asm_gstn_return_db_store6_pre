package com.deloitte.returns.repository.log;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.BaseJsonEntity;
import com.deloitte.returns.entity.log.R5InitialJson;

@Repository
public interface R5InitialJsonRepository extends JpaRepository<R5InitialJson, Long> {

	boolean existsByDt(Date date);

	List<? extends BaseJsonEntity> findTop30ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

}
