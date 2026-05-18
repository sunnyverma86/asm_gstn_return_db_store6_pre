package com.deloitte.returns.entity.regis;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bzdtls", schema = "regis")
public class Regis_Bzdtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("bzdtlsbz")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bzdtlsbz_id")
	private Regis_Bzdtlsbz bzdtlsbz;

	@JsonProperty("exrgdtls")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bzdtls_id")
	private List<Regis_Exrgdtls> exrgdtls;

}
