package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr2a.Gstr2a;

@Repository
public interface Gstr2aRepository extends JpaRepository<Gstr2a, Long> {
}
