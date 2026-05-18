package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.DownloadDocument.RegisDcupdtlsTesting;

@Repository
public interface RegisDcupdtlsTestingRepository extends JpaRepository<RegisDcupdtlsTesting, Long> {

	// List<RegisDcupdtlsTesting> findByDistinctJsonIdDcupdtls();

	// List<RegisDcupdtlsTesting> findByJsonIdDcupdtls();

}
