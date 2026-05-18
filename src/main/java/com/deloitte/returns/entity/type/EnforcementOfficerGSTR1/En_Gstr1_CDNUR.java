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
@Table(name = "cdnur", schema = "enforcement_officer_gstr1")


public class En_Gstr1_CDNUR {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("nt_num")
	private String nt_num;

	@JsonProperty("nt_dt")
	private String nt_dt;
	
	@JsonProperty("p_gst")
	private String p_gst;

	@JsonProperty("inum")
	private String inum;
	
	@JsonProperty("idt")
	private String idt;
	
	@JsonProperty("val")
	private Double val;
	
    @JsonProperty("itms")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cdnur_id")
    private List<En_Gstr1_Items> itms;


}
