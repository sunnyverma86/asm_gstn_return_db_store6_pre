
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
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "unreg_details", "comp_details", "uin_details" })

@Entity
@Data
@Table(name = "inter_sup", schema = "gstr3b")
public class Gstr3b_InterSup implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("unreg_details")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inter_sup_id")
	private List<Gstr3b_UnregDetail> unregDetails = new ArrayList<>();

	@JsonProperty("comp_details")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inter_sup_id")
	private List<Gstr3b_CompDetail> compDetails = new ArrayList<Gstr3b_CompDetail>();

	@JsonProperty("uin_details")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inter_sup_id")
	private List<Gstr3b_UinDetail> uinDetails = new ArrayList<Gstr3b_UinDetail>();

	private final static long serialVersionUID = 4698165250918749141L;

}
