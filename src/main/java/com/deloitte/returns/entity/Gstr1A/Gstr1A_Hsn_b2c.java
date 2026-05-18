package com.deloitte.returns.entity.Gstr1A;
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
@Table(name = "hsn_b2c", schema = "gstr1a")
@Entity


public class Gstr1A_Hsn_b2c {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum; //added
	
	
	
	@JsonProperty("csamt")
	private Double csamt;
	
	@JsonProperty("samt")
	private Double samt;
	
	@JsonProperty("val")
	private Double val;

	@JsonProperty("rt")
	private Double rt;
	
	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("uqc")
	@Column(length = 1000)
	private String uqc;
	
	@JsonProperty("num")
	private Long num;

	@JsonProperty("qty")
	private Double qty;

	@JsonProperty("camt")
	private Double camt;
	
	@JsonProperty("hsn_sc")
	@Column(length = 1000)
	private String hsnSc;

	@JsonProperty("iamt")
	private Double iamt;
	
	@JsonProperty("desc")
	@Column(length = 35000)
	private String descData;
	
	@JsonProperty("user_desc")
	@Column(length = 35000)
	private String userDesc;

	@JsonProperty("flag")
	private String flag;

}
