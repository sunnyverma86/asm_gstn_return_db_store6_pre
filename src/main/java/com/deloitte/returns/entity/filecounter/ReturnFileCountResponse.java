package com.deloitte.returns.entity.filecounter;

import java.sql.Date;
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
//@Table(name = "return_file_count", schema = "asm")
@Table(name = "\"ReturnFileCount\"", schema = "filecounter")
public class ReturnFileCountResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "\"ReturnFileCountId\"")
	private Long returnFileCountId;

	@Column(name = "ty")
	private String ty;

	@Column(name = "eod_closed")
	private String eodClosed;

	@Column(name = "num_files")
	private Integer numFiles;

	@Column(name = "\"IsSuccess\"")
	private Boolean isSuccess;

	@Column(name = "msg")
	private String msg;

	@Column(name = "dt") // date
	private Date dt;

	@Column(name = "insertdt") // TimeStamp
	private Timestamp insertDt;

	@Column(name = "counter_attempt") //
	private int counterAttempt;
	
	@Column(name = "url") //
	private String url;
}