package com.deloitte.returns.repository.eway;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.returns.entity.EwayBill.PartAEwb;

@Repository
public interface PartAEwbRepository extends JpaRepository<PartAEwb, Long> {

	Optional<PartAEwb> findFirstByEwbNo(String ewbNo);

}
