
package com.deloitte.returns.entity.Gstr8;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Tax paid details
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "pd_by_cash" })
@Entity
@Table(name = "tax_paid", schema = "gstr8")
@Data
public class Gstr8_TaxPaid implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * IGST amount paid
	 *
	 */
	@JsonProperty("iamt")
	@JsonPropertyDescription("IGST amount paid")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "iamt_id")
	public Gstr8_Iamt iamt;

	/**
	 * SGST amount paid
	 *
	 */
	@JsonProperty("samt")
	@JsonPropertyDescription("SGST amount paid")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "samt_id")
	public Gstr8_Samt samt;

	/**
	 * CGST amount paid
	 *
	 */
	@JsonProperty("camt")
	@JsonPropertyDescription("CGST amount paid")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "camt_id")
	public Gstr8_Camt camt;

	/**
	 * Liability identifier
	 *
	 */
	@JsonProperty("liab_id")
	@JsonPropertyDescription("Liability identifier")
	@Column
	public Double liabId;

	/**
	 * Debit number
	 *
	 */
	@JsonProperty("debit_id")
	@JsonPropertyDescription("Debit number")
	@Column
	public String debitId;

	@JsonProperty("trancd")
	@Column
	public Double trancd;

	@JsonProperty("trandate")
	@Column
	public String trandate;

	@JsonProperty("pd_by_cash")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_id")
	public List<Gstr8_PdByCash> pdByCash = new ArrayList<Gstr8_PdByCash>();

	private final static long serialVersionUID = -26434583035395827L;

}
