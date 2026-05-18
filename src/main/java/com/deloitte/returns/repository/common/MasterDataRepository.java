package com.deloitte.returns.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.common.entity.MasterData;

@Repository
public interface MasterDataRepository extends JpaRepository<MasterData, Long> {

    MasterData findTopByUserNameOrderByIdDesc(String userName);


}