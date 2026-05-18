package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.AEwayBill.EWayBillAuthBean;

@Repository
public interface EWayBillBeanRepository extends JpaRepository<EWayBillAuthBean, Long> {

	EWayBillAuthBean findFirstByOrderByIdDesc();

}
