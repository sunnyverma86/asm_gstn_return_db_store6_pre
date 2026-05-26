package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.AlertJson;

@Repository
public interface AlertJsonRepository extends JpaRepository<AlertJson, Long> {

}
