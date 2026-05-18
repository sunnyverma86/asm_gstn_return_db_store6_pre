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
@Table(name = "certificate", schema = "gstr9c")
public class Gstr9c_Certificate {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonProperty("cert_data")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_data_id")
	private Gstr9c_CERTData certData;

	@JsonProperty("cert_text")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cert_text_id")
	private Gstr9c_CERTText certText;

}
