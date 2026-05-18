package com.deloitte.returns.entity.type.AdjudicationNonfilersReturns;

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
@Table(name = "nfproceeding", schema = "adjudication_nonfilers_returns")
public class Adjnf_Nfproceeding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemName")
	private String itemNames;
	
	@JsonProperty("itemname")
	private String itemName;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("nfdropprodata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nfdropprodata_id")
	private Adjnf_Nfdropprodata nfdropprodata;

}
