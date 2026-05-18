package com.deloitte.returns.repository.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.LedgerItc.LedgerItc;

@Repository
public interface LedgerItcRepository extends JpaRepository<LedgerItc, Long> {

}
