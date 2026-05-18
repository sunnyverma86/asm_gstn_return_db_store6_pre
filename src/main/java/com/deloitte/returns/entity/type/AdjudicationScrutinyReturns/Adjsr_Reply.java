package com.deloitte.returns.entity.type.AdjudicationScrutinyReturns;

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
@Table(name = "reply", schema = "adjudication_scrutiny_returns")
public class Adjsr_Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("reason")
	@Column(length = 4000)
	private String reason;

	@JsonProperty("replyty")
	private String replyty;

	@JsonProperty("fy")
	private String fy;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Adjsr_Decdtls decdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private List<Adjsr_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private List<Adjsr_Suppdocs> suppdocs;
	
	@JsonProperty("annxdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private List<Adjsr_Annxdocs> annxdocs;
	
	@JsonProperty("pymtdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private List<Adjsr_Pymtdtls> pymtdtls;

}