package com.deloitte.returns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr1.Gstr1;

@Repository
public interface Gstr1Repository extends JpaRepository<Gstr1, Long> {

	Gstr1 findByGstin(String gstin);

	Gstr1 findByGstinAndFp(String gstin, String fp);

	<T> Optional<T> findByIdIn(List<Long> ids);

}
