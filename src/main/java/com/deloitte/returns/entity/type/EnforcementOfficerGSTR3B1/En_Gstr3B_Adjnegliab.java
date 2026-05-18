package com.deloitte.returns.entity.type.EnforcementOfficerGSTR3B1;

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
@Table(name = "adjnegliab", schema = "enforcement_officer_gstr3b")
public class En_Gstr3B_Adjnegliab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
