package com.deloitte.returns.entity.AEwayBill;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "details_data", schema = "eway_bill_not")
public class EwbDetailsData {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ewbDt;
	private String ewbCategory;
	private int fileNum;
	private int totalRecords;

	private String fileHash;
	private String url;

	private String errorDesc;

}
