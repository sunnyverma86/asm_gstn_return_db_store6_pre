package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "tax_paid", schema = "gstr9a")
@Entity
public class Gstr9a_TaxPaid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("pd_by_cash")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	private List<Gstr9a_PDByCash> pdByCash;

}