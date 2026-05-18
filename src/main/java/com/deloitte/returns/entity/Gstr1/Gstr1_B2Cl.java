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
@Table(name = "b2cl", schema = "gstr1")
@Entity
public class Gstr1_B2Cl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("inv")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "b2cl_id")
	private List<Gstr1_Inv> inv;

	@JsonProperty("stin")
	private String stin;

	@JsonProperty("rtin")
	private String rtin;

}
