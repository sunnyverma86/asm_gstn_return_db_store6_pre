package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.ReturnCountCrn;

@Repository
public interface ReturnCountCrnRepository extends JpaRepository<ReturnCountCrn, Long> {

}
