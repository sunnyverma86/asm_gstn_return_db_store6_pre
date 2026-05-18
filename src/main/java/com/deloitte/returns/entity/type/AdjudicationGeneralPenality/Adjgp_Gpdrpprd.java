package com.deloitte.returns.entity.type.AdjudicationGeneralPenality;

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
@Table(name = "gpdrpprd", schema = "adjudication_general_penality")
public class Adjgp_Gpdrpprd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("gpdrpprddata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpdrpprddata_id")
	private Adjgp_Gpdrpprddata gpdrpprddata;

	@JsonProperty("gpscndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gpscndata_id")
	private Adjgp_Gpscndata gpscndata;

}
