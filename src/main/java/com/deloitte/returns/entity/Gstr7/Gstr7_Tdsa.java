package com.deloitte.returns.entity.Gstr7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tdsa",schema = "gstr7")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ogstin_ded", "omonth", "oamt_ded", "gstin_ded", "deductee_name", "odeductee_name", "amt_ded",
		"iamt", "camt", "samt", "chksum", "source", "act_tkn" })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gstr7_Tdsa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("ogstin_ded")
	@Column(name = "ogstin_ded")
	public String ogstinDed;

	@JsonProperty("omonth")
	@Column(name = "omonth")
	public String omonth;

	@JsonProperty("oamt_ded")
	@Column(name = "oamt_ded")
	public Double oamtDed;

	@JsonProperty("gstin_ded")
	@Column(name = "gstin_ded")
	public String gstinDed;

	@JsonProperty("deductee_name")
	@Column(name = "deductee_name")
	public String deducteeName;

	@JsonProperty("odeductee_name")
	@Column(name = "odeductee_name")
	public String odeducteeName;

	@JsonProperty("amt_ded")
	@Column(name = "amt_ded")
	public Double amtDed;

	@JsonProperty("iamt")
	@Column(name = "iamt")
	public Double iamt;

	@JsonProperty("camt")
	@Column(name = "camt")
	public Double camt;

	@JsonProperty("samt")
	@Column(name = "samt")
	public Double samt;

	@JsonProperty("chksum")
	@Column(name = "chksum")
	public String chksum;

	@JsonProperty("source")
	@Enumerated(EnumType.STRING)
	@Column(name = "source")
	public Source source;

	@JsonProperty("act_tkn")
	@Enumerated(EnumType.STRING)
	@Column(name = "act_tkn")
	public ActTkn actTkn;
	
	
	@JsonProperty("inv")   //new add
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tdsa_id")
	private List<Gstr7_Inv> inv;

	@Generated("jsonschema2pojo")
	public enum ActTkn {

		Y("Y"), N("N");

		private final String value;
		private final static Map<String, Gstr7_Tdsa.ActTkn> CONSTANTS = new HashMap<>();

		static {
			for (Gstr7_Tdsa.ActTkn c : values()) {
				CONSTANTS.put(c.value, c);
			}
		}

		ActTkn(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}

		@JsonCreator
		public static Gstr7_Tdsa.ActTkn fromValue(String value) {
			Gstr7_Tdsa.ActTkn constant = CONSTANTS.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

	@Generated("jsonschema2pojo")
	public enum Source {

		C("C"), D("D");

		private final String value;
		private final static Map<String, Gstr7_Tdsa.Source> CONSTANTS = new HashMap<>();

		static {
			for (Gstr7_Tdsa.Source c : values()) {
				CONSTANTS.put(c.value, c);
			}
		}

		Source(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}

		@JsonCreator
		public static Gstr7_Tdsa.Source fromValue(String value) {
			Gstr7_Tdsa.Source constant = CONSTANTS.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
