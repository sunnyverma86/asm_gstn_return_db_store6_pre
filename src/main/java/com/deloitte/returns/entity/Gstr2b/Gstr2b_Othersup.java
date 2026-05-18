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
@Table(name = "othersup", schema = "gstr2b")
@Entity
public class Gstr2b_Othersup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cess")
	private Double cess;

	@JsonProperty("cgst")
	private Double cgst;

	@JsonProperty("igst")
	private Double igst;

	@JsonProperty("sgst")
	private Double sgst;

	@JsonProperty("cdnr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnr_id")
	private Gstr2b_Cdnr cdnr;

	@JsonProperty("cdnra")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnra_id")
	private Gstr2b_Cdnra cdnra;

	@JsonProperty("cdnrarev")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnrarev_id")
	private Gstr2b_Cdnrarev cdnrarev;

	@JsonProperty("cdnrrev")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdnrrev_id")
	private Gstr2b_Cdnrrev cdnrrev;

	@JsonProperty("isd")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isd_id")
	private Gstr2b_Isd isd;

	@JsonProperty("isda")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isda_id")
	private Gstr2b_Isda isda;

}