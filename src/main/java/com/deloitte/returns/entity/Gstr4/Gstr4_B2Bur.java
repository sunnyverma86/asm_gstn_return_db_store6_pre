package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "b2bur", schema = "gstr4")
@Entity
public class Gstr4_B2Bur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2bur_id")
	private List<Gstr4_B2BurInv> inv;

	@JsonProperty("itms")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2bur_id")
	private List<Gstr4_Itms> itms;

	@JsonProperty("val")
	private long val;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("sply_ty")
	private String splyTy;

}
