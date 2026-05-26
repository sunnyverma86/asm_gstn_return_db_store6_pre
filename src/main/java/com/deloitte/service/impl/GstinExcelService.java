package com.deloitte.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.returns.entity.GstinEntity;
import com.deloitte.returns.repository.common.GstinRepository;

@Service
public class GstinExcelService {

	@Autowired
	private GstinRepository gstinRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final int BATCH_SIZE = 5000;

	public void processCsv(String filePath) {

		String sql = """
				INSERT INTO asm.gstin_entity
				(gstin, taxpayer_type, found_info,
				 is_processed_gstr2a,
				 is_processed_ledger_cash,
				 is_processed_ledger_itc,
				 is_processed_ledger_tax,
				 is_processed_ledger_other,
				 is_processed_registration,
				 create_date_time,
				 updated_date_time)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())
				""";

		List<Object[]> batchArgs = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath), 1024 * 1024)) {

			String line;
			int rowNumber = 0;

			while ((line = reader.readLine()) != null) {

				rowNumber++;

				if (rowNumber == 1) {
					continue; // skip CSV header
				}

				String[] columns = line.split(",");

				if (columns.length < 2) {
					continue;
				}

				String gstin = columns[0].replace("\"", "").trim();
				String typeCode = columns[1].replace("\"", "").trim();

				String taxpayerType = mapTaxpayerType(typeCode);

				batchArgs.add(new Object[] { gstin, taxpayerType, true, // foundInfo
						false, // isProcessedGstr2a
						false, // isProcessedLedgerCash
						false, // isProcessedLedgerItc
						false, // isProcessedLedgerTax
						false, // isProcessedLedgerOther
						false // isProcessedRegistration
				});

				if (batchArgs.size() == BATCH_SIZE) {

					jdbcTemplate.batchUpdate(sql, batchArgs);
					batchArgs.clear();
				}
			}

			if (!batchArgs.isEmpty()) {
				jdbcTemplate.batchUpdate(sql, batchArgs);
			}

			System.out.println("✅ CSV data imported successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processCsv1(String filePath) {

		List<GstinEntity> batchList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			String line;
			int rowNumber = 0;

			while ((line = reader.readLine()) != null) {

				rowNumber++;

				if (rowNumber == 1) {
					continue; // skip header
				}

				String[] columns = line.split(",");

				if (columns.length < 2) {
					continue;
				}

				String gstin = columns[0].trim();
				String typeCode = columns[1].trim();

				GstinEntity entity = new GstinEntity();

				entity.setGstin(gstin);
				entity.setTaxpayerType(mapTaxpayerType(typeCode));

				batchList.add(entity);

				if (batchList.size() == 1000) {
					gstinRepository.saveAll(batchList);
					batchList.clear();
				}
			}

			if (!batchList.isEmpty()) {
				gstinRepository.saveAll(batchList);
			}

			System.out.println("✅ CSV data imported successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String mapTaxpayerType(String code) {

		switch (code) {

		case "APLTC":
			return "TCS";

		case "APLTD":
			return "TDS";

		case "CA":
			return "CASUAL";

		case "CO":
			return "COMPOSITION";

		case "ID":
			return "ISD";

		case "TP":
		case "NT":
			return "NORMAL";

		default:
			return "UNKNOWN";
		}
	}

	public void processExcel(MultipartFile file) {

		List<GstinEntity> batchList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {

				if (row.getRowNum() == 0)
					continue;

				String gstin = row.getCell(0).getStringCellValue();
				String typeCode = row.getCell(1).getStringCellValue();

				GstinEntity entity = new GstinEntity();
				entity.setGstin(gstin);
				entity.setTaxpayerType(mapTaxpayerType(typeCode));

				batchList.add(entity);

				if (batchList.size() == 1000) {
					gstinRepository.saveAll(batchList);
					batchList.clear();
				}
			}

			if (!batchList.isEmpty()) {
				gstinRepository.saveAll(batchList);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void processExcel(String filePath) {

		List<GstinEntity> batchList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {

				if (row.getRowNum() == 0) {
					continue; // skip header
				}

				String gstin = row.getCell(0).getStringCellValue();
				String typeCode = row.getCell(1).getStringCellValue();

				GstinEntity entity = new GstinEntity();

				entity.setGstin(gstin);
				entity.setTaxpayerType(mapTaxpayerType(typeCode));

				batchList.add(entity);

				if (batchList.size() == 1000) {
					gstinRepository.saveAll(batchList);
					batchList.clear();
				}
			}

			if (!batchList.isEmpty()) {
				gstinRepository.saveAll(batchList);
			}

			System.out.println("✅ Excel data imported successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}