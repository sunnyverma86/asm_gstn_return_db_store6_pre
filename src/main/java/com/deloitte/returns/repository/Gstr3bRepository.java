package com.deloitte.returns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr3b.Gstr3b;

@Repository
public interface Gstr3bRepository extends JpaRepository<Gstr3b, Long> {

	// List<Gstr3b> findByIdIn(List<Long> ids);
	<T> Optional<T> findByIdIn(List<Long> ids);
}
