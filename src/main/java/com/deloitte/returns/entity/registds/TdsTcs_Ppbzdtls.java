
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
@Table(name = "ppbzdtls", schema = "registds")
public class TdsTcs_Ppbzdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("anystgst")
	public String anystgst;

	@JsonProperty("ieccode")
	public String ieccode;

	@JsonProperty("othstgst")
	public String othstgst;

	@JsonProperty("psnt")
	public String psnt;

	@JsonProperty("add")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "add_id")
	public TdsTcs_Add add;

	@JsonProperty("contdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contdtls_id")
	public TdsTcs_Contdtls contdtls;

	@JsonProperty("dcupdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ppbzdtls_id")
	public List<TdsTcs_Dcupdtl> dcupdtls;

	@JsonProperty("eid")
	public String eid;

}
