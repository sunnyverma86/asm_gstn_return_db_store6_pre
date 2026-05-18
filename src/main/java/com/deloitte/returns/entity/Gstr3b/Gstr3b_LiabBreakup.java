
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ret_period", "liability" })

@Entity
@Data
@Table(name = "liab_breakup", schema = "gstr3b")
public class Gstr3b_LiabBreakup implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Return period of breakup
	 * 
	 */
	@JsonProperty("ret_period")
	@JsonPropertyDescription("Return period of breakup ")
	@Column
	public String retPeriod;

	@JsonProperty("liability")
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "liability_id")
	public Gstr3b_Liability liability;

	private final static long serialVersionUID = 8546572116126551721L;

}
