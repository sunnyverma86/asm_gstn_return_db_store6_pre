package com.deloitte.returns.entity.type.EnforcementOfficerGSTR1;
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
@Table(name = "b2cs", schema = "enforcement_officer_gstr1")



public class En_Gstr1_B2CS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("chksum")
	private String chksum;

	@JsonProperty("sply_ty")
	private String sply_ty;

	@JsonProperty("typ")
	private String typ;

	@JsonProperty("etin")
	private String etin;

	@JsonProperty("pos")
	private String pos;
	
    @JsonProperty("diff_percent")//
    private Double diff_percent;

    @JsonProperty("txval")
    private Double txval;

    @JsonProperty("rt")
    private Double rt;

    @JsonProperty("iamt")
    private Double iamt;
    
    @JsonProperty("csamt")
    private Double csamt;



	
	

}
