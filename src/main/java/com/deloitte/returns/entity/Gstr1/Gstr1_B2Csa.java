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
@Table(name = "b2csa", schema = "gstr1")
@Entity
public class Gstr1_B2Csa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum; // added

	@JsonProperty("diff_percent")
	private Double diffPercent;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("omon")
	private String omon;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2csa_id")
	private List<Gstr1_Itms> itms;

	@JsonProperty("stin")
	private String stin;

	@JsonProperty("sply_ty")
	private String splyTy;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("rtin")
	private String rtin;

}
