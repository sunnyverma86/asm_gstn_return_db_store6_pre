package com.deloitte.returns.repository.ledger;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registration.LedgerCommonDetails;

@Repository
public interface LedgerCommonDetailsRepository extends JpaRepository<LedgerCommonDetails, Long> {

	boolean existsByGstinAndActionAndFromDateAndToDate(String gstin, String action, LocalDate fromDate,
			LocalDate toDate);

}
