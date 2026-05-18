package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.pwd.PwdPayment;

@Repository

public interface PwdPaymentRecordRepository extends JpaRepository<PwdPayment, Long> {

}
