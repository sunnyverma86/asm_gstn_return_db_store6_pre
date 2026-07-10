package com.deloitte.service.impl;

import java.net.InetAddress;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SchedularService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${server.port}")
	private String serverPort;

//	@Scheduled(fixedDelay = 3 * 60 * 60 * 1000) // 3 hours delay
	public void hitUrl() {
		String url = "http://" + getLocalIp() + ":" + serverPort + "/common/userSession/authenticate/GSTG2G18";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedNow = now.format(formatter);
		System.out.println("Scheduler For  2 hours: " + formattedNow);
		log.info("Time: "+formattedNow);

		try {
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("Hit URL: " + url + " with response: " + response);
			log.info("Time: "+formattedNow);
		} catch (Exception e) {
			System.err.println("Failed to hit URL: " + url + ", Error: " + e.getMessage());
		}
	}
	
	//@Scheduled(fixedDelay = 3 * 60 * 60 * 1000) // 3 hours delay
	public void hitUrlEway() {

		// String url = "http://localhost:" + serverPort +
		// "/common/userSession/authenticate/GSTG2G18";
		String url = "http://" + getLocalIp() + ":" + serverPort + "/EwayBill/authenticate";
		
		// String url = "http://10.79.1.152:" + serverPort +
		// "/common/userSession/authenticate/GSTG2G18";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedNow = now.format(formatter);
		System.out.println("Scheduler For  2 hours: " + formattedNow);
		log.info("Time: "+formattedNow);

		try {
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("Hit URL: " + url + " with response: " + response);
			log.info("Time: "+formattedNow);
		} catch (Exception e) {
			System.err.println("Failed to hit URL: " + url + ", Error: " + e.getMessage());
		}
	}

	private String getLocalIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			return "localhost";
		}
	}

//	@Scheduled(fixedDelay = 3 * 60 * 60 * 1000) // 3 hours delay
//	public void hitUrl1() {
//
//		String url = "http://localhost:" + serverPort + "/common/userSession/authenticateDataPublic?username=GSTG2G18";
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String formattedNow = now.format(formatter);
//		System.out.println("Scheduler For  2 hours: " + formattedNow);
//
//		try {
//			String response = restTemplate.getForObject(url, String.class);
//			System.out.println("Hit URL: " + url + " with response: " + response);
//		} catch (Exception e) {
//			System.err.println("Failed to hit URL: " + url + ", Error: " + e.getMessage());
//		}
//	}

}
