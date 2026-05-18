package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.pmt.Pmt;

@Repository
public interface PmtRepository extends JpaRepository<Pmt, Long> {

	Pmt findByGstin(String gstin);

}
