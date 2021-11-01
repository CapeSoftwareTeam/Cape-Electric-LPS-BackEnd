package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "EARTHELECTRODECHAMBER_TABLE")
public class EarthElectrodeChamber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHELECTRODECHAMBER_ID")
	private Integer earthingElectrodeChamberId;
	
	@Column(name = "PHYSICAL_INSPECTIONOB")
	private String physicalInspeOb;
	
	@Column(name = "PHYSICAL_INSPECTIONREM")
	private String physicalInspeRem;

	@Column(name = "CHAMBER_TYPEOB")
	private String chamberTypeOb;

	@Column(name = "CHAMBER_TYPEREM")
	private String chamberTypeRem;

	@Column(name = "CHAMBER_SIZEOB")
	private String chamberSizeOb;

	@Column(name = "CHAMBER_SIZEREM")
	private String chamberSizeRem;

	@Column(name = "MAXIMUMWITHSTAND_LOADOB")
	private String maximumWithStandLoadOb;

	@Column(name = "MAXIMUMWITHSTAND_LOADREM")
	private String maximumWithStandLoadRem;

	@Column(name = "CHAMBER_PLACED_SOILOB")
	private String maximumPlacedSoilOb;

	@Column(name = "CHAMBER_PLACED_SOILREM")
	private String maximumPlacedSoilRem;

	@Column(name = "TOTAL_CHAMBERSNOOB")
	private String totalChamberNoOb;

	@Column(name = "TOTAL_CHAMBERSNOREM")
	private String totalChamberNoRem;
	
	@Column(name = "INSPECTED_CHAMBERINOB")
	private String  inspectedChamberInOb;
	
	@Column(name = "INSPECTED_CHAMBERINREM")
	private String  inspectedChamberInRem;
	
	@Column(name = "INSPECTED_PASSEDINOB")
	private String  inspectionPassedInOb;
	
	@Column(name = "INSPECTED_PASSEDINREM")
	private String  inspectionPassedInRem;
	
	@Column(name = "INSPECTED_FAILEDINOB")
	private String  inspectionFailedInOb;
	
	@Column(name = "INSPECTED_FAILEDINREM")
	private String  inspectionFailedInRem;

	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthingElectrodeChamberId() {
		return earthingElectrodeChamberId;
	}

	public void setEarthingElectrodeChamberId(Integer earthingElectrodeChamberId) {
		this.earthingElectrodeChamberId = earthingElectrodeChamberId;
	}

	public String getPhysicalInspeRem() {
		return physicalInspeRem;
	}

	public void setPhysicalInspeRem(String physicalInspeRem) {
		this.physicalInspeRem = physicalInspeRem;
	}

	public String getChamberTypeOb() {
		return chamberTypeOb;
	}

	public void setChamberTypeOb(String chamberTypeOb) {
		this.chamberTypeOb = chamberTypeOb;
	}

	public String getChamberTypeRem() {
		return chamberTypeRem;
	}

	public void setChamberTypeRem(String chamberTypeRem) {
		this.chamberTypeRem = chamberTypeRem;
	}

	public String getChamberSizeOb() {
		return chamberSizeOb;
	}

	public void setChamberSizeOb(String chamberSizeOb) {
		this.chamberSizeOb = chamberSizeOb;
	}

	public String getChamberSizeRem() {
		return chamberSizeRem;
	}

	public void setChamberSizeRem(String chamberSizeRem) {
		this.chamberSizeRem = chamberSizeRem;
	}

	public String getMaximumWithStandLoadOb() {
		return maximumWithStandLoadOb;
	}

	public void setMaximumWithStandLoadOb(String maximumWithStandLoadOb) {
		this.maximumWithStandLoadOb = maximumWithStandLoadOb;
	}

	public String getMaximumWithStandLoadRem() {
		return maximumWithStandLoadRem;
	}

	public void setMaximumWithStandLoadRem(String maximumWithStandLoadRem) {
		this.maximumWithStandLoadRem = maximumWithStandLoadRem;
	}

	public String getMaximumPlacedSoilOb() {
		return maximumPlacedSoilOb;
	}

	public void setMaximumPlacedSoilOb(String maximumPlacedSoilOb) {
		this.maximumPlacedSoilOb = maximumPlacedSoilOb;
	}

	public String getMaximumPlacedSoilRem() {
		return maximumPlacedSoilRem;
	}

	public void setMaximumPlacedSoilRem(String maximumPlacedSoilRem) {
		this.maximumPlacedSoilRem = maximumPlacedSoilRem;
	}

	public String getTotalChamberNoOb() {
		return totalChamberNoOb;
	}

	public void setTotalChamberNoOb(String totalChamberNoOb) {
		this.totalChamberNoOb = totalChamberNoOb;
	}

	public String getTotalChamberNoRem() {
		return totalChamberNoRem;
	}

	public void setTotalChamberNoRem(String totalChamberNoRem) {
		this.totalChamberNoRem = totalChamberNoRem;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public String getPhysicalInspeOb() {
		return physicalInspeOb;
	}

	public void setPhysicalInspeOb(String physicalInspeOb) {
		this.physicalInspeOb = physicalInspeOb;
	}

	public String getInspectedChamberInOb() {
		return inspectedChamberInOb;
	}

	public void setInspectedChamberInOb(String inspectedChamberInOb) {
		this.inspectedChamberInOb = inspectedChamberInOb;
	}

	public String getInspectedChamberInRem() {
		return inspectedChamberInRem;
	}

	public void setInspectedChamberInRem(String inspectedChamberInRem) {
		this.inspectedChamberInRem = inspectedChamberInRem;
	}

	public String getInspectionPassedInOb() {
		return inspectionPassedInOb;
	}

	public void setInspectionPassedInOb(String inspectionPassedInOb) {
		this.inspectionPassedInOb = inspectionPassedInOb;
	}

	public String getInspectionPassedInRem() {
		return inspectionPassedInRem;
	}

	public void setInspectionPassedInRem(String inspectionPassedInRem) {
		this.inspectionPassedInRem = inspectionPassedInRem;
	}

	public String getInspectionFailedInOb() {
		return inspectionFailedInOb;
	}

	public void setInspectionFailedInOb(String inspectionFailedInOb) {
		this.inspectionFailedInOb = inspectionFailedInOb;
	}

	public String getInspectionFailedInRem() {
		return inspectionFailedInRem;
	}

	public void setInspectionFailedInRem(String inspectionFailedInRem) {
		this.inspectionFailedInRem = inspectionFailedInRem;
	}

		
}
