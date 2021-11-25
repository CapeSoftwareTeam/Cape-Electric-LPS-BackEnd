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
@Table(name = "BRIDGINGDESCRIPTION_TABLE")
public class BridgingDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BRIDGINGDESCRIPTION_ID")
	private Integer bridgingDescriptionId;
	
	@Column(name = "FLAG")
	private Boolean flag;
	
	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "ENSURE_BRIDGINGCABLE_OB")
	private String ensureBridgingCableOb;

	@Column(name = "ENSURE_BRIDGINGCABLE_REM")
	private String ensureBridgingCableRem;

	@Column(name = "ALUMINIUMCONDUCTOR_SIDEWALL_OB")
	private String aluminiumConductorSideWallOb;

	@Column(name = "ALUMINIUMCONDUCTOR_SIDEWALL_REM")
	private String aluminiumConductorSideWallRem;

	@Column(name = "BRIDGINGCABLE_CONNECTION_OB")
	private String bridgingCableConnectionOb;

	@Column(name = "BRIDGINGCABLE_CONNECTION_REM")
	private String bridgingCableConnectionRem;

	@Column(name = "TOTALNO_BRIDGINGCABLE_OB")
	private String totalNoBridgingCableOb;

	@Column(name = "TOTALNO_BRIDGINGCABLE_REM")
	private String totalNoBridgingCableRem;

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

	public Integer getBridgingDescriptionId() {
		return bridgingDescriptionId;
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

	public void setBridgingDescriptionId(Integer bridgingDescriptionId) {
		this.bridgingDescriptionId = bridgingDescriptionId;
	}

	public String getEnsureBridgingCableOb() {
		return ensureBridgingCableOb;
	}

	public void setEnsureBridgingCableOb(String ensureBridgingCableOb) {
		this.ensureBridgingCableOb = ensureBridgingCableOb;
	}

	public String getEnsureBridgingCableRem() {
		return ensureBridgingCableRem;
	}

	public void setEnsureBridgingCableRem(String ensureBridgingCableRem) {
		this.ensureBridgingCableRem = ensureBridgingCableRem;
	}

	public String getAluminiumConductorSideWallOb() {
		return aluminiumConductorSideWallOb;
	}

	public void setAluminiumConductorSideWallOb(String aluminiumConductorSideWallOb) {
		this.aluminiumConductorSideWallOb = aluminiumConductorSideWallOb;
	}

	public String getAluminiumConductorSideWallRem() {
		return aluminiumConductorSideWallRem;
	}

	public void setAluminiumConductorSideWallRem(String aluminiumConductorSideWallRem) {
		this.aluminiumConductorSideWallRem = aluminiumConductorSideWallRem;
	}

	public String getBridgingCableConnectionOb() {
		return bridgingCableConnectionOb;
	}

	public void setBridgingCableConnectionOb(String bridgingCableConnectionOb) {
		this.bridgingCableConnectionOb = bridgingCableConnectionOb;
	}

	public String getBridgingCableConnectionRem() {
		return bridgingCableConnectionRem;
	}

	public void setBridgingCableConnectionRem(String bridgingCableConnectionRem) {
		this.bridgingCableConnectionRem = bridgingCableConnectionRem;
	}

	public String getTotalNoBridgingCableOb() {
		return totalNoBridgingCableOb;
	}

	public void setTotalNoBridgingCableOb(String totalNoBridgingCableOb) {
		this.totalNoBridgingCableOb = totalNoBridgingCableOb;
	}

	public String getTotalNoBridgingCableRem() {
		return totalNoBridgingCableRem;
	}

	public void setTotalNoBridgingCableRem(String totalNoBridgingCableRem) {
		this.totalNoBridgingCableRem = totalNoBridgingCableRem;
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
