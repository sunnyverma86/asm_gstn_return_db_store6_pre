package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr1A.Gstr1A;

@Repository
public interface Gstr1ARepository extends JpaRepository<Gstr1A, Long> {

	Gstr1A findByGstin(String gstin);

	Gstr1A findByGstinAndFp(String gstin, String fp);

}
