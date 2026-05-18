package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr98a.Gstr98a;

@Repository
public interface Gstr98aRepository extends JpaRepository<Gstr98a, Long> {

}
