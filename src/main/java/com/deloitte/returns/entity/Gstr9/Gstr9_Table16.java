package com.deloitte.returns.entity.Gstr9;

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
@Table(name = "table16", schema = "gstr9")
public class Gstr9_Table16 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("comp_supp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comp_supp_id")
	private Gstr9_CompSupp compSupp;

	@JsonProperty("deemed_supp")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "deemed_supp_id")
	private Gstr9_DeemedSupp deemedSupp;

	@JsonProperty("not_returned")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "not_returned_id")
	private Gstr9_NotReturned notReturned;

}