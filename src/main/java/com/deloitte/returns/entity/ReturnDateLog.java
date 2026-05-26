package com.deloitte.returns.entity;

import java.sql.Timestamp;

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
@Table(name = "return_date_log", schema = "common")
@Entity
public class ReturnDateLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "start_date")
	private String startDate;

	private Timestamp insertDt;

	// getters setters

	// INSERT INTO eway_bill_not.ewaybill_log (start_date, insert_dt)
	// VALUES ('01-02-2026', CURRENT_TIMESTAMP);
}
