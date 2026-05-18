package com.deloitte.returns.entity.type.AdjudicationTaxCollectedButNotDeposited;

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
@Entity
@Table(name = "tcproceeding", schema = "adjudication_tax_collected_but_not_deposited")
public class Adjnd_Tcproceeding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemName")
	private String itemName;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("tcdropprodata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tcdropprodata_id")
	private Adjnd_Tcdropprodata tcdropprodata;

}
