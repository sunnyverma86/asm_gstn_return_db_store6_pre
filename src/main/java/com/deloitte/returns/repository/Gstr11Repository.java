package com.deloitte.returns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.Gstr11.Gstr11;

@Repository
public interface Gstr11Repository extends JpaRepository<Gstr11, Long> {

}
