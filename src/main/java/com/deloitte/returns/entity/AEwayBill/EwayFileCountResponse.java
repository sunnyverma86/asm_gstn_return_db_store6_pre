package com.deloitte.returns.entity.AEwayBill;

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
@Table(name = "eway_file_count_response", schema = "asm")
public class EwayFileCountResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long returnFileCountId;

	private String ty;
	private String eodClosed;
	private Integer numFiles;

	private Boolean isSuccess;
	private String msg;

	private String dt;

	private Timestamp insertDt;
}
