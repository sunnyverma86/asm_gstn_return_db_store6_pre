package com.deloitte.returns.entity.Gstr9;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.fasterxml.jackson.annotation.*;

/**
 * Total ITC to be lapsed in current financial year (E + F + J)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tot_itc_lapsed", schema = "gstr9")
@Entity
public class Gstr9_TotItcLapsed {

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