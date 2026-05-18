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
@Table(name = "hsn", schema = "enforcement_officer_gstr1")



public class En_Gstr1_HSN {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("flag")
	private String flag;
	
    @JsonProperty("data")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hsn_id")
    private List<En_Gstr1_DATA> data;



}
