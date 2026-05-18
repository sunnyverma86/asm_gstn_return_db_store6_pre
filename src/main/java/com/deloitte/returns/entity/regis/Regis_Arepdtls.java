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
@Table(name = "arepdtls", schema = "regis")
public class Regis_Arepdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("mn")
	private String mn;

	@JsonProperty("ln")
	private String ln;

	@JsonProperty("sts")
	private String sts;

	@JsonProperty("mbno")
	private String mbno;

	@JsonProperty("typAR")
	private String typAR;

	@JsonProperty("fn")
	private String fn;

	@JsonProperty("em")
	private String em;
	
	@JsonProperty("enrlnum")
	private String enrlnum;
	
	@JsonProperty("tlphno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tlphno_id")
	private Regis_Tlphno tlphno;
	
	
	@JsonProperty("fxno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fxno_id")
	private Regis_Fxno fxno;
	
	
	

	@JsonProperty("uid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid_id")
	private Regis_Uid uid;

	@JsonProperty("pan")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pan_id")
	private Regis_Pan pan;

}
