package com.deloitte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.common.entity.GSTUserSession;
import com.deloitte.service.impl.GSTUserSessionServicesImpl;

@RestController
@RequestMapping("/common/userSession")
public class GSTUserSessionController {

	@Autowired
	private GSTUserSessionServicesImpl apiServices;

	@GetMapping("/authenticate/{username}")
	public ResponseEntity<GSTUserSession> authenticate(@PathVariable String username) {
		return new ResponseEntity<>(apiServices.createUserSession(username), HttpStatus.CREATED);
	}

}
