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
@Table(name = "b2ca", schema = "gstr1")
@Entity
public class Gstr1_B2Ca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum; // added

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("diff_percent")
	private Double diffPercent;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("rt")
	private Double rt;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("sply_ty")
	private String splyTy;

	@JsonProperty("stin")
	private String stin;

	@JsonProperty("rtin")
	private String rtin;

	@JsonProperty("posItms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ata_id")
	private List<Gstr1_PosItm> posItms;

}
