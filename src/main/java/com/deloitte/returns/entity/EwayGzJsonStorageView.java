package com.deloitte.returns.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eway_gz_json_storage_asm_view", schema = "eway_bill_not")
public class EwayGzJsonStorageView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long returnFileDetailPrimaryId;

	private Long returnFileCountPrimaryId;

	private String filePath;

	private String filePathAbs;

	private Integer fileNumber;

	private Integer sequenceNumber;

	private String dt;

	private String category;

	private Boolean isProcessed;

	private Timestamp insertDt;
}
