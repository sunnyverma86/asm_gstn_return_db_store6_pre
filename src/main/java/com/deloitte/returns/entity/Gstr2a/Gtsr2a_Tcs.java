
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sup_gstin", "sup_name", "month", "supR", "retsupR", "grossSup", "grossRetSup", "supU", "retsupU",
		"amt", "gstr9Camt", "gstr9Samt", "gstr9Iamt" })

@Entity
@Table(name = "tcs", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Gtsr2a_Tcs implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("sup_gstin")
	@Column
	public String supGstin;
	/**
	 * name of Supplier
	 * 
	 */
	@JsonProperty("sup_name")
	@JsonPropertyDescription("name of Supplier")
	@Column
	public String supName;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("month")
	@Column
	public String month;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("supR")
	@Column
	public Double supR;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("retsupR")
	@Column
	public Double retsupR;
	@JsonProperty("grossSup")
	@Column
	public Double grossSup;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("grossRetSup")
	@Column
	public Double grossRetSup;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("supU")
	@Column
	public Double supU;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("retsupU")
	@Column
	public Double retsupU;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("amt")
	@Column
	public Double amt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("Camt")
	@Column
	public Double camt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("Samt")
	@Column
	public Double samt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("Iamt")
	@Column
	public Double iamt;

	@ManyToOne
	@JoinColumn(name = "gstr2a_id")
	private Gstr2a gstr2a;

	private final static long serialVersionUID = 3965659678331775820L;

}
