package com.deloitte.returns.entity.regis;

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
@Table(name = "contdtls", schema = "regis")
public class Regis_Contdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("mbno")
	private String mbno;

	@JsonProperty("em")
	private String em;
	
	
	
	@JsonProperty("tlphno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tlphno_id")
	private Regis_Tlphno tlphno;
	
	@JsonProperty("fxno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fxno_id")
	private Regis_Fxno fxno;
	
	

}
