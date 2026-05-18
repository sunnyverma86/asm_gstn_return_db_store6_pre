package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr4.Gstr4;

@Repository
public interface Gstr4Repository extends JpaRepository<Gstr4, Long> {

}
