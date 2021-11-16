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
@Table(name = "EARTHINGCLAMPS_TABLE")
public class EarthingClamps implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHCLAMPS_ID")
	private Integer earthingClampsId;

	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "PHYSICAL_INSPECTIONINOB")
	private String physicalInspectionInOb;

	@Column(name = "PSYSICAL_INSPECTIONINREM")
	private String psysicalInspectionInRem;

	@Column(name = "CLAMPS_FIRMLYOB")
	private String clampsFirmlyOb;

	@Column(name = "CLAMPS_FIRMLYINREM")
	private String clampsFirmlyRem;

	@Column(name = "INTERCONNECTION_OF_EARTHCLAMPSINOB")
	private String interConnectOfEarthClampInOb;

	@Column(name = "INTERCONNECTION_OF_EARTHCLAMPSINREM")
	private String interConnectOfEarthClampInRem;

	@Column(name = "TYPE_OF_CLAMPSINOB")
	private String typeOfClampsInOb;

	@Column(name = "TYPE_OF_CLAMPSINREM")
	private String typeOfClampsInRem;

	@Column(name = "MATERIAL_OF_CLAMPSINOB")
	private String materialOfClampsInOb;

	@Column(name = "MATERIAL_OF_CLAMPSINREM")
	private String materialOfClampsInRem;

	@Column(name = "TOTALNO_CLAMPSINOB")
	private String totalNoClampsInOb;

	@Column(name = "TOTALNO_CLAMPSINREM")
	private String totalNoClampsInRem;

	@Column(name = "INSPECTED_CLAMPSINOB")
	private String inspectedClampsInOb;

	@Column(name = "INSPECTED_CLAMPSINREM")
	private String inspectedClampsInRem;

	@Column(name = "INSPECTED_PASSEDINOB")
	private String inspectionPassedInOb;

	@Column(name = "INSPECTED_PASSEDINREM")
	private String inspectionPassedInRem;

	@Column(name = "INSPECTED_FAILEDINOB")
	private String inspectionFailedInOb;

	@Column(name = "INSPECTED_FAILEDINREM")
	private String inspectionFailedInRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthingClampsId() {
		return earthingClampsId;
	}

	public Integer getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(Integer locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setEarthingClampsId(Integer earthingClampsId) {
		this.earthingClampsId = earthingClampsId;
	}

	public String getPhysicalInspectionInOb() {
		return physicalInspectionInOb;
	}

	public void setPhysicalInspectionInOb(String physicalInspectionInOb) {
		this.physicalInspectionInOb = physicalInspectionInOb;
	}

	public String getPsysicalInspectionInRem() {
		return psysicalInspectionInRem;
	}

	public void setPsysicalInspectionInRem(String psysicalInspectionInRem) {
		this.psysicalInspectionInRem = psysicalInspectionInRem;
	}

	public String getClampsFirmlyOb() {
		return clampsFirmlyOb;
	}

	public void setClampsFirmlyOb(String clampsFirmlyOb) {
		this.clampsFirmlyOb = clampsFirmlyOb;
	}

	public String getClampsFirmlyRem() {
		return clampsFirmlyRem;
	}

	public void setClampsFirmlyRem(String clampsFirmlyRem) {
		this.clampsFirmlyRem = clampsFirmlyRem;
	}

	public String getInterConnectOfEarthClampInOb() {
		return interConnectOfEarthClampInOb;
	}

	public void setInterConnectOfEarthClampInOb(String interConnectOfEarthClampInOb) {
		this.interConnectOfEarthClampInOb = interConnectOfEarthClampInOb;
	}

	public String getInterConnectOfEarthClampInRem() {
		return interConnectOfEarthClampInRem;
	}

	public void setInterConnectOfEarthClampInRem(String interConnectOfEarthClampInRem) {
		this.interConnectOfEarthClampInRem = interConnectOfEarthClampInRem;
	}

	public String getTypeOfClampsInOb() {
		return typeOfClampsInOb;
	}

	public void setTypeOfClampsInOb(String typeOfClampsInOb) {
		this.typeOfClampsInOb = typeOfClampsInOb;
	}

	public String getTypeOfClampsInRem() {
		return typeOfClampsInRem;
	}

	public void setTypeOfClampsInRem(String typeOfClampsInRem) {
		this.typeOfClampsInRem = typeOfClampsInRem;
	}

	public String getMaterialOfClampsInOb() {
		return materialOfClampsInOb;
	}

	public void setMaterialOfClampsInOb(String materialOfClampsInOb) {
		this.materialOfClampsInOb = materialOfClampsInOb;
	}

	public String getMaterialOfClampsInRem() {
		return materialOfClampsInRem;
	}

	public void setMaterialOfClampsInRem(String materialOfClampsInRem) {
		this.materialOfClampsInRem = materialOfClampsInRem;
	}

	public String getTotalNoClampsInOb() {
		return totalNoClampsInOb;
	}

	public void setTotalNoClampsInOb(String totalNoClampsInOb) {
		this.totalNoClampsInOb = totalNoClampsInOb;
	}

	public String getTotalNoClampsInRem() {
		return totalNoClampsInRem;
	}

	public void setTotalNoClampsInRem(String totalNoClampsInRem) {
		this.totalNoClampsInRem = totalNoClampsInRem;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public String getInspectedClampsInOb() {
		return inspectedClampsInOb;
	}

	public void setInspectedClampsInOb(String inspectedClampsInOb) {
		this.inspectedClampsInOb = inspectedClampsInOb;
	}

	public String getInspectedClampsInRem() {
		return inspectedClampsInRem;
	}

	public void setInspectedClampsInRem(String inspectedClampsInRem) {
		this.inspectedClampsInRem = inspectedClampsInRem;
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
