package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.GstinCollectionDetails;

@Repository
public interface GstinCollectionDetailsRepository extends JpaRepository<GstinCollectionDetails, Long> {

	Page<GstinCollectionDetails> findByIsMissingTrueAndCounterAttemptLessThan(int i, Pageable pageable);

}
