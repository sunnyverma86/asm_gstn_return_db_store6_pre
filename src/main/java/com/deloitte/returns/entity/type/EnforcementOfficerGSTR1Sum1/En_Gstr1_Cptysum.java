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
@Table(name = "cptysum", schema = "enforcement_officer_gstr1_sum")

public class En_Gstr1_Cptysum {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

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
	
    @JsonProperty("chksum")
    public String chksum;
    
    @JsonProperty("ctin")
    public String ctin;

	@JsonProperty("ttl_rec")
	@Column(name = "ttlrec")
	public Double ttlrec;


}
