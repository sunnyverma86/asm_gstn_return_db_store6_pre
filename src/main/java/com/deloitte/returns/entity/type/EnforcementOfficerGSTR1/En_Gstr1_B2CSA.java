package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;
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
@Entity
@Table(name = "b2csa", schema = "enforcement_officer_gstr1")


public class En_Gstr1_B2CSA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("omon")
	private String omon;

	@JsonProperty("opos")
	private String opos;

	@JsonProperty("sply_ty")
	private String sply_ty;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("pos")
	private String pos;
	
    @JsonProperty("itms")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "b2csa_id")
    private List<En_Gstr1_Items> itms;



}
