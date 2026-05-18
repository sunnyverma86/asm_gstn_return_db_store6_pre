package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.AEwayBill.EwayFileDetailResponse;

@Repository
public interface EwayFileDetailResponseRepository extends JpaRepository<EwayFileDetailResponse, Long> {
}
