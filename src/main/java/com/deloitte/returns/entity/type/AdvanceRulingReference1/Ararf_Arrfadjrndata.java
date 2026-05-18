package com.deloitte.returns.entity.type.AdvanceRulingReference1;
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
@Table(name = "arrfadjrndata", schema = "advance_ruling_reference")

public class Ararf_Arrfadjrndata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("sdetl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sdetl_id")
	private Ararf_Sdetl sdetl;

	@JsonProperty("todtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "todtls_id")
	private Ararf_Todtls todtls;
	
	@JsonProperty("docupdtl")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "arrfadjrndata_id")
	private List<Ararf_Docupdtl> docupdtl;



}
