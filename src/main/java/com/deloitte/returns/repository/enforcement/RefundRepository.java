package com.deloitte.returns.repository.enforcement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Refund.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {

}