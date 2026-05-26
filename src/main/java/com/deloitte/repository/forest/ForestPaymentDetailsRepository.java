package com.deloitte.repository.forest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Forest.ForestPaymentDetails;

@Repository
public interface ForestPaymentDetailsRepository extends JpaRepository<ForestPaymentDetails, Long> {

}
