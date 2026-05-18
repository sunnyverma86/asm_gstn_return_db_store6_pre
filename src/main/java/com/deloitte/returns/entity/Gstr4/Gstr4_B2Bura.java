package com.deloitte.returns.entity.Gstr4;

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
@Table(name = "b2bura", schema = "gstr4")
@Entity
public class Gstr4_B2Bura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@JsonProperty("inv")
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "b2bur_id")
//	private List<Gstr4_B2BurInv> inv;

}
