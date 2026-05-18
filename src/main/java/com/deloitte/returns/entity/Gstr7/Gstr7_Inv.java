package com.deloitte.returns.entity.Gstr7;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inv",schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"gstin_ded","deductee_name","inum","idt","ival", "amt_ded", "iamt", "camt", "samt","flag","chksum","source","act_tkn","oinum","oidt","oival","oamt_ded" })
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Gstr7_Inv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("gstin_ded")
	@Column(name = "gstin_ded")
	public String gstinDed;
	
	@JsonProperty("deductee_name")
	@Column(name = "deductee_name")
	public String deducteeName;


	@JsonProperty("inum")
	@Column(name = "inum")
	public String inum;

	@JsonProperty("idt")
	@Column(name = "idt")
	public String idt;
	
	@JsonProperty("ival")
	@Column(name = "ival")
	public Double ival;

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
	
	@JsonProperty("flag")
	@Column(name = "flag")
	public String flag;

	@JsonProperty("chksum")
	@Column(name = "chksum")
	public String chksum;
	
	//new add
	@JsonProperty("source")  
	@Column(name = "source")
	public String source;

	@JsonProperty("act_tkn")
	@Column(name = "act_tkn")
	public String act_tkn;
	
	@JsonProperty("oinum")
	@Column(name = "oinum")
	public String oinum;

	@JsonProperty("oidt")
	@Column(name = "oidt")
	public String oidt;
	
	@JsonProperty("oival")
	@Column(name = "oival")
	public Double oival;

	@JsonProperty("oamt_ded")
	@Column(name = "oamt_ded")
	public Double oamt_ded;
	
	
	

	

}
