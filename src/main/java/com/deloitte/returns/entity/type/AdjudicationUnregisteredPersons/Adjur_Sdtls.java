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
@Table(name = "sdtls", schema = "adjudication_unregistered_persons")
public class Adjur_Sdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("aplorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aplorder_id")
	private Adjur_Aplorder aplorder;

	@JsonProperty("paorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paorder_id")
	private Adjur_Paorder paorder;

	@JsonProperty("aedrcorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aedrcorder_id")
	private Adjur_Aedrcorder aedrcorder;

	@JsonProperty("aescn")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "aescn_id")
	private Adjur_Aescn aescn;

	@JsonProperty("remandedordr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "remandedordr_id")
	private Adjur_Remandedordr remandedordr;
	
	@JsonProperty("urscnwph")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "urscnwph_id")
	private Adjur_Urscnwph urscnwph;
	
	@JsonProperty("urorder")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "urorder_id")
	private Adjur_Urorder urorder;



}