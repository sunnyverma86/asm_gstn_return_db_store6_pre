package com.deloitte.returns.entity.Gstr9a;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sgst", schema = "gstr9a")
@Entity
public class Gstr9a_Sgst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("fee")
	private long fee;

	@JsonProperty("intr")
	private long intr;

	@JsonProperty("oth")
	private long oth;

	@JsonProperty("pen")
	private long pen;

	@JsonProperty("tot")
	private long tot;

	@JsonProperty("tx")
	private long tx;
	
	@JsonProperty("txval")
	private long txval;
}