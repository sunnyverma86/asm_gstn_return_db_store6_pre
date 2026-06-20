package com.deloitte.returns.repository.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.DownloadDocument.DcupdtlsGstr9c;

@Repository
public interface DcupdtlsGstr9cRepository extends JpaRepository<DcupdtlsGstr9c, Long> {

	Page<DcupdtlsGstr9c> findByIsProcessedNullOrIsProcessedFalse(Pageable pageable);

}
