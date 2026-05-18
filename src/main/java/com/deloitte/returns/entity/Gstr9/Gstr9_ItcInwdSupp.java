package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.*;

/**
 * ITC on inward supplies (other than imports and inward supplies liable to reverse charge
 * but includes services received from SEZs) received during 2017-18 but availed during
 * April to September, 2018
 */

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
@Table(name = "itc_inwd_supp", schema = "gstr9")
@Entity
public class Gstr9_ItcInwdSupp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;
}