package com.deloitte.returns.entity.pmt;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pditc", schema = "pmt")
public class Pmt_Pditc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("c_pdc")
	private Double cPdc;

	@JsonProperty("c_pdi")
	private Double cPdi;

	@JsonProperty("cs_pdcs")
	private Double csPdcs;

	@JsonProperty("i_pdc")
	private Double iPdc;

	@JsonProperty("i_pdi")
	private Double iPdi;

	@JsonProperty("i_pds")
	private Double iPds;

	@JsonProperty("liab_id")
	private long liabID;

	@JsonProperty("s_pdi")
	private Double sPdi;

	@JsonProperty("s_pds")
	private Double sPds;

	@JsonProperty("trancd")
	private double trancd;

}