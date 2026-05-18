package com.deloitte.returns.entity.Gstr1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urp2ca", schema = "gstr1")
@Entity
public class Gstr1_Urp2ca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("csamt")
	private Integer csamt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("rt")
	private Integer rt;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("pos")
	private String pos;
	
	@JsonProperty("omon")//new add
	private String omon;

	@JsonProperty("txval")
	private Integer txval;

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("iamt")
	private Integer iamt;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("sply_ty")
	private String splyTy;
	
	//new add
	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "urp2ca_id")
	private List<Gstr1_Itms> itms;

	
	
}
