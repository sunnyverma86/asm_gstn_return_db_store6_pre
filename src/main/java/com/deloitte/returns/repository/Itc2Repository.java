package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Itc2.Itc2;

@Repository
public interface Itc2Repository extends JpaRepository<Itc2, Long> {

}
