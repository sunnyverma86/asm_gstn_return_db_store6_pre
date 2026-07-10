package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.GstinCollectionDetailsOnTheFly;

@Repository
public interface GstinCollectionDetailsOnTheFlyRepository extends JpaRepository<GstinCollectionDetailsOnTheFly, Long> {

	Page<GstinCollectionDetailsOnTheFly> findByIsMissingTrueAndCounterAttemptLessThan(int i, Pageable pageable);

}
