package com.deloitte.returns.entity.type.EnforcementOfficerGSTR4Sum1;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "enforcement_officer_gstr4", schema = "enforcement_officer_gstr4")

public class EnforcementOfficerGSTR4 {
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
    
    @JsonProperty("ret_period")
    private String retperiod;


    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("sec_sum")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gstr4_id")
    public List<En_Gstr4_Secsum> secsum;

    @JsonProperty("dbtdtl")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gstr4_id")
    public List<En_Gstr4_Dbtdtl> dbtdtl;  

    @JsonProperty("liabDetl")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "liabDetl_id")
    public En_Gstr4_LiabDetl liabDetl; 
    
    @JsonProperty("ttl_inv")
    private Double ttl_inv;



}
