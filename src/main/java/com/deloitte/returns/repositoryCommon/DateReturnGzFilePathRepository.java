package com.deloitte.returns.repositoryCommon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.common.entity.DateReturnGzFilePath;

@Repository
public interface DateReturnGzFilePathRepository extends JpaRepository<DateReturnGzFilePath, Long> {

	List<DateReturnGzFilePath> findTop10000ByApplicationAndIsProcessedFalseOrderByIdAsc(String application);

	long countByApplicationAndIsProcessedFalse(String application);

	List<DateReturnGzFilePath> findTop100ByApplicationAndIsProcessedFalseOrderByIdAsc(String application);

}
