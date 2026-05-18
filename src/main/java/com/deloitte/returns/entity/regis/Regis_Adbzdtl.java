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
@Table(name = "adbzdtls", schema = "regis")
@Entity
public class Regis_Adbzdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ntbz")
	private List<String> ntbz;

	@JsonProperty("psnt")
	private String psnt;

	@JsonProperty("eid")
	private String eid;
	
	
	@JsonProperty("otherntbz")
	private String otherntbz;
	

	@JsonProperty("add")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_id")
	private Regis_Add add;

	@JsonProperty("contdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Contdtls_id")
	private Regis_Contdtls contdtls;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "adbzdtls_id")
	private List<Regis_Dcupdtls> dcupdtls;
	
    @JsonProperty("gcadr")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gcadr_id")
	private Regis_Gcadr gcadr;

}
