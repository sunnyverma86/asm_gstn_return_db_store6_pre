package com.deloitte.returns.entity.Gstr98a;

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
@Table(name = "cdna", schema = "gstr9_8a")
@Entity
public class Gstr9_8a_Cdna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("filingdt")
	private String filingdt;

	@JsonProperty("rtnPrd")
	private String rtnPrd;

	@JsonProperty("stin")
	private String stin;

	@JsonProperty("documents")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cdna_id")
	private List<Gstr9_8a_Document> documents;

}