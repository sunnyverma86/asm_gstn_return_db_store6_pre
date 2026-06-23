package com.deloitte.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.deloitte.common.bean.ReportRequestDTO;
import com.deloitte.common.bean.ReportResponseDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportFileServiceImpl {

	public void generateExcel(List<ReportResponseDTO> data, ReportRequestDTO request, HttpServletResponse response)
			throws Exception {

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		response.setHeader("Content-Disposition", "attachment; filename=Report.xlsx");

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Report");

		/*
		 * TITLE STYLE
		 */
		CellStyle titleStyle = workbook.createCellStyle();

		Font titleFont = workbook.createFont();
		titleFont.setBold(true);
		titleFont.setFontHeightInPoints((short) 16);

		titleStyle.setFont(titleFont);
		titleStyle.setAlignment(HorizontalAlignment.CENTER);

		Row titleRow = sheet.createRow(0);

		Cell titleCell = titleRow.createCell(0);

		titleCell.setCellValue("ENTERPRISE REPORT");

		titleCell.setCellStyle(titleStyle);

		sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 7));

		/*
		 * FILTER DETAILS
		 */
		Row filterRow = sheet.createRow(2);

		filterRow.createCell(0).setCellValue("TY : " + request.getTy());

		filterRow.createCell(2).setCellValue("FROM : " + request.getFromDate());

		filterRow.createCell(4).setCellValue("TO : " + request.getToDate());

		filterRow.createCell(6).setCellValue("Generated : " + LocalDateTime.now());

		/*
		 * HEADER STYLE
		 */
		CellStyle headerStyle = workbook.createCellStyle();

		Font headerFont = workbook.createFont();

		headerFont.setBold(true);

		headerStyle.setFont(headerFont);

		headerStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());

		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);

		/*
		 * DATA STYLE
		 */
		CellStyle dataStyle = workbook.createCellStyle();

		dataStyle.setBorderBottom(BorderStyle.THIN);
		dataStyle.setBorderTop(BorderStyle.THIN);
		dataStyle.setBorderLeft(BorderStyle.THIN);
		dataStyle.setBorderRight(BorderStyle.THIN);

		Row header = sheet.createRow(4);

		String[] cols = { "DATE", "NUM FILES", "FILE NUM", "NUM FILES CNT", "DATE-JSON", "FILE NUMBER", "JSON COUNT",
				"STATUS" };

		for (int i = 0; i < cols.length; i++) {

			Cell cell = header.createCell(i);

			cell.setCellValue(cols[i]);

			cell.setCellStyle(headerStyle);
		}

		int rowNum = 5;

		for (ReportResponseDTO dto : data) {

			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(dto.getDt() == null ? "" : dto.getDt().toString());

			row.createCell(1).setCellValue(dto.getNumFiles() == null ? 0 : dto.getNumFiles());

			row.createCell(2).setCellValue(dto.getFileNum() == null ? 0 : dto.getFileNum());

			row.createCell(3).setCellValue(dto.getNumFilesCnt() == null ? 0 : dto.getNumFilesCnt());

			row.createCell(4).setCellValue(dto.getDt2() == null ? "" : dto.getDt2().toString());

			row.createCell(5).setCellValue(dto.getFileNumber() == null ? 0 : dto.getFileNumber());

			row.createCell(6).setCellValue(dto.getJsonCount() == null ? 0 : dto.getJsonCount());

			String status = dto.getNumFilesCnt() != null && dto.getJsonCount() != null
					&& dto.getNumFilesCnt().longValue() == dto.getJsonCount() ? "MATCHED" : "MISMATCH";

			row.createCell(7).setCellValue(status);

			for (int i = 0; i < 8; i++) {

				row.getCell(i).setCellStyle(dataStyle);
			}
		}

		Row totalRow = sheet.createRow(rowNum + 2);

		totalRow.createCell(0).setCellValue("TOTAL RECORDS");

		totalRow.createCell(1).setCellValue(data.size());

		for (int i = 0; i < 8; i++) {
			sheet.autoSizeColumn(i);
		}

		workbook.write(response.getOutputStream());

		workbook.close();
	}

	public void generatePdf(List<ReportResponseDTO> data, ReportRequestDTO request, HttpServletResponse response)
			throws Exception {

		response.setContentType("application/pdf");

		response.setHeader("Content-Disposition", "attachment; filename=Report.pdf");

		Document document = new Document(PageSize.A4.rotate());

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		com.itextpdf.text.Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

		Paragraph heading = new Paragraph("ENTERPRISE REPORT", title);

		heading.setAlignment(Element.ALIGN_CENTER);

		document.add(heading);

		document.add(new Paragraph(" "));

		document.add(new Paragraph("TY : " + request.getTy()));

		document.add(new Paragraph("FROM DATE : " + request.getFromDate()));

		document.add(new Paragraph("TO DATE : " + request.getToDate()));

		document.add(new Paragraph("TOTAL RECORDS : " + data.size()));

		document.add(new Paragraph(" "));

		PdfPTable table = new PdfPTable(8);

		table.setWidthPercentage(100);

		table.setWidths(new float[] { 2, 2, 2, 2, 2, 2, 2, 2 });

		String[] headers = { "DATE", "NUM FILES", "FILE NUM", "NUM FILES CNT", "DATE-JSON", "FILE NUMBER", "JSON COUNT",
				"STATUS" };

		com.itextpdf.text.Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

		for (String h : headers) {

			PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));

			cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(cell);
		}

		for (ReportResponseDTO dto : data) {

			table.addCell(dto.getDt() == null ? "" : dto.getDt().toString());

			table.addCell(String.valueOf(dto.getNumFiles()));

			table.addCell(String.valueOf(dto.getFileNum()));

			table.addCell(String.valueOf(dto.getNumFilesCnt()));

			table.addCell(dto.getDt2() == null ? "" : dto.getDt2().toString());

			table.addCell(String.valueOf(dto.getFileNumber()));

			table.addCell(String.valueOf(dto.getJsonCount()));

			String status = dto.getNumFilesCnt() != null && dto.getJsonCount() != null
					&& dto.getNumFilesCnt().longValue() == dto.getJsonCount() ? "MATCHED" : "MISMATCH";

			table.addCell(status);
		}

		document.add(table);

		document.close();
	}
}
