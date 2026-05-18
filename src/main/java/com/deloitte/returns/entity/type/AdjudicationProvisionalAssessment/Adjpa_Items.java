package com.deloitte.returns.entity.type.AdjudicationProvisionalAssessment;

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
@Table(name = "items", schema = "adjudication_provisional_assessment")
public class Adjpa_Items {

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

	@JsonProperty("paappdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paappdata_id")
	private Adjpa_Paappdata paappdata;

	@JsonProperty("panotcerlssecdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "panotcerlssecdata_id")
	private Adjpa_Panotcerlssecdata panotcerlssecdata;

	@JsonProperty("paordrejrlssecdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paordrejrlssecdata_id")
	private Adjpa_Paordrejrlssecdata paordrejrlssecdata;

	@JsonProperty("paordrlssec09data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paordrlssec09data_id")
	private Adjpa_Paordrlssec09data paordrlssec09Data;

	@JsonProperty("paordfinass07data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paordfinass07data_id")
	private Adjpa_Paordfinass07Data paordfinass07Data;

	@JsonProperty("paordsecaccptdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paordsecaccptdata_id")
	private Adjpa_Paordsecaccptdata paordsecaccptdata;

	@JsonProperty("panotceadinf06")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Panotceadinf06> panotceadinf06;

	@JsonProperty("panotceadinf02")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Panotceadinf02> panotceadinf02;

	@JsonProperty("paordproass04")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Paordproass04> paordproass04;

	@JsonProperty("paordrejordr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Paordrejordr> paordrejordr;

	@JsonProperty("paordsecmodi")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Paordsecmodi> paordsecmodi;

	@JsonProperty("paordsecresub")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Paordsecresub> paordsecresub;

	@JsonProperty("pareplyrlssec")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Pareplyrlssec> pareplyrlssec;

	@JsonProperty("pareplyadinf06")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Pareplyadinf06> pareplyadinf06;

	@JsonProperty("pareplyadinf02")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Pareplyadinf02> pareplyadinf02;

	@JsonProperty("pafursec")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Pafursec> pafursec;

	@JsonProperty("pasubmodisec")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Pasubmodisec> pasubmodisec;

	@JsonProperty("paresubsec")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Paresubsec> paresubsec;

	@JsonProperty("parlssec")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjpa_Parlssec> parlssec;

}