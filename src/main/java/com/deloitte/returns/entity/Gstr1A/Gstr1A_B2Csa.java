package com.deloitte.returns.entity.Gstr1A;

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
@Table(name = "b2csa", schema = "gstr1a")
@Entity
public class Gstr1A_B2Csa {

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
	private List<Gstr1A_Itms> itms;

	@JsonProperty("sply_ty")
	private String splyTy;

	@JsonProperty("typ")
	private String typ;

}
