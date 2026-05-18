package com.deloitte.returns.entity.Gstr9c;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cert_text_partb1", schema = "gstr9c")
public class Gstr9c_Partb1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("qualifications")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_text_partb1_id")
	private List<Gstr9c_Qualifications> qualifications;

	@JsonProperty("section1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "section1_id")
	private Gstr9c_Section1 section1;

	@JsonProperty("section2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "section2_id")
	private Gstr9c_Section2 section2;

	@JsonProperty("section3")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "section3_id")
	private Gstr9c_Section3 section3;

	@JsonProperty("section4")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "section4_id")
	private Gstr9c_Section4 section4;

	@JsonProperty("section5")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "section5_id")
	private Gstr9c_Section5 section5;

	@JsonProperty("signature")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "signature_id")
	private Gstr9c_Signature signature;

}
