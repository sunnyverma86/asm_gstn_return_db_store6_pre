package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr9.Gstr9;

@Repository
public interface Gstr9Repository extends JpaRepository<Gstr9, Long> {

	Gstr9 findByGstin(String gstin);

	Gstr9 findByGstinAndFp(String gstin, String fp);
}
