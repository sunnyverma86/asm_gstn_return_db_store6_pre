package com.deloitte.returns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr2b.Gstr2b;

@Repository
public interface Gstr2bRepository extends JpaRepository<Gstr2b, Long> {

	// List<Gstr2b> findByIdIn(List<Long> ids);
	<T> Optional<T> findByIdIn(List<Long> ids);

}
