package com.deloitte.returns.entity.Gstr2b;

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
@Table(name = "itcunavl", schema = "gstr2b")
@Entity
public class Gstr2b_Itcunavl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("imports")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imports_id")
	private Gstr2b_Imports imports;

	@JsonProperty("isdsup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isdsup_id")
	private Gstr2b_Isdsup isdsup;

	@JsonProperty("nonrevsup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nonrevsup_id")
	private Gstr2b_Nonrevsup nonrevsup;

	@JsonProperty("othersup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "othersup_id")
	private Gstr2b_Othersup othersup;

	@JsonProperty("revsup")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "revsup_id")
	private Gstr2b_Revsup revsup;

}