package com.deloitte.returns.entity.type.EnforcementOfficerGSTR8Sum1;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enforcement_officer_gstr8", schema = "enforcement_officer_gstr8")


public class EnforcementOfficerGSTR8 {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;

	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

    @JsonProperty("gstin")
    private String gstin;

    @JsonProperty("dg")
    private String dg;

    @JsonProperty("arn_dt")
    private String arn_dt;
    
    @JsonProperty("arn")
    private String arn;

    @JsonProperty("fp")
    private String fp;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("dflt_amt")
    public Double dfltamt;   
    
    @JsonProperty("tcs")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tcs_id")
    public En_Gstr8_Tcs tcs;

    @JsonProperty("tcsa")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tcsa_id")
    public En_Gstr8_Tcsa tcsa;

    @JsonProperty("tax_pay")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxpay_id")
    public En_Gstr8_Taxpay taxpay;

    @JsonProperty("tax_paid")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxpaid_id")
    public En_Gstr8_Taxpaid taxpaid;

    @JsonProperty("urd")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "urd_id")
    public En_Gstr8_Urd urd;

    @JsonProperty("urda")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "urda_id")
    public En_Gstr8_Urda urda;





}
