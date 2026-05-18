package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "items", schema = "gstr9")
@Entity
public class Gstr9_Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("hsn_sc")
	private String hsnSc;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("isconcesstional")
	private String isconcesstional;

	@JsonProperty("qty")
	private Double qty;

	@JsonProperty("rt")
	private Double rt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("uqc")
	private String uqc;
	
	@JsonProperty("desc")
	@Column(length = 1500)
	private String descItems;

	
}
