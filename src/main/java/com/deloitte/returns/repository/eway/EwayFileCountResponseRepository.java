package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.AEwayBill.EwayFileCountResponse;

@Repository
public interface EwayFileCountResponseRepository extends JpaRepository<EwayFileCountResponse, Long> {
}
