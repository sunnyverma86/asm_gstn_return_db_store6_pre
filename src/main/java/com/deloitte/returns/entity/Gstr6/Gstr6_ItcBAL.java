package com.deloitte.returns.entity.Gstr6;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itc_bal", schema = "gstr6")
@Entity
public class Gstr6_ItcBAL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("ret_period")
	private String retPeriod;

	@JsonProperty("elgitc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "elgitc_id")
	private Gstr6_Elgitc elgitc;

	@JsonProperty("inelgitc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inelgitc_id")
	private Gstr6_Inelgitc inelgitc;

	@JsonProperty("isdItcCross")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isdItcCross_id")
	private Gstr6_IsdItcCross isdItcCross;

	@JsonProperty("totalItc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "totalItc_id")
	private Gstr6_TotalItc totalItc;

}