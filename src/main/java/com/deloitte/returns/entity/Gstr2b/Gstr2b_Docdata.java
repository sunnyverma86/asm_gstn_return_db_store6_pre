package com.deloitte.returns.entity.Gstr2b;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "docdata", schema = "gstr2b")
@Entity
public class Gstr2b_Docdata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_B2B> b2B;

	@JsonProperty("b2ba")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_B2Ba> b2Ba;

	@JsonProperty("cdnr")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Cdnr> cdnr;

	@JsonProperty("cdnra")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Cdnra> cdnra;

	@JsonProperty("ecom")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Ecom> ecom;

	@JsonProperty("ecoma")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Ecoma> ecoma;

	@JsonProperty("impg")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Impg> impg;

	@JsonProperty("impgsez")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Impgsez> impgsez;

	@JsonProperty("isd")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Isd> isd;

	@JsonProperty("isda")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docdata_id")
	private List<Gstr2b_Isda> isda;

}