package com.deloitte.returns.repository.eway;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.returns.entity.EwayGzJsonStorageView;

@Repository
public interface EwayGzJsonStorageViewRepository extends JpaRepository<EwayGzJsonStorageView, Long> {

	List<EwayGzJsonStorageView> findAllByIsProcessedFalseOrderById();

	Page<EwayGzJsonStorageView> findByIsProcessedFalseOrderById(Pageable pageable);

	long countByIsProcessedFalse();

	List<EwayGzJsonStorageView> findTop10000ByCategoryAndIsProcessedFalseOrderByIdAsc(String application);

	List<EwayGzJsonStorageView> findTop10ByCategoryAndIsProcessedFalseOrderByIdAsc(String application);

	@Modifying
	@Transactional
	@Query("update EwayGzJsonStorageView e set e.isProcessed = true where e.id = :id")
	void markAsProcessedNew(@Param("id") Long id);

}
