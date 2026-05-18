package com.deloitte.returns.entity.pmt;

import java.util.List;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tax_paid", schema = "pmt")
public class Pmt_TaxPaid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("pd_by_cash")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private List<Pmt_PdByCash> pdByCash;

	@JsonProperty("pditc")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pditc_id")
	private Pmt_Pditc pditc;

}