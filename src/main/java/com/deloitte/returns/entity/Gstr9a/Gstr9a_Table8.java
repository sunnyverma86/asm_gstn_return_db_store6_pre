package com.deloitte.returns.entity.Gstr9a;

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
@Table(name = "table8", schema = "gstr9a")
@Entity
public class Gstr9a_Table8 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("b2b")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2b_id")
	private Gstr9a_B2B b2B;

	@JsonProperty("impg")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "impg_id")
	private Gstr9a_Impg impg;

}