package com.deloitte.returns.entity.type.AdjudicationUnregisteredPersons;

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
@Table(name = "iaplorder", schema = "adjudication_unregistered_persons")
public class Adjur_Iaplorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refdt")
	private String refdt;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("aplorderddata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aplorderddata_id")
	private Adjur_Aplorderddata aplorderddata;

}