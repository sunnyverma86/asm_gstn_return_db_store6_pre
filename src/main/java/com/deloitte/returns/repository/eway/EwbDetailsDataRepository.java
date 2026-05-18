package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.AEwayBill.EwbDetailsData;

@Repository
public interface EwbDetailsDataRepository extends JpaRepository<EwbDetailsData, Long> {

}
