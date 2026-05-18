package com.deloitte.returns.repository.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.type.LedgerOther.LedgerOther;

@Repository
public interface LedgerOtherRepository extends JpaRepository<LedgerOther, Long> {

}
