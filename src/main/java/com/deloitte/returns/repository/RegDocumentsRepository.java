package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.registration.RegDocuments;

@Repository
public interface RegDocumentsRepository extends JpaRepository<RegDocuments, Long> {

	Page<RegDocuments> findByIsSuccessIsNull(Pageable pageable);

	 Page<RegDocuments> findByIsSuccessIsNullOrIsSuccessFalse(Pageable pageable);

}
