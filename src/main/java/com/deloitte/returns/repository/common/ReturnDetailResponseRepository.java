package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.ReturnFileDetailResponse;

@Repository
public interface ReturnDetailResponseRepository extends JpaRepository<ReturnFileDetailResponse, Long> {

}
