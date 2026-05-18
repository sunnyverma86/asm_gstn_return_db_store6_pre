package com.deloitte.returns.entity.Gstr9;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Supplies received from Composition taxpayers
 */
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
@Table(name = "comp_supp", schema = "gstr9")
@Entity
public class Gstr9_CompSupp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("txval")
	private Double txval;
	
	@JsonProperty("intr")
	private Double intr;

}
