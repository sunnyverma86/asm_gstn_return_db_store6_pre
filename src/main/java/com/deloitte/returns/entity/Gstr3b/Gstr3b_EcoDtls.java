
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "eco_sup", "eco_reg_sup" })

@Entity
@Data
@Table(name = "eco_dtls", schema = "gstr3b")
public class Gstr3b_EcoDtls implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("eco_sup")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "eco_sup_id")
	private Gstr3b_EcoSup ecoSup;

	@JsonProperty("eco_reg_sup")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "eco_reg_sup_id")
	private Gstr3b_EcoRegSup ecoRegSup;

	private final static long serialVersionUID = -3669651389125083085L;

}
