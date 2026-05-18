package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr10.Gstr10;

@Repository
public interface Gstr10Repository extends JpaRepository<Gstr10, Long> {

	Gstr10 findByGstin(String gstin);

}
