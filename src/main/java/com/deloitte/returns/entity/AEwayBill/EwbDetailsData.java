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
@Table(name = "\"EWBFileDetail\"", schema = "filecounter")
public class EwbDetailsData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long returnFileCountId;

	private String ewbDt;
	private String ewbCategory;
	private int fileNum;
	private int totalRecords;
	
	private String fileHash;
	private String url;
	
	private String errorDesc;
	
	private Boolean isSuccess;
	
	private String msg;
	
	private Timestamp insertDt;

}
