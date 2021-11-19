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
@Table(name = "HOLDERS_TABLE")
public class Holder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOLDERS_ID")
	private Integer holderId;

	@Column(name = "FLAG")
	private Boolean flag;
	
	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "PHYSICAL_INSPECTION_OB")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REM")
	private String physicalInspectionRem;

	@Column(name = "CONDUCTORHOLDER_FLATSURFACE_OB")
	private String conductHolderFlatSurfaceOb;

	@Column(name = "CONDUCTORHOLDER_FLATSURFACE_REM")
	private String conductHolderFlatSurfaceRem;

	@Column(name = "CONDUCTOR_HOLDED_OB")
	private String conductorHoldedOb;

	@Column(name = "CONDUCTOR_HOLDED_REM")
	private String conductorHoldedRem;

	@Column(name = "MATERIAL_HOLDER_OB")
	private String materialHolderOb;

	@Column(name = "MATERIAL_HOLDER_REM")
	private String materialHolderRem;

	@Column(name = "TOTALNO_HOLDERS_OB")
	private String totalNoHolderOb;

	@Column(name = "TOTALNO_HOLDERS_REM")
	private String totalNoHolderRem;

	@Column(name = "INSPECTED_NO_OB")
	private String inspectedNoOb;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private String inspectionPassedNoOb;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_OB")
	private String inspectionFailedNoOb;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorDescription downConductorDescription;

	public Integer getHolderId() {
		return holderId;
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

	public void setHolderId(Integer holderId) {
		this.holderId = holderId;
	}

	public String getPhysicalInspectionOb() {
		return physicalInspectionOb;
	}

	public void setPhysicalInspectionOb(String physicalInspectionOb) {
		this.physicalInspectionOb = physicalInspectionOb;
	}

	public String getPhysicalInspectionRem() {
		return physicalInspectionRem;
	}

	public void setPhysicalInspectionRem(String physicalInspectionRem) {
		this.physicalInspectionRem = physicalInspectionRem;
	}

	public String getConductHolderFlatSurfaceOb() {
		return conductHolderFlatSurfaceOb;
	}

	public void setConductHolderFlatSurfaceOb(String conductHolderFlatSurfaceOb) {
		this.conductHolderFlatSurfaceOb = conductHolderFlatSurfaceOb;
	}

	public String getConductHolderFlatSurfaceRem() {
		return conductHolderFlatSurfaceRem;
	}

	public void setConductHolderFlatSurfaceRem(String conductHolderFlatSurfaceRem) {
		this.conductHolderFlatSurfaceRem = conductHolderFlatSurfaceRem;
	}

	public String getConductorHoldedOb() {
		return conductorHoldedOb;
	}

	public void setConductorHoldedOb(String conductorHoldedOb) {
		this.conductorHoldedOb = conductorHoldedOb;
	}

	public String getConductorHoldedRem() {
		return conductorHoldedRem;
	}

	public void setConductorHoldedRem(String conductorHoldedRem) {
		this.conductorHoldedRem = conductorHoldedRem;
	}

	public String getMaterialHolderOb() {
		return materialHolderOb;
	}

	public void setMaterialHolderOb(String materialHolderOb) {
		this.materialHolderOb = materialHolderOb;
	}

	public String getMaterialHolderRem() {
		return materialHolderRem;
	}

	public void setMaterialHolderRem(String materialHolderRem) {
		this.materialHolderRem = materialHolderRem;
	}

	public String getTotalNoHolderOb() {
		return totalNoHolderOb;
	}

	public void setTotalNoHolderOb(String totalNoHolderOb) {
		this.totalNoHolderOb = totalNoHolderOb;
	}

	public String getTotalNoHolderRem() {
		return totalNoHolderRem;
	}

	public void setTotalNoHolderRem(String totalNoHolderRem) {
		this.totalNoHolderRem = totalNoHolderRem;
	}

	public String getInspectedNoOb() {
		return inspectedNoOb;
	}

	public void setInspectedNoOb(String inspectedNoOb) {
		this.inspectedNoOb = inspectedNoOb;
	}

	public String getInspectedNoRem() {
		return inspectedNoRem;
	}

	public void setInspectedNoRem(String inspectedNoRem) {
		this.inspectedNoRem = inspectedNoRem;
	}

	public String getInspectionPassedNoOb() {
		return inspectionPassedNoOb;
	}

	public void setInspectionPassedNoOb(String inspectionPassedNoOb) {
		this.inspectionPassedNoOb = inspectionPassedNoOb;
	}

	public String getInspectionPassedNoRem() {
		return inspectionPassedNoRem;
	}

	public void setInspectionPassedNoRem(String inspectionPassedNoRem) {
		this.inspectionPassedNoRem = inspectionPassedNoRem;
	}

	public String getInspectionFailedNoOb() {
		return inspectionFailedNoOb;
	}

	public void setInspectionFailedNoOb(String inspectionFailedNoOb) {
		this.inspectionFailedNoOb = inspectionFailedNoOb;
	}

	public String getInspectionFailedNoRem() {
		return inspectionFailedNoRem;
	}

	public void setInspectionFailedNoRem(String inspectionFailedNoRem) {
		this.inspectionFailedNoRem = inspectionFailedNoRem;
	}

	public DownConductorDescription getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(DownConductorDescription downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
