package com.deloitte.returns.repository.log;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.BaseJsonEntity;
import com.deloitte.returns.entity.log.Cmp08InitialJson;

@Repository
public interface Cmp08InitialJsonRepository extends JpaRepository<Cmp08InitialJson, Long> {

	boolean existsByDt(Date date);

	List<? extends BaseJsonEntity> findTop30ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

	List<? extends BaseJsonEntity> findTop500ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

	List<? extends BaseJsonEntity> findTop2000ByIsProcessedFalseOrIsProcessedIsNullOrderByIdDesc();

}
