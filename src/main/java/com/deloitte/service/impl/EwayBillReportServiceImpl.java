package com.deloitte.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.returns.entity.EwayBill.EwayBill_Ewb;
import com.deloitte.returns.repository.eway.EwayBillEwbRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EwayBillReportServiceImpl {

	@Autowired
	private EwayBillEwbRepository ewayBillEwbRepository;

	public byte[] generatePdf(String docDate, String status) throws Exception {

		List<EwayBill_Ewb> list = getData(docDate, status);

		double totalIgst = list.stream().mapToDouble(EwayBill_Ewb::getIgstVal).sum();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Document document = new Document(PageSize.A4.rotate());

		PdfWriter.getInstance(document, baos);

		document.open();

		Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

		document.add(new Paragraph("E-Way Bill Report", titleFont));

		document.add(new Paragraph("Document Date : " + docDate));

		document.add(new Paragraph("Status : " + status));

		document.add(new Paragraph("Total E-Way Bill : " + list.size()));

		document.add(new Paragraph("Total IGST : " + totalIgst));

		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(8);

		table.addCell("EWB No");
		table.addCell("Date");
		table.addCell("Status");
		table.addCell("From GSTIN");
		table.addCell("To GSTIN");
		table.addCell("Doc No");
		table.addCell("Value");
		table.addCell("IGST");

		for (EwayBill_Ewb e : list) {

			table.addCell(String.valueOf(e.getEwbNo()));

			table.addCell(e.getEwbDt());

			table.addCell(e.getStatus());

			table.addCell(e.getFrGstin());

			table.addCell(e.getToGstin());

			table.addCell(e.getDocNo());

			table.addCell(String.valueOf(e.getAssVal()));

			table.addCell(String.valueOf(e.getIgstVal()));

		}

		document.add(table);

		document.close();

		return baos.toByteArray();

	}

	public byte[] generateExcel(String docDate, String status) throws Exception {

		List<EwayBill_Ewb> list = getData(docDate, status);

		double totalIgst = list.stream().mapToDouble(EwayBill_Ewb::getIgstVal).sum();

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("EwayBill");

		int rowNum = 0;

		Row summary = sheet.createRow(rowNum++);

		summary.createCell(0).setCellValue("Total E-Way Bill");

		summary.createCell(1).setCellValue(list.size());

		Row igstRow = sheet.createRow(rowNum++);

		igstRow.createCell(0).setCellValue("Total IGST");

		igstRow.createCell(1).setCellValue(totalIgst);

		rowNum++;

		Row header = sheet.createRow(rowNum++);

		String[] columns = {

				"EWB No", "Date", "Status", "From GSTIN", "To GSTIN", "Doc No", "Value", "IGST"

		};

		for (int i = 0; i < columns.length; i++) {

			header.createCell(i).setCellValue(columns[i]);

		}

		for (EwayBill_Ewb e : list) {

			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(e.getEwbNo());

			row.createCell(1).setCellValue(e.getEwbDt());

			row.createCell(2).setCellValue(e.getStatus());

			row.createCell(3).setCellValue(e.getFrGstin());

			row.createCell(4).setCellValue(e.getToGstin());

			row.createCell(5).setCellValue(e.getDocNo());

			row.createCell(6).setCellValue(e.getAssVal());

			row.createCell(7).setCellValue(e.getIgstVal());

		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		workbook.write(baos);

		workbook.close();

		return baos.toByteArray();

	}

	private List<EwayBill_Ewb> getData(String docDate, String status) {
		
		LocalDate date = LocalDate.parse(docDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		if ("ALL".equalsIgnoreCase(status)) {

			return ewayBillEwbRepository.findByDocDt(formattedDate);

		}

		return ewayBillEwbRepository.findByDocDtAndStatus(formattedDate, status);

	}

}