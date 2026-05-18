package com.deloitte.returns.entity.type.AppealHigherAuthority1;
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
@Table(name = "items", schema = "appeal_higher_authority")

public class Aplha_Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("itemname")
	private String itemname;

	@JsonProperty("refid")
	private String refid;

	@JsonProperty("refdt")
	private String refdt;
	
	@JsonProperty("appldtlsdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appldtlsdata_id")
	private Aplha_Appldtlsdata appldtlsdata;

	@JsonProperty("applordrsdata")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "applordrsdata_id")
	private Aplha_Applordrsdata applordrsdata;
	
	@JsonProperty("aplremandins")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "items_id")
	private List<Aplha_Aplremandins> aplremandins;



}
