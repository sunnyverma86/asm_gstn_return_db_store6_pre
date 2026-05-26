package com.deloitte.returns.repository.log;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.BaseJsonEntity;
import com.deloitte.returns.entity.log.R10InitialJson;

@Repository
public interface R10InitialJsonRepository extends JpaRepository<R10InitialJson, Long> {

	boolean existsByDt(Date date);

	List<? extends BaseJsonEntity> findTop30ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

	List<? extends BaseJsonEntity> findTop1000ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

	List<? extends BaseJsonEntity> findTop10000ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

}
