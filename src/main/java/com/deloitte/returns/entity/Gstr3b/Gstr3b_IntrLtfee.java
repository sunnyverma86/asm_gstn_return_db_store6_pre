
package com.deloitte.returns.entity.Gstr3b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Interest and LateFee
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "intr_details", "ltfee_details", "r1ltfeedet" })

@Entity
@Data
@Table(name = "intr_ltfee", schema = "gstr3b")
public class Gstr3b_IntrLtfee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Interest
	 * 
	 */
	@JsonProperty("intr_details")
	@JsonPropertyDescription("Interest")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "intr_details_id")
	private Gstr3b_IntrDetails intrDetails;
	/**
	 * Latefee
	 * 
	 */
	@JsonProperty("ltfee_details")
	@JsonPropertyDescription("Latefee")

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "ltfee_details_id")
	private Gstr3b_LtfeeDetails ltfeeDetails;
	/**
	 * Late fee details of GSTR1
	 * 
	 */
	@JsonProperty("r1ltfeedet")
	@JsonPropertyDescription("Late fee details of GSTR1")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "intr_ltfee_id")
	private List<Gstr3b_R1ltfeedet> r1ltfeedet = new ArrayList<Gstr3b_R1ltfeedet>();

	private final static long serialVersionUID = -3057617817650615687L;
}
