package com.deloitte.returns.repository.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.LedgerCash.LedgerCash;

@Repository
public interface LedgerCashRepository extends JpaRepository<LedgerCash, Long> {

}
