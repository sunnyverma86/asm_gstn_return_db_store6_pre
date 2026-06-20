package com.deloitte.returns.entity.AEwayBill;

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
@Entity
@Table(name = "\"EWBFileCount\"", schema = "filecounter")
public class EwbCountData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt")
	private String ewbDt;

	@Column(name = "ty")
	private String ewbCategory;

	private int genFileCnt;

	private String errorDesc;

	private Timestamp insertDt;

	private Boolean isSuccess;

	private String msg;

	@Column(name = "counter_attempt") //
	private int counterAttempt;

}
