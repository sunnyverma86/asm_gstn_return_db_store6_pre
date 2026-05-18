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
@Table(name = "nt", schema = "enforcement_officer_gstr1")


public class En_Gstr1_NT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("cflag")
	private String cflag;
	
	@JsonProperty("updby")
	private String updby;

	@JsonProperty("ntty")
	private String ntty;
	
	@JsonProperty("ont_num")
	private String ont_num;

	@JsonProperty("ont_dt")
	private String ont_dt;

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

	@JsonProperty("opd")
	private String opd;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("val")
	private Double val;
	
    @JsonProperty("itms")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "nt_id")
    private List<En_Gstr1_Items> itms;



	
	

}
