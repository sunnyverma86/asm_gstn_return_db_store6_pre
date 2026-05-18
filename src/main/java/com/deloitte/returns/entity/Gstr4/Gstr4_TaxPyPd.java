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
@Table(name = "tax_py_pd", schema = "gstr4")
@Entity
public class Gstr4_TaxPyPd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("tax_pay")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_TaxPay> taxPay;

	@JsonProperty("pd_by_cash")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr4_id")
	private List<Gstr4_PdByCash> PdByCash;
}
