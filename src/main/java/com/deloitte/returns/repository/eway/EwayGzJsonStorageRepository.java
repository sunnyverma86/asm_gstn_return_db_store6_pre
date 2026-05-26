package com.deloitte.returns.repository.eway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.EwayGzJsonStorage;

@Repository
public interface EwayGzJsonStorageRepository extends JpaRepository<EwayGzJsonStorage, Long> {
}
