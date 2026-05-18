package com.deloitte.returns.entity.pmt;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table3", schema = "pmt")
public class Pmt_Table3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_id")
	private Pmt_Itc itc;

	@JsonProperty("nonrev")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nonrev_id")
	private Pmt_NonReverse nonrev;

	@JsonProperty("rev")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rev_id")
	private Pmt_ReverseCharge rev;

}