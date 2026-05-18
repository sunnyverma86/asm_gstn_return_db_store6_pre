
package com.deloitte.returns.entity.Gstr5;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "gstin", "fp", "fil_dt", "b2b", "gstr5Cdn", "gstr5B2Cl", "gstr5B2CS", "imp_g", "gstr5Cdnur",
		"gstr5Txi", "gstr5Txpd", "gstr5B2Ba", "gstr5B2Cla", "gstr5B2Csa", "gstr5Cdna", "gstr5Cdnura", "imp_ga",
		"REVCHRG" })
@Generated("jsonschema2pojo")
@Entity
@Table(name = "gstr5", schema = "gstr5")
@Data
public class Gstr5 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * GSTIN of principal (Required)
	 * 
	 */
	@JsonProperty("gstin")
	@JsonPropertyDescription("GSTIN of principal")

	@Column
	public String gstin;
	/**
	 * Filing period (Required)
	 * 
	 */
	@JsonProperty("fp")
	@JsonPropertyDescription("Filing period")

	@Column
	public String fp;
	/**
	 * Filing date (Required)
	 * 
	 */
	@JsonProperty("fil_dt")
	@JsonPropertyDescription("Filing date")

	@Column
	public String filDt;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("b2b")
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_B2b> gstr5B2B = new ArrayList<Gstr5_B2b>();
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("gstr5Cdn")
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Cdn> gstr5Cdn = new ArrayList<Gstr5_Cdn>();

	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5B2Cl")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_B2cl> gstr5B2Cl = new ArrayList<Gstr5_B2cl>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5B2CS")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_B2c> gstr5B2CS = new ArrayList<Gstr5_B2c>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("imp_g")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_ImpG> gstr5ImpG = new ArrayList<Gstr5_ImpG>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5Cdnur")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Cdnur> gstr5Cdnur = new ArrayList<Gstr5_Cdnur>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5Txi")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Txi> gstr5Txi = new ArrayList<Gstr5_Txi>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5Txpd")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Txpd> gstr5Txpd = new ArrayList<Gstr5_Txpd>();

	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5B2Ba")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_B2ba> gstr5B2Ba = new ArrayList<Gstr5_B2ba>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5B2Cla")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_B2cla> gstr5B2Cla = new ArrayList<Gstr5_B2cla>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5B2Csa")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_B2csa> gstr5B2Csa = new ArrayList<Gstr5_B2csa>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5Cdna")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Cdna> gstr5Cdna = new ArrayList<Gstr5_Cdna>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("gstr5Cdnura")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Cdnura> gstr5Cdnura = new ArrayList<Gstr5_Cdnura>();
	/**
	 *
	 * (Required)
	 *
	 */
	@JsonProperty("imp_ga")

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_ImpGa> gstr5ImpGa = new ArrayList<Gstr5_ImpGa>();

	@JsonProperty("REVCHRG")

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gstr5_id")
	public List<Gstr5_Revchrg> gstr5Revchrg = new ArrayList<Gstr5_Revchrg>();
	private final static long serialVersionUID = -6539878654665822278L;

	@Column(name = "create_date_time", updatable = false)
	private LocalDateTime createDateTime;
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		createDateTime = LocalDateTime.now();
		updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDateTime = LocalDateTime.now();
	}

}
