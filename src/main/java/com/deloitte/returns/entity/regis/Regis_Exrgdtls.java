package com.deloitte.returns.entity.regis;

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
@Entity
@Table(name = "exrgdtls", schema = "regis")
public class Regis_Exrgdtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("regdt")
	private String regdt;

	@JsonProperty("regnum")
	private String regnum;

	@JsonProperty("regtyp")
	private String regtyp;
	
	@JsonProperty("othrReg")
	private String othrReg;
	

}
