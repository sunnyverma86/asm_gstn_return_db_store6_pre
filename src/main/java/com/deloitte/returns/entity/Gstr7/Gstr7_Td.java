package com.deloitte.returns.entity.Gstr7;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "td",schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstin_ded", "deductee_name", "amt_ded", "iamt", "camt", "samt", "chksum" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7_Td {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("gstin_ded")
	@Column(name = "gstin_ded")
	public String gstinDed;

	@JsonProperty("deductee_name")
	@Column(name = "deductee_name")
	public String deducteeName;

	@JsonProperty("amt_ded")
	@Column(name = "amt_ded")
	public Double amtDed;

	@JsonProperty("iamt")
	@Column(name = "iamt")
	public Double iamt;

	@JsonProperty("camt")
	@Column(name = "camt")
	public Double camt;

	@JsonProperty("samt")
	@Column(name = "samt")
	public Double samt;

	@JsonProperty("chksum")
	@Column(name = "chksum")
	public String chksum;
	
	//new add
	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "td_id")
	private List<Gstr7_Inv> inv;
	
	
	
	

}
