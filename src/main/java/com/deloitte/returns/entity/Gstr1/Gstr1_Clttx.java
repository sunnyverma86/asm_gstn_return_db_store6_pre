package com.deloitte.returns.entity.Gstr1;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "clttx", schema = "gstr1")
@Entity
public class Gstr1_Clttx {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("cess")
	private Double cess;

	@JsonProperty("suppval")
	private Double suppval;

	@JsonProperty("igst")
	private Double igst;
	
	@JsonProperty("oetin")
	private String oetin;
	

	
	@JsonProperty("omon")
	private String omon;

}
