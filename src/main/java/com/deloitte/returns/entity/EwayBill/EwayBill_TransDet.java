package com.deloitte.returns.entity.EwayBill;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "TransDet", schema = "eway_live_eway_bill_new")
public class EwayBill_TransDet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@JsonProperty("TransId")
	private String transID;

	@JsonProperty("UpdId")
	private String updID;

	@JsonProperty("UpdDt")
	private String updDt;
	
	// New columns to store EwbNos and ewbNo from the EwayBill_Ewb table
	@Column(name = "Ewb_nos")
	private Long ewbNos;

	@Column(name = "Ewb_no")
	private Long ewbNo;

}
