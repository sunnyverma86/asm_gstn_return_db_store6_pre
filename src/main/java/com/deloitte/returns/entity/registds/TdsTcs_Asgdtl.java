
package com.deloitte.returns.entity.registds;

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
@Table(name = "asgdtls", schema = "registds")
public class TdsTcs_Asgdtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ispas")
	private String ispas;// new

	@JsonProperty("mbrId")
	private String mbrId;// new

	@JsonProperty("dg")
	public String dg;

	@JsonProperty("dob")
	public String dob;

	@JsonProperty("eid")
	public String eid;

	@JsonProperty("em")
	public String em;

	@JsonProperty("fhfn")
	public String fhfn;

	@JsonProperty("fhln")
	public String fhln;

	@JsonProperty("fhmn")
	public String fhmn;

	@JsonProperty("fn")
	public String fn;

	@JsonProperty("gd")
	public String gd;

	@JsonProperty("iscitind")
	public String iscitind;

	@JsonProperty("ln")
	public String ln;

	@JsonProperty("mbno")
	public String mbno;

	@JsonProperty("mn")
	public String mn;

	@JsonProperty("ppno")
	public String ppno;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "asgdtls_id")
	public List<TdsTcs_Dcupdtl> dcupdtls;

	@JsonProperty("adhdcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "asgdtls_id")
	public List<TdsTcs_Adhdcupdtl> adhdcupdtls;

	@JsonProperty("din")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "din_id")
	public TdsTcs_Din din;

	@JsonProperty("pan")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pan_id")
	public TdsTcs_Pan pan;

	@JsonProperty("rsad")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rsad_id")
	public TdsTcs_Rsad rsad;

	@JsonProperty("tlphno")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tlphno_id")
	public TdsTcs_Tlphno tlphno;

	@JsonProperty("uid")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid_id")
	public TdsTcs_Uid uid;

}
