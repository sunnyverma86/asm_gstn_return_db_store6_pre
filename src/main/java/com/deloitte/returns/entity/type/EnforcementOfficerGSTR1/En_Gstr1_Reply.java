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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reply", schema = "enforcement_officer_gstr1")
public class En_Gstr1_Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("date")
	private String date;

	@JsonProperty("designationn")
	private String designationn;

	@JsonProperty("fname")
	private String fname;

	@JsonProperty("lname")
	private String lname;

	@JsonProperty("ntcdt")
	private String ntcdt;

	@JsonProperty("ntcno")
	private String ntcno;

	@JsonProperty("pan")
	private String pan;

	@JsonProperty("pershrng")
	private String pershrng;

	@JsonProperty("place")
	private String place;

	@JsonProperty("reason")
	private String reason;

	@JsonProperty("replyty")
	private String replyty;

	@JsonProperty("decdtls")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "decdtls_id")
	private En_Gstr1_Decdtls decdtls;

	@JsonProperty("maindocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private List<En_Gstr1_Maindocs> maindocs;

	@JsonProperty("suppdocs")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reply_id")
	private List<En_Gstr1_Suppdocs> suppdocs;

}