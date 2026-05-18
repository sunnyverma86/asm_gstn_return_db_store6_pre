package com.deloitte.returns.entity.type.AdjudicationTaxCollectedButNotDeposited;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items", schema = "adjudication_tax_collected_but_not_deposited")
public class Adjnd_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "item_name")
	@JsonProperty("itemName")
	private String itemName;

	@Column(name = "item_names")
	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("addIntimationData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addIntimationData_id")
	private Adjnd_AddIntimationData addIntimationData;

	@JsonProperty("tcnoticedrc01data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tcnoticedrc01data_id")
	private Adjnd_Tcnoticedrc01Data tcnoticedrc01Data;

	@JsonProperty("tcorderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tcorderdata_id")
	private Adjnd_Tcorderdata tcorderdata;

	@JsonProperty("tcproceeding")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjnd_Tcproceeding> tcproceeding;

	@JsonProperty("tcreply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjnd_Tcreply> tcreply;

}