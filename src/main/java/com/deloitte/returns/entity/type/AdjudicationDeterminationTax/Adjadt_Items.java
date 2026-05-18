package com.deloitte.returns.entity.type.AdjudicationDeterminationTax;

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
@Table(name = "items", schema = "adjudication_determination_tax")
public class Adjadt_Items {

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

	@JsonProperty("dtnoticedrc01data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtnoticedrc01data_id")
	private Adjadt_Dtnoticedrc01Data dtnoticedrc01Data;

	@JsonProperty("dtnoticedrc02data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtnoticedrc02data_id")
	private Adjadt_Dtnoticedrc02Data dtnoticedrc02Data;

	@JsonProperty("dtorderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorderdata_id")
	private Adjadt_Dtorderdata dtorderdata;

	@JsonProperty("addIntimationData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addIntimationData_id")
	private Adjadt_AddIntimationData addIntimationData;

	@JsonProperty("dtproceeding")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjadt_Dtproceeding> dtproceeding;

	@JsonProperty("dtreply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjadt_Dtreply> dtreply;

	@JsonProperty("gpreply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjadt_Gpreply> gpreply;

	@JsonProperty("gpscn")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjadt_Gpscn> gpscn;

	@JsonProperty("gporder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjadt_Gporder> gporder;

}