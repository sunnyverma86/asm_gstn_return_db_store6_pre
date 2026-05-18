package com.deloitte.returns.entity.type.AppealHigherAuthority1;
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
@Table(name = "appeal_higher_authority", schema = "appeal_higher_authority")

public class AppealHigherAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("casety")
	private String casety;

	@JsonProperty("crn")
	private String crn;

	@JsonProperty("dof")
	private String dof;

	@JsonProperty("gstin")
	private String gstin;

	@JsonProperty("state_cd")
	private String stateCD;

	@JsonProperty("status_desc")
	private String statusDesc;

	@JsonProperty("items")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "appeal_higher_authority_id")
	private List<Aplha_Items> items;



}
