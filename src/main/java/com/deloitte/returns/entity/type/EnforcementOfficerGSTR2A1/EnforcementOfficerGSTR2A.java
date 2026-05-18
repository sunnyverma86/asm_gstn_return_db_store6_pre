package com.deloitte.returns.entity.type.EnforcementOfficerGSTR2A1;

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
@Table(name = "enforcement_officer_gstr2a", schema = "enforcement_officer_gstr2a")
public class EnforcementOfficerGSTR2A {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column
    public String gstin;

	@JsonProperty("b2b")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "enforcement_officer_gstr2a_id")
	public List<En_Gstr2A_B2B> b2B;
	
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
	
	

//	@JsonProperty("b2ba")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_B2Ba> b2Ba;
//
//	@JsonProperty("cdn")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_CDN> cdn;
//
//	@JsonProperty("cdna")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_Cdna> cdna;
//
//	@JsonProperty("isd")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_Isd> isd;
//
//	@JsonProperty("isda")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_Isda> isda;
//
//	@JsonProperty("tds")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_Tds> tds;
//
//	@JsonProperty("tdsa")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "enforcement_officer_gstr2a_id")
//	private List<En_Gstr2A_Tdsa> tdsa;

}
