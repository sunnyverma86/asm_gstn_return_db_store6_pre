package com.deloitte.returns.entity.Gstr3b;

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
@Table(name = "liab_intrchrg", schema = "gstr3b")
@Entity
public class Gstr3b_liabIntrchrg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("iamt")
	@Column
	private double iamt;

	@JsonProperty("camt")
	@Column
	private double camt;

	@JsonProperty("samt")
	@Column
	private double samt;

	@JsonProperty("csamt")
	@Column
	private double csamt;

}
