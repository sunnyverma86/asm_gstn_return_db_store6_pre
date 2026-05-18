package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr5.Gstr5;

@Repository
public interface Gstr5Repository extends JpaRepository<Gstr5, Long> {

	Gstr5 findByGstin(String gstin);

	Gstr5 findByGstinAndFp(String gstin, String fp);
}
