package com.deloitte.returns.entity.filecounter;

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
@Table(name = "\"ReturnFileDetail\"", schema = "filecounter")
public class ReturnFileDetailResponse {

	@Column(name = "\"ReturnFileCountId\"")
	private Long returnFileCountId;

	@Column(name = "file_num")
	private Integer fileNum;

	@Column(name = "cnt")
	private Integer cnt;

	@Column(name = "url")
	private String url;

	@Column(name = "\"IsSuccess\"")
	private Boolean isSuccess;

	@Column(name = "msg")
	private String msg;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ReturnFileDetailId\"")
	private Long returnFileDetailId;

	@Column(name = "\"insertdatetime\"")
	private Timestamp insertDt;
	
	private String dt;

	private String hash;

}
