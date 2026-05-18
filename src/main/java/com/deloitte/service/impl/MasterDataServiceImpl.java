package com.deloitte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.repository.common.MasterDataRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MasterDataServiceImpl {

	@Autowired
	private MasterDataRepository masterDataRepository;

	public MasterData create(MasterData masterData) {
		return masterDataRepository.save(masterData);
	}

	public MasterData getMasterdatabyName(String username) {
		return masterDataRepository.findTopByUserNameOrderByIdDesc(username);

	}

}
