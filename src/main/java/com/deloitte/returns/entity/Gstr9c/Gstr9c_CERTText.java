package com.deloitte.returns.entity.Gstr9c;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cert_text", schema = "gstr9c")
public class Gstr9c_CERTText {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("cert_text_partb1")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_text_partb1_id")
	private Gstr9c_Partb1 certTextPartb1;

	@JsonProperty("cert_text_partb2")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_text_partb2_id")
	private Gstr9c_Partb2 certTextPartb2;

}
