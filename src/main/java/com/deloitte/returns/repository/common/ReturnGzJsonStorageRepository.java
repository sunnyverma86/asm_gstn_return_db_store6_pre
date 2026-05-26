package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.filecounter.ReturnGzJsonStorage;

@Repository
public interface ReturnGzJsonStorageRepository extends JpaRepository<ReturnGzJsonStorage, Long> {

}
