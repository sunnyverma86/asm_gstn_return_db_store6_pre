package com.deloitte.returns.entity.EwayBill;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "ewb", schema = "eway_live_eway_bill_new")
public class EwayBill_Ewb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("EwbNo")
	@Column(name = "Ewb_nos")
	private long ewbNo;

	@JsonProperty("Ewb_no")
	@Column(name = "Ewb_no")
	private long EwbNos;

	@JsonProperty("Fin_Valid_dt")
	private String finValidDt;

	@JsonProperty("EwbDt")
	private String ewbDt;

	@JsonProperty("UserTyp")
	private String userTyp;

	@JsonProperty("UserGstin")
	private String userGstin;

	@JsonProperty("TransTyp")
	private long transTyp;

	@JsonProperty("SupType")
	private String supType;

	@JsonProperty("SSupTyp")
	private String sSupTyp;

	@JsonProperty("DocTyp")
	private String docTyp;

	@JsonProperty("DocNo")
	private String docNo;

	@JsonProperty("DocDt")
	private String docDt;

	@JsonProperty("FrGstin")
	private String frGstin;

	@JsonProperty("FrName")
	private String frName;

	@JsonProperty("FrPlac")
	private String frPlac;

	@JsonProperty("FrPin")
	private long frPin;

	@JsonProperty("FrStat")
	private long frStat;

	@JsonProperty("ToGstin")
	private String toGstin;

	@JsonProperty("ToName")
	private String toName;

	@JsonProperty("ToPlac")
	private String toPlac;

	@JsonProperty("ToPin")
	private long toPin;

	@JsonProperty("ToStat")
	private long toStat;

	@JsonProperty("AssVal")
	private double assVal;

	@JsonProperty("CGSTVal")
	private double cgstVal;

	@JsonProperty("SGSTVal")
	private double sgstVal;

	@JsonProperty("IGSTVal")
	private double igstVal;

	@JsonProperty("CESSVal")
	private double cessVal;

	@JsonProperty("CESSNonAdvolVal")
	private double cessNonAdvolVal;

	@JsonProperty("OtherVal")
	private double otherVal;

	@JsonProperty("Status")
	private String status;

	@JsonProperty("RejStatus")
	private String rejStatus;

	@JsonProperty("TravDist")
	private long travDist;

	@JsonProperty("SSupDesc")
	private String sSupDesc;

	@JsonProperty("InvVal")
	private double invVal;

	@JsonProperty("EwbValidDt")
	private String ewbValidDt;
	
	
	@JsonProperty("AccStatus")
	private String AccStatus;
	

	@JsonProperty("VehType")
	private String vehType;

	@JsonProperty("DespFrStat")
	private long despFrStat;

	@JsonProperty("ShipToStat")
	private long shipToStat;
	
	
	//New Add IPAdd
	@JsonProperty("IpAddr")
	private String IpAddr;
	
	@JsonProperty("FrAddr")
	private String FrAddr;
	
	@JsonProperty("ToAddr")
	private String ToAddr;
	//New Add end
	

	@JsonProperty("itemList")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ewb_id")
	private List<EwayBill_ItemList> itemList;

	//
	@JsonProperty("PartBDet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ewb_id")
	private List<EwayBill_PartBDet> partBDet;

	@JsonProperty("TransDet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ewb_id")
	private List<EwayBill_TransDet> transDet;

	@JsonProperty("RejectDet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ewb_id")
	private List<EwayBill_RejectDet> rejectDet;

	@JsonProperty("ExtendDet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ewb_id")
	private List<EwayBill_ExtendDet> extendDet;

	@JsonProperty("CancelDet")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ewb_id")
	private List<EwayBill_CancelDet> cancelDet;
}
