package com.deloitte.returns.entity;

import java.sql.Date;
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
@Table(name = "r8_initial_json", schema = "log")
public class R8InitialJson implements BaseJsonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"Id\"")
	private Long id;

	@Column(name = "\"ReturnFileDetailId\"")
	private Long returnFileDetailPrimaryId;

	@Column(name = "filepath")
	private String filePath;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "jsondata")
	private JsonNode jsonData;

	@Column(name = "filenumber")
	private Integer fileNumber;

	@Column(name = "dt")
	private Date dt;

	private Integer sequenceNumber;

	private String category;

	private Timestamp insertDt;

	@Column(name = "\"ReturnFileCountId\"")
	private Long returnFileCountPrimaryId;
}
