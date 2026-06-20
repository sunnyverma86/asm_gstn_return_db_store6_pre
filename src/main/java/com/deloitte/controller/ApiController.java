package com.deloitte.controller;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.common.bean.LastUpdateDTO;
import com.deloitte.common.bean.ReportRequestDTO;
import com.deloitte.common.bean.ReportResponseDTO;
import com.deloitte.service.impl.ReportServiceImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

	private final ReportServiceImpl reportServiceImpl;

	@PostMapping("/report")
	public List<ReportResponseDTO> report(@RequestBody ReportRequestDTO request) {

		log.info("Report request received. FromDate={}, ToDate={}, TY={}", request.getFromDate(), request.getToDate(),
				request.getTy());

		long start = System.currentTimeMillis();

		List<ReportResponseDTO> response = reportServiceImpl.generateReport(request);

		log.info("Report generated successfully. Records={}, TimeTaken={} ms", response.size(),
				(System.currentTimeMillis() - start));

		return response;
	}

	@GetMapping("/last-update")
	public List<LastUpdateDTO> getLastUpdateReport() {

		return reportServiceImpl.getLastUpdateReport();

	}

	@GetMapping("/excel")
	public void excel(HttpServletResponse response) throws Exception {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Report");

		workbook.write(response.getOutputStream());

	}

	@GetMapping("/pdf")
	public void pdf(HttpServletResponse response) throws Exception {

		Document document = new Document();

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		document.add(new Paragraph("Enterprise Report"));

		document.close();
	}

}