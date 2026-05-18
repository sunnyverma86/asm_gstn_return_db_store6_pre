package com.deloitte.returns.repository.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.LedgerDataJsonFile;

@Repository
public interface LedgerDataJsonFileRepository extends JpaRepository<LedgerDataJsonFile, Long> {

	List<LedgerDataJsonFile> findTop100ByActionAndIsProcessedFalseOrderByIdAsc(String action);

	Optional<LedgerDataJsonFile> findByGstinAndFromDateAndToDateAndAction(String gstin, String fromDate, String toDate,
			String action);

}
