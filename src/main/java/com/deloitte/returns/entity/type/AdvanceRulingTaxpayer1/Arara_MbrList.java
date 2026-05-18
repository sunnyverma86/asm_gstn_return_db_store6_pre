package com.deloitte.returns.entity.type.AdvanceRulingTaxpayer1;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "mbrList", schema = "advance_ruling_taxpayer")

public class Arara_MbrList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("mbrDob")
	private String mbrDob;

	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("panNum")
	private String panNum;

	@JsonProperty("indCtz")
	private String indCtz;

	@JsonProperty("rowCheck")
	private String rowCheck;

	@JsonProperty("entityType")
	private String entityType;

	@JsonProperty("midName")
	private String midName;

	@JsonProperty("mbrDesig")
	private String mbrDesig;

	@JsonProperty("relFirstName")
	private String relFirstName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("entityNum")
	private String entityNum;

	@JsonProperty("mbrGender")
	private String mbrGender;

	@JsonProperty("mbrNationality")
	private String mbrNationality;

	@JsonProperty("memberType")
	private String memberType;

	@JsonProperty("eid")
	private Integer eid;

	@JsonProperty("insertTmstmp")
	private Integer insertTmstmp;

	@JsonProperty("latestVerNum")
	private Integer latestVerNum;
	
	@JsonProperty("mbrId")
	private Integer mbrId;




}
