package com.deloitte.returns.entity.Gstr98a;

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
@Table(name = "documents", schema = "gstr9_8a")
@Entity
public class Gstr9_8a_Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("oinum")
	private String oinum;

	@JsonProperty("oidt")
	private String oidt;

	@JsonProperty("iseligible")
	private String iseligible;

	@JsonProperty("ont_num")
	private String ontNum;

	@JsonProperty("ont_dt")
	private String ontDt;

	@JsonProperty("ontty")
	private String ontty;

	@JsonProperty("inv_typ")
	private String inv_typ;

	@JsonProperty("rchrg")
	private String rchrg;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("nt_dt")
	private String ntDt;

	@JsonProperty("nt_num")
	private String ntNum;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("val")
	private double val;

}