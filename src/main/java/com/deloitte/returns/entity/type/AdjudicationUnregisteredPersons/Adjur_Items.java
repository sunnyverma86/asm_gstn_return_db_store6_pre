package com.deloitte.returns.entity.type.AdjudicationUnregisteredPersons;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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
@Table(name = "items", schema = "adjudication_unregistered_persons")
public class Adjur_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("remandae")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjur_Remandae> remandae;

	@JsonProperty("iaplorder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjur_Iaplorder> iaplorder;

	@JsonProperty("rcorderdrc")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjur_Rcorderdrc> rcorderdrc;

	@JsonProperty("rcorder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjur_Rcorder> rcorder;

	@JsonProperty("remandedReply")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjur_RemandedReply> remandedReply;
	
	@JsonProperty("upnoticedata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "upnoticedata_id")
	private Adjur_Upnoticedata upnoticedata;
	
	@JsonProperty("uporder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjur_Uporder> uporder;



}
