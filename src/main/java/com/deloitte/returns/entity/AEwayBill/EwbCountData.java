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
@Table(name = "count_data", schema = "eway_bill_not")
public class EwbCountData {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ewbDt;
	private String ewbCategory;
	private int genFileCnt;
	private int totalRecords;
	private String urlForData;

	private String errorDesc;

}
