package com.deloitte.returns.entity.Gstr9c;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cgst", schema = "gstr9c")

public class Gstr9c_Cgst {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("tx")
	private Double tx;
	
	@JsonProperty("intr")
	private Double intr;

	@JsonProperty("pen")
	private Double pen;

	@JsonProperty("fee")
	private Double fee;

	@JsonProperty("oth")
	private Double oth;

	@JsonProperty("tot")
	private Double tot;


}
