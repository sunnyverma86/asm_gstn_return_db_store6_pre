package com.deloitte.returns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr7.Gstr7;

@Repository
public interface Gstr7Repository extends JpaRepository<Gstr7, Long> {

	// List<Gstr7> findByIdIn(List<Long> ids);
	<T> Optional<T> findByIdIn(List<Long> ids);

}
