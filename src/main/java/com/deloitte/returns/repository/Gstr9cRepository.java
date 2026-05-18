package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr9c.Gstr9c;

@Repository
public interface Gstr9cRepository extends JpaRepository<Gstr9c, Long> {

}
