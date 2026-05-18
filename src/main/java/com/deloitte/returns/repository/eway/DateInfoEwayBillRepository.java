package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.returns.entity.AEwayBill.DateInfoEwayBill;

public interface DateInfoEwayBillRepository extends JpaRepository<DateInfoEwayBill, Long> {

	DateInfoEwayBill findDateDataByDateAndCategory(String date, String category);

	DateInfoEwayBill findByDateAndCategory(String date, String category);

}
