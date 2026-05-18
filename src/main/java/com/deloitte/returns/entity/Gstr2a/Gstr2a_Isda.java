
package com.deloitte.returns.entity.Gstr2a;

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
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cfs", "ctin", "doclist" })

@Entity
@Table(name = "isda", schema = "gstr2a")
@Data
public class Gstr2a_Isda implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * counter party filing status
	 * 
	 */
	@JsonProperty("cfs")
	@JsonPropertyDescription("counter party filing status")
	@Column
	public String cfs;
	@JsonProperty("ctin")
	@Column
	public String ctin;
	@JsonProperty("doclist")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "isda_id")
	public List<Gstr2a_Doc> doclist = new ArrayList<Gstr2a_Doc>();

	private final static long serialVersionUID = 2821088638592197367L;

}
