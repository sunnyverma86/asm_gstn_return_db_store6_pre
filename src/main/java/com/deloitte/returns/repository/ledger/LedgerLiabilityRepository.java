package com.deloitte.returns.repository.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.LedgerLiability.LedgerLiability;

@Repository
public interface LedgerLiabilityRepository extends JpaRepository<LedgerLiability, Long> {

}
