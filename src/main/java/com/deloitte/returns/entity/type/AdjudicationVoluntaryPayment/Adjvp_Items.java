package com.deloitte.returns.entity.type.AdjudicationVoluntaryPayment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
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
@Table(name = "items", schema = "adjudication_voluntary_payment")
public class Adjvp_Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;
	
	@JsonProperty("refid")
	private String refid;
	
	@JsonAlias({"vpappdatamfy", "vpappdata"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_vpappdatamfy_id")
	private Adjvp_Vpappdata vpappdatamfy;


//	@JsonProperty("vpappdatamfy")
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "items_vpappdatamfy_id")
//	private Adjvp_Vpappdata vpappdatamfy;

	@JsonProperty("vpack")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjvp_Vpack> vpack;

	@JsonProperty("vpdropProceeding")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Adjvp_VpdropProceeding> vpdropProceeding;

}