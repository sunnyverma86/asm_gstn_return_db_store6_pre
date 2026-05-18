package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.RentActivePpbzdtls;

@Repository
public interface RentActivePpbzdtlsRepository extends JpaRepository<RentActivePpbzdtls, Long> {

	Page<RentActivePpbzdtls> findByIsProcessedNullOrIsProcessedFalse(Pageable pageable);

}
