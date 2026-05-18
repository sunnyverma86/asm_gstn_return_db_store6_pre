package com.deloitte.returns.entity.type.AdvanceRulingTaxpayer1;
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
@Table(name = "items", schema = "advance_ruling_taxpayer")

public class Arara_Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("arapplndata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "arapplndata_id")
	private Arara_Arapplndata arapplndata;
	
	@JsonProperty("aradjrn")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Arara_Aradjrn> aradjrn;
	
	@JsonProperty("arhrng")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Arara_Arhrng> arhrng;
	
	@JsonProperty("aradmisord")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Arara_Aradmisord> aradmisord;
	
	@JsonProperty("aradvord")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Arara_Aradvord> aradvord;
	
	@JsonProperty("aradvvoid")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Arara_Aradvvoid> aradvvoid;
	
	@JsonProperty("arvoidprodrop")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Arara_Arvoidprodrop> arvoidprodrop;






	

}
