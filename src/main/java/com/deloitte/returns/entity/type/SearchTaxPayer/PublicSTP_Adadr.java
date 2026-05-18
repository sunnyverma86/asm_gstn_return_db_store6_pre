package com.deloitte.returns.entity.type.SearchTaxPayer;

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
@Table(name = "adadr", schema = "search_tax_payer")
public class PublicSTP_Adadr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("ntr")
	private String ntr;

	@JsonProperty("addr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private PublicSTP_Addr addr;

	

}