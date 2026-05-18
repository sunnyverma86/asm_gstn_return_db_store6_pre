package com.deloitte.returns.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.common.entity.MasterData;
import com.deloitte.returns.repository.common.MasterDataRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MasterDataService {

	@Autowired
	private MasterDataRepository masterDataRepository;

	public MasterData getMasterdatabyName(String username) {

	    return masterDataRepository.findAll()
	            .stream()
	            .filter(data -> username.equals(data.getUserName()))
	            .findFirst()
	            .orElseThrow(() ->
	                    new IllegalStateException("MasterData not found for username: " + username));
	}

}
