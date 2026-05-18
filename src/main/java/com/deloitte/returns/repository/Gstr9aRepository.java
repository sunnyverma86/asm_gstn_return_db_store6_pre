package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr9a.Gstr9A;

@Repository
public interface Gstr9aRepository extends JpaRepository<Gstr9A, Long> {

}
