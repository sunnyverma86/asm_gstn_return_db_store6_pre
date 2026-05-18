package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "txos", schema = "gstr4")
@Entity
public class Gstr4_Txo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("rt")
	private double rt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("trnovr")
	private long trnovr;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "txos_id")
	private List<Gstr4_Itms> itms;

	@JsonProperty("chksum")
	private String chksum;

}
