package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1Sum1;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsections", schema = "enforcement_officer_gstr1_sum")


public class En_Gstr1_Subsections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("sec_nm")
    private String secnm;
    
    @JsonProperty("secIdentifier")
    private String secIdentifier;

    @JsonProperty("typ")
    private String typ;

    @JsonProperty("chksum")
    private String chksum;
    
	@JsonProperty("ttl_rec")
	@Column(name = "ttl_rec")
	public Double ttlrec;
	
	@JsonProperty("ttl_sgst")
	@Column(name = "ttlsgst")
	public Double ttlsgst;

	@JsonProperty("ttl_igst")
	@Column(name = "ttligst")
	public Double ttligst;

	@JsonProperty("ttl_cess")
	@Column(name = "ttlcess")
	public Double ttlcess;

	@JsonProperty("ttl_cgst")
	@Column(name = "ttlcgst")
	public Double ttlcgst;

	@JsonProperty("ttl_val")
	@Column(name = "ttlval")
	public Double ttlval;
	
	@JsonProperty("ttl_tax")
	@Column(name = "ttltax")
	public Double ttltax;
	
	@JsonProperty("act_cgst")
	@Column(name = "actcgst")
	public Double actcgst;
	
	@JsonProperty("act_tax")
	@Column(name = "acttax")
	public Double acttax;
	
	@JsonProperty("act_val")
	@Column(name = "actval")
	public Double actval;
	
	@JsonProperty("act_igst")
	@Column(name = "actigst")
	public Double actigst;
	
	@JsonProperty("act_cess")
	@Column(name = "actcess")
	public Double actcess;
	
	@JsonProperty("act_sgst")
	@Column(name = "actsgst")
	public Double actsgst;	


}
