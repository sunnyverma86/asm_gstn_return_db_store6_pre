package com.deloitte.returns.entity.type.AdjudicationDeterminationTax;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "dtreply", schema = "adjudication_determination_tax")
public class Adjadt_Dtreply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="item_name")
	@JsonProperty("itemName")
	private String itemName;
	
	@Column(name="item_names")
	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("dtreplydata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtreplydata_id")
	private Adjadt_Dtreplydata dtreplydata;

}