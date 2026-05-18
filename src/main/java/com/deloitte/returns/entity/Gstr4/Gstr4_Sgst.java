package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "sgst", schema = "gstr4")
@Entity
public class Gstr4_Sgst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("intr")
	private long intr;

	@JsonProperty("oth")
	private long oth;

	@JsonProperty("tx")
	private long tx;

	@JsonProperty("fee")
	private long fee;

	@JsonProperty("tot")
	private long tot;

	@JsonProperty("pen")
	private long pen;

}
