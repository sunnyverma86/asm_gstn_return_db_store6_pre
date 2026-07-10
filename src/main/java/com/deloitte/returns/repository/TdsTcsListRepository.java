package com.deloitte.returns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.log.TdsTcsList;

@Repository
public interface TdsTcsListRepository extends JpaRepository<TdsTcsList, Long> {

	Page<TdsTcsList> findByIsMissingTrueAndCounterAttemptLessThan(int i, Pageable pageable);

}
