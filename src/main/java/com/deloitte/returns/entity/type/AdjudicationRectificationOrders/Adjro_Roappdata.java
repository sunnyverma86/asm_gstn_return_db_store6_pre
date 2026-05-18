package com.deloitte.returns.entity.type.AdjudicationRectificationOrders;

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
@Table(name = "roappdata", schema = "adjudication_rectification_orders")
public class Adjro_Roappdata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ordrdt")
	private String ordrdt;

	@JsonProperty("ordrno")
	private String ordrno;

	@JsonProperty("rsn")
	@Column(length = 2000)
	private String rsn;

	@JsonProperty("sec")
	private String sec;

	@JsonProperty("ovtp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ovtp_id")
	private Adjro_Ovtp ovtp;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private Adjro_Decdtls decdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "roappdata_id")
	private List<Adjro_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "roappdata_id")
	private List<Adjro_Suppdocs> suppdocs;

}