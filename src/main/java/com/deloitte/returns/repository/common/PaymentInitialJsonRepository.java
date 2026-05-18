package com.deloitte.returns.repository.common;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.PaymentInitialJson;

@Repository
public interface PaymentInitialJsonRepository extends JpaRepository<PaymentInitialJson, Long> {

	boolean existsByDt(Date date);

}
