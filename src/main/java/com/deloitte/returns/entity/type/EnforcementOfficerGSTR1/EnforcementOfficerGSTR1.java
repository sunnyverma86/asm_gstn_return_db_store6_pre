package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;

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
@Table(name = "enforcement_officer_gstr1", schema = "enforcement_officer_gstr1")
public class EnforcementOfficerGSTR1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column
    private String gstin;


	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_gstr1_id")
	private List<En_Gstr1_B2B> b2b;
	
	@JsonProperty("b2ba")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
	private List<En_Gstr1_B2BA> b2Ba;

    @JsonProperty("cdn")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Cdn> cdn;

    @JsonProperty("cdna")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Cdna> cdna;

    @JsonProperty("isda")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Isda> isda;

    @JsonProperty("isd")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Isd> isd;

    @JsonProperty("tds")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Tds> tds;

    @JsonProperty("tdsa")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Tdsa> tdsa;

    @JsonProperty("tcs")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enforcement_officer_gstr1_id")
    private List<En_Gstr1_Tcs> tcs;
  
    @Column(name = "create_date_time", updatable = false)
    private LocalDateTime createDateTime;
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    @PrePersist
    protected void onCreate(){
        createDateTime = LocalDateTime.now();
        updatedDateTime = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now();
    }
    
    
 }