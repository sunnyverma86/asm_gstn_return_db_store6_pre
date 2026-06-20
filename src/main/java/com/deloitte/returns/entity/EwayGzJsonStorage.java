package com.deloitte.returns.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
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
@Table(name = "eway_storage", schema = "eway_bill_not")
public class EwayGzJsonStorage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long returnFileDetailPrimaryId;

	private Long returnFileCountPrimaryId;

	private String filePath;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "json_data")
	private JsonNode jsonData;

	private Integer fileNumber;

	private Integer sequenceNumber;

	private String dt;

	private String category;

	private Boolean isProcessed;

	private Timestamp insertDt;
}
