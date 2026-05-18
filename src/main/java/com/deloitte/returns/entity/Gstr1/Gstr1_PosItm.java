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
@Table(name = "posItms", schema = "gstr1")
@Entity
public class Gstr1_PosItm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "posItms_id")
	private List<Gstr1_Itms> itms;

	@JsonProperty("stin")
	private String stin;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("ostin")
	private String ostin;

	@JsonProperty("omon")
	private String omon;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("sply_ty")
	private String splyTy;

}
