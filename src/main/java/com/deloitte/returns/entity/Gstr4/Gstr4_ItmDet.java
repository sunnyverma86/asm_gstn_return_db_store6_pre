package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "itm_det", schema = "gstr4")
@Entity
public class Gstr4_ItmDet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("rt")
	private double rt;

	@JsonProperty("txval")
	private double txval;
	
	@JsonProperty("samt")
	private Double samt;
	
	@JsonProperty("camt")
	private Double camt;


}
