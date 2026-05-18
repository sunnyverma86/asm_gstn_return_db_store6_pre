package com.deloitte.returns.entity.type.AdjudicationDeterminationTax;

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
@Table(name = "gpscn", schema = "adjudication_determination_tax")
public class Adjadt_Gpscn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("gpscndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscndata_id")
	private Adjadt_Gpscndata gpscndata;

	@JsonProperty("refid")
	private String refid;

}
