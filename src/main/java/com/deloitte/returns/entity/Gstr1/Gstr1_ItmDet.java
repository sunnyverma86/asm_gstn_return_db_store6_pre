package com.deloitte.returns.entity.Gstr1;

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
@Table(name = "itm_det", schema = "gstr1")
@Entity
public class Gstr1_ItmDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("rt")
	private Double rt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("chksum")
	@Column(length = 2000)
	private String chksum;

}
