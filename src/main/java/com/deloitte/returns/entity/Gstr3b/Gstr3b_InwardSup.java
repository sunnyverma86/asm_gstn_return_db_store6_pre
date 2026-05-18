
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
@JsonPropertyOrder({ "isup_details" })

@Entity
@Data
@Table(name = "inward_sup", schema = "gstr3b")
public class Gstr3b_InwardSup implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("isup_details")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inward_sup_id")
	private List<Gstr3b_IsupDetail> isupDetails = new ArrayList<Gstr3b_IsupDetail>();

	private final static long serialVersionUID = -9053571403437750083L;

}
