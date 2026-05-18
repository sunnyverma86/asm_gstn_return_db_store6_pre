package com.deloitte.returns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.DownloadDocument.RegisDcupdtlsTesting;

@Repository
public interface RegisDcupdtlsRepository extends JpaRepository<RegisDcupdtlsTesting, Long> {

	List<RegisDcupdtlsTesting> findAllByIsProcessedFalseAndFoundInfoTrueOrderById();

//
	RegisDcupdtlsTesting findFirstByGstinAndJsonIdDcupdtls(String gstin, Long jsonIdDcupdtls);
}
