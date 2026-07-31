package com.deloitte.returns.entity.EwayBill;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "nic_ewb_part_a", name = "\"PARTA_EWB\"")
public class PartAEwb {

	@Id
	@Column(name = "\"PARTA_EWB_ID\"")
	private Long partAEwbId;

	@Column(name = "\"EwbNo\"")
	private String ewbNo;

	@Column(name = "\"EwbDt\"")
	private String ewbDt;

	@Column(name = "\"UserTyp\"")
	private String userTyp;

	@Column(name = "\"UserGstin\"")
	private String userGstin;

	@Column(name = "\"TransTyp\"")
	private String transTyp;

	@Column(name = "\"SupType\"")
	private String supType;

	@Column(name = "\"SSupTyp\"")
	private String sSupTyp;

	@Column(name = "\"SSupDesc\"")
	private String sSupDesc;

	@Column(name = "\"DocTyp\"")
	private String docTyp;

	@Column(name = "\"DocNo\"")
	private String docNo;

	@Column(name = "\"DocDt\"")
	private String docDt;

	@Column(name = "\"FrGstin\"")
	private String frGstin;

	@Column(name = "\"FrName\"")
	private String frName;

	@Column(name = "\"FrPlac\"")
	private String frPlac;

	@Column(name = "\"FrPin\"")
	private Integer frPin;

	@Column(name = "\"FrStat\"")
	private Integer frStat;

	@Column(name = "\"ToGstin\"")
	private String toGstin;

	@Column(name = "\"ToName\"")
	private String toName;

	@Column(name = "\"ToPlac\"")
	private String toPlac;

	@Column(name = "\"ToPin\"")
	private Integer toPin;

	@Column(name = "\"ToStat\"")
	private Integer toStat;

	@Column(name = "\"InvVal\"")
	private BigDecimal invVal;

	@Column(name = "\"AssVal\"")
	private BigDecimal assVal;

	@Column(name = "\"IGSTVal\"")
	private BigDecimal igstVal;

	@Column(name = "\"CGSTVal\"")
	private BigDecimal cgstVal;

	@Column(name = "\"SGSTVal\"")
	private BigDecimal sgstVal;

	@Column(name = "\"CESSVal\"")
	private BigDecimal cessVal;

	@Column(name = "\"CESSNonAdvolVal\"")
	private BigDecimal cessNonAdvolVal;

	@Column(name = "\"OtherVal\"")
	private BigDecimal otherVal;

	@Column(name = "\"Status\"")
	private String status;

	@Column(name = "\"RejStatus\"")
	private String rejStatus;

	@Column(name = "\"TravDist\"")
	private Integer travDist;

	@Column(name = "\"EwbValidDt\"")
	private String ewbValidDt;

	@Column(name = "\"VehType\"")
	private String vehType;

	@Column(name = "\"DespFrStat\"")
	private Integer despFrStat;

	@Column(name = "\"ShipToStat\"")
	private Integer shipToStat;

	@Column(name = "\"IpAddr\"")
	private String ipAddr;

	@Column(name = "\"FrAddr\"")
	private String frAddr;

	@Column(name = "\"ToAddr\"")
	private String toAddr;

	@Column(name = "log_id")
	private Long logId;

	@Column(name = "\"EWB_A_ID\"")
	private Long ewbAId;

	@Column(name = "dt")
	private LocalDateTime dt;
}