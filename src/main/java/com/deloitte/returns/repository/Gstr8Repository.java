package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr8.Gstr8;

@Repository
public interface Gstr8Repository extends JpaRepository<Gstr8, Long> {

}
