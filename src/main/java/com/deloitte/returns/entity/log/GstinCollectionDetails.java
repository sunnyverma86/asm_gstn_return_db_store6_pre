package com.deloitte.returns.entity.log;

import java.time.LocalDateTime;

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
@Table(name = "gstin_collection_details", schema = "gst_api_registration")
public class GstinCollectionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "\"Id\"")
	private Long id;

	@Column(name = "gstin", length = 20)
	private String gstin;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "jsondata")
	private JsonNode jsondata;

	@Column(name = "issuccess")
	private Boolean issuccess;

	@Column(name = "msg", columnDefinition = "jsonb")
	private JsonNode msg;

	@Column(name = "insert_tm")
	private LocalDateTime insertTm;

	@Column(name = "is_missing")
	private Boolean isMissing;

	@Column(name = "counter_attempt") //
	private int counterAttempt;

}
