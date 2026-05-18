
package com.deloitte.returns.entity.Gstr2a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cfs", "ctin", "doclist" })

@Entity
@Table(name = "isd", schema = "gstr2a")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Gtsr2a_Isd implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * counter party filing status
	 * 
	 */
	@JsonProperty("cfs")
	@JsonPropertyDescription("counter party filing status")
	@Column
	public String cfs;
	@JsonProperty("ctin")
	@Column
	public String ctin;
	@JsonProperty("doclist")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "isd_id")
	public List<Gtsr2a_Doc> doclist = new ArrayList<Gtsr2a_Doc>();

	@ManyToOne
	@JoinColumn(name = "gstr2a_id")
	private Gstr2a gstr2a;
	private final static long serialVersionUID = 3270020365252116449L;

}
