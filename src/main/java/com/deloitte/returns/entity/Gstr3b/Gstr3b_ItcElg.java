
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "itc_avl", "itc_rev", "itc_net", "itc_inelg" })

@Entity
@Data
@Table(name = "itc_elg", schema = "gstr3b")
public class Gstr3b_ItcElg implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itc_avl")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	private List<Gstr3b_ItcAvl> itcAvl = new ArrayList<Gstr3b_ItcAvl>();

	@JsonProperty("itc_rev")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	private List<Gstr3b_ItcRev> itcRev = new ArrayList<Gstr3b_ItcRev>();

	@JsonProperty("itc_net")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "itc_net_id")
	private Gstr3b_ItcNet itcNet;

	@JsonProperty("itc_inelg")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "itc_elg_id")
	private List<Gstr3b_ItcInelg> itcInelg = new ArrayList<Gstr3b_ItcInelg>();

	private final static long serialVersionUID = -531859624448229897L;

}
