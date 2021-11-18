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
@Table(name = "CONNECTORS_TABLE")
public class Connectors implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONNECTORS_ID")
	private Integer connectorId;

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

	@Column(name = "STRAIGHT_CONNECTORCHACK_OB")
	private String strightConnectCheckOb;

	@Column(name = "STRAIGHT_CONNECTORCHACK_REM")
	private String strightConnectCheckRem;

	@Column(name = "MATERIAL_CONNECTOR_OB")
	private String materialConnectorOb;

	@Column(name = "MATERIAL_CONNECTOR_REM ")
	private String materialConnectorRem;

	@Column(name = "MAXCONNECTORS_DOWNCONDUCTORS_OB")
	private String maxConnectorsDownConductorOb;

	@Column(name = "MAXCONNECTORS_DOWNCONDUCTORS_REM")
	private String maxConnectorsDownConductorRem;

	@Column(name = "TOTALNO_CONNECTORS_OB")
	private String totalNoConnectorsOb;

	@Column(name = "TOTALNO_CONNECTORS_REM")
	private String totalNoConnectorsRem;

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

	public Integer getConnectorId() {
		return connectorId;
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

	public void setConnectorId(Integer connectorId) {
		this.connectorId = connectorId;
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

	public String getStrightConnectCheckOb() {
		return strightConnectCheckOb;
	}

	public void setStrightConnectCheckOb(String strightConnectCheckOb) {
		this.strightConnectCheckOb = strightConnectCheckOb;
	}

	public String getStrightConnectCheckRem() {
		return strightConnectCheckRem;
	}

	public void setStrightConnectCheckRem(String strightConnectCheckRem) {
		this.strightConnectCheckRem = strightConnectCheckRem;
	}

	public String getMaterialConnectorOb() {
		return materialConnectorOb;
	}

	public void setMaterialConnectorOb(String materialConnectorOb) {
		this.materialConnectorOb = materialConnectorOb;
	}

	public String getMaterialConnectorRem() {
		return materialConnectorRem;
	}

	public void setMaterialConnectorRem(String materialConnectorRem) {
		this.materialConnectorRem = materialConnectorRem;
	}

	public String getMaxConnectorsDownConductorOb() {
		return maxConnectorsDownConductorOb;
	}

	public void setMaxConnectorsDownConductorOb(String maxConnectorsDownConductorOb) {
		this.maxConnectorsDownConductorOb = maxConnectorsDownConductorOb;
	}

	public String getMaxConnectorsDownConductorRem() {
		return maxConnectorsDownConductorRem;
	}

	public void setMaxConnectorsDownConductorRem(String maxConnectorsDownConductorRem) {
		this.maxConnectorsDownConductorRem = maxConnectorsDownConductorRem;
	}

	public String getTotalNoConnectorsOb() {
		return totalNoConnectorsOb;
	}

	public void setTotalNoConnectorsOb(String totalNoConnectorsOb) {
		this.totalNoConnectorsOb = totalNoConnectorsOb;
	}

	public String getTotalNoConnectorsRem() {
		return totalNoConnectorsRem;
	}

	public void setTotalNoConnectorsRem(String totalNoConnectorsRem) {
		this.totalNoConnectorsRem = totalNoConnectorsRem;
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
