package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SPD_TABLE")
public class SPD implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SPD_ID")
	private Integer spdId;
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "MAINS_INCOMING_OB")
	private String mainsIncomingOb;
	
	@Column(name = "MAINS_INCOMING_REM")
	private String mainsIncomingRem;
	
	@Column(name = "TOTALMAINS_INCOMING_OB")
	private Integer totalMainsIncomingOb;
	
	
	@Column(name = "TOTALMAINS_INCOMING_REM")
	private String totalMainsIncomingRem;
	
	@Column(name = "NO_PANNELSUPPLITTING_OB")
	private String noPannelSupplittingOb;
	
	@Column(name = "NO_PANNELSUPPLITTING_REM")
	private String noPannelSupplittingRem;

	@Column(name = "TOTAL_NO_OUTDOOREQUIPMENT_OB")
	private Integer totalNoOutDoorRequipmentOb;
	
	@Column(name = "TOTAL_NO_OUTDOOREQUIPMENT_REM")
	private String totalNoOutDoorRequipmentRem;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "spd", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpdDescription> spdDescription;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SPD_REPORT_ID")
	private SpdReport spdReport;

	public Integer getSpdId() {
		return spdId;
	}

	public void setSpdId(Integer spdId) {
		this.spdId = spdId;
	}

	public String getMainsIncomingOb() {
		return mainsIncomingOb;
	}

	public void setMainsIncomingOb(String mainsIncomingOb) {
		this.mainsIncomingOb = mainsIncomingOb;
	}

	public String getMainsIncomingRem() {
		return mainsIncomingRem;
	}

	public void setMainsIncomingRem(String mainsIncomingRem) {
		this.mainsIncomingRem = mainsIncomingRem;
	}

	

	public String getTotalMainsIncomingRem() {
		return totalMainsIncomingRem;
	}

	public void setTotalMainsIncomingRem(String totalMainsIncomingRem) {
		this.totalMainsIncomingRem = totalMainsIncomingRem;
	}

	public String getNoPannelSupplittingOb() {
		return noPannelSupplittingOb;
	}

	public void setNoPannelSupplittingOb(String noPannelSupplittingOb) {
		this.noPannelSupplittingOb = noPannelSupplittingOb;
	}

	public String getNoPannelSupplittingRem() {
		return noPannelSupplittingRem;
	}

	public void setNoPannelSupplittingRem(String noPannelSupplittingRem) {
		this.noPannelSupplittingRem = noPannelSupplittingRem;
	}

	public Integer getTotalMainsIncomingOb() {
		return totalMainsIncomingOb;
	}

	public void setTotalMainsIncomingOb(Integer totalMainsIncomingOb) {
		this.totalMainsIncomingOb = totalMainsIncomingOb;
	}

	public Integer getTotalNoOutDoorRequipmentOb() {
		return totalNoOutDoorRequipmentOb;
	}

	public void setTotalNoOutDoorRequipmentOb(Integer totalNoOutDoorRequipmentOb) {
		this.totalNoOutDoorRequipmentOb = totalNoOutDoorRequipmentOb;
	}

	public String getTotalNoOutDoorRequipmentRem() {
		return totalNoOutDoorRequipmentRem;
	}

	public void setTotalNoOutDoorRequipmentRem(String totalNoOutDoorRequipmentRem) {
		this.totalNoOutDoorRequipmentRem = totalNoOutDoorRequipmentRem;
	}

	public List<SpdDescription> getSpdDescription() {
		return spdDescription;
	}

	public void setSpdDescription(List<SpdDescription> spdDescription) {
		this.spdDescription = spdDescription;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getBuildingCount() {
		return buildingCount;
	}

	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public SpdReport getSpdReport() {
		return spdReport;
	}

	public void setSpdReport(SpdReport spdReport) {
		this.spdReport = spdReport;
	}	
	
}
