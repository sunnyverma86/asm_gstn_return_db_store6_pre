package com.deloitte.returns.entity.Gstr9c;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tax_paid_9c", schema = "gstr9c")

public class Gstr9c_Taxpaid9c {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("pd_by_cash")  
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tax_paid_9c")
    private List<Gstr9c_PdByCash> pd_by_cash;


}
