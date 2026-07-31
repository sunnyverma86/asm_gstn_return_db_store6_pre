package com.deloitte.tab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.tab.bean.CrnReportRequestDTO;
import com.deloitte.tab.bean.CrnReportResponseDTO;
import com.deloitte.tab.bean.Gstr1B2BDto;
import com.deloitte.tab.bean.Gstr3BMonthlyDto;
import com.deloitte.tab.bean.RegistrationReportRequestDTO;
import com.deloitte.tab.bean.RegistrationReportResponseDTO;
import com.deloitte.tab.service.CrnReportServiceImpl;
import com.deloitte.tab.service.Gstr1MonthlyService;
import com.deloitte.tab.service.Gstr3BMonthlyService;
import com.deloitte.tab.service.RegistrationReportServiceImpl;

@RestController
@RequestMapping("/api/return-gstr")
public class GstrsReturnController {

	private static final Logger log = LoggerFactory.getLogger(GstrsReturnController.class);

	@Autowired
	private Gstr3BMonthlyService gstr3BMonthlyService;

	@Autowired
	private CrnReportServiceImpl crnReportService;

	@Autowired
	private Gstr1MonthlyService gstr1MonthlyService;

	private static final String USERNAME = "GSTG2G18";

	@Autowired
	private RegistrationReportServiceImpl registrationReportServiceImpl;

	@PostMapping("/registration-report")
	public List<RegistrationReportResponseDTO> report(@RequestBody RegistrationReportRequestDTO request) {

		log.info("Registration Report Request : FromDate={}, ToDate={}", request.getFromDate(), request.getToDate());

		long start = System.currentTimeMillis();

		List<RegistrationReportResponseDTO> response = registrationReportServiceImpl.generateReport(request);

		log.info("Registration Report Generated Successfully. Records={}, Time={} ms", response.size(),
				System.currentTimeMillis() - start);

		return response;
	}

//https://boapi.internal.gst.gov.in/govtapi/v0.3/returns?action=ENFR3BDET&gstin=18ABRPA4839K2Z6&ret_period=032026
	@GetMapping("/summary-report")
	public ResponseEntity<?> getGstr3BSummaryReport(@RequestParam String gstin, @RequestParam String fy) {

		try {

			List<Gstr3BMonthlyDto> response = gstr3BMonthlyService.getGstr3BSummaryReport(USERNAME, gstin, fy);

			return ResponseEntity.ok(response);

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// https://boapi.internal.gst.gov.in/govtapi/v0.3/returns?action=ENFR1DET&gstin=18ABRPA4839K2Z6&ret_period=042025&sec_name=B2B
	@GetMapping("/summary-repo-gstr1-sec")
	public ResponseEntity<?> getGstr1SummaryReportSection(@RequestParam String gstin, @RequestParam String fy,
			@RequestParam String sections) {

		try {

			List<Gstr1B2BDto> response = gstr1MonthlyService.getGstr1SummaryReportSections(USERNAME, gstin, fy,
					sections);
			
			log.info("hold");

			return ResponseEntity.ok(response);

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/crn-report")
	public List<CrnReportResponseDTO> generateCrnReport(@RequestBody CrnReportRequestDTO request) {

		log.info("CRN Report Request Received. FromDate={}, ToDate={}, CaseType={}", request.getFromDate(),
				request.getToDate(), request.getCaseType());

		long startTime = System.currentTimeMillis();

		List<CrnReportResponseDTO> response = crnReportService.generateCrnReport(request);

		log.info("CRN Report Generated Successfully. Records={}, Time={} ms", response.size(),
				(System.currentTimeMillis() - startTime));

		return response;
	}

}

//@GetMapping("/detail")
//public ResponseEntity<?> getGstr3BDetail(@RequestParam String userName, @RequestParam String gstin,
//		@RequestParam String retPeriod) {
//
//	try {
//
//		JsonNode response = gstr3BMonthlyService.getGstr3BDetail(userName, gstin, retPeriod);
//
//		return ResponseEntity.ok(response);
//
//	} catch (Exception e) {
//
//		return ResponseEntity.badRequest().body(e.getMessage());
//	}
//}
//
//@PostMapping("/summary")
//public ResponseEntity<?> getGstr3BSummary(@RequestBody Gstr3BRequest request) {
//
//	try {
//
//		List<Gstr3BMonthlyDto> response = gstr3BMonthlyService.getGstr3BSummaryoLD(request.getUserName(),
//				request.getGstin(), request.getFinancialYear());
//
//		return ResponseEntity.ok(response);
//
//	} catch (Exception e) {
//
//		return ResponseEntity.badRequest().body(e.getMessage());
//	}
//}
