package com.deloitte.returns.entity.type.AppealTaxPayer1;
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
@Table(name = "appelappdata", schema = "appeal_by_tax_payer")

public class Appeal_Appelappdata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("userdtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userdtl_id")
	private Appeal_Userdtl userdtl;
	
	@JsonProperty("orddtl")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orddtl_id")
	private Appeal_Orddtl orddtl;
	
	@JsonProperty("issuecat")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelappdata_id")
	private List<Appeal_Issuecat> issuecat;
	
	@JsonProperty("initamtorign")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelappdata_id")
	private List<Appeal_Initamtorign> initamtorign;
	
	@JsonProperty("docappeal")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docappeal_id")
	private Appeal_Docappeal docappeal;

	@JsonProperty("docannexure")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docannexure_id")
	private Appeal_Docannexure docannexure;

	@JsonProperty("docpayment")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docpayment_id")
	private Appeal_Docpayment docpayment;

	@JsonProperty("docothers")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelappdata_id")
	private List<Appeal_Docothers> docothers;

	@JsonProperty("authdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authdtls_id")
	private Appeal_Authdtls authdtls;
	
	@JsonProperty("isMFY")
	private String isMFY;
	
	@JsonProperty("docappeal_mfy")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appelappdata_id")
	private List<Appeal_Docappeal_mfy> docappeal_mfy;



	


}
