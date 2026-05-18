package com.deloitte.returns.entity.type.AdjudicationGeneralPenality;

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
@Table(name = "items", schema = "adjudication_general_penality")
public class Adjgp_Items {

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

	@JsonProperty("dtorderdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dtorderdata_id")
	private Adjgp_Dtorderdata dtorderdata;

	@JsonProperty("addIntimationData")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addIntimationData_id")
	private Adjgp_AddIntimationData addIntimationData;

	@JsonProperty("gpreply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjgp_Gpreply> gpreply;

	@JsonProperty("gpscn")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjgp_Gpscn> gpscn;

	@JsonProperty("gpdrpprd")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjgp_Gpdrpprd> gpdrpprd;
	
	@JsonProperty("gporder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjgp_Gporder> gporder;

}