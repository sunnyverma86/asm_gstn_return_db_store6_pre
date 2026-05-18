package com.deloitte.returns.entity.type.EnforcementOfficerGSTR6Sum1;
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
@Table(name = "enforcement_officer_gstr6", schema = "enforcement_officer_gstr6")


public class EnforcementOfficerGSTR6 {
	
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
    
    @JsonProperty("lateFeemain")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lateFeemain_id")
    public En_Gstr6_LateFeemain lateFeemain;

    @JsonProperty("itcDetails")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itcDetails_id")
    public En_Gstr6_ItcDetails itcDetails;  

    @JsonProperty("section_summary")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gstr6_id")   
    public List<En_Gstr6_Sectionsummary> sectionsummary;

 
 

}
