package com.deloitte.returns.entity.regis;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ppbzdtls", schema = "regis")
public class Regis_Ppbzdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("hasaddl")
	private String hasaddl;

	@JsonProperty("ctjd")
	private String ctjd;

	@JsonProperty("ntbz")
	private List<String> ntbz;

	@JsonProperty("psnt")
	private String psnt;

	@JsonProperty("stjd")
	private String stjd;
	
	@JsonProperty("otherntbz")
	private String otherntbz;
	
	
	
	@JsonProperty("gcadr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gcadr_id")
	private Regis_Gcadr gcadr;
	

	@JsonProperty("contdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contdtls_id")
	private Regis_Contdtls contdtls;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ppbzdtls_id")
	public List<Regis_Dcupdtls> dcupdtls;

	@JsonProperty("add")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_id")
	private Regis_Add add;

}
