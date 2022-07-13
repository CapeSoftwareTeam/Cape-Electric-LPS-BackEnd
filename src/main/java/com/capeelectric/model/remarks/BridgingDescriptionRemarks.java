/**
 * 
 */
package com.capeelectric.model.remarks;

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

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "BRIDGINGDESCRIPTION_TABLE")
public class BridgingDescriptionRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BRIDGINGDESCRIPTION_ID")
	private Integer bridgingDescriptionId;
	
	@Column(name = "FLAG")
	private String flag;

	@Column(name = "ENSURE_BRIDGINGCABLE_REM")
	private String ensureBridgingCableRem;

	@Column(name = "ALUMINIUMCONDUCTOR_SIDEWALL_REM")
	private String aluminiumConductorSideWallRem;

	@Column(name = "BRIDGINGCABLE_CONNECTION_REM")
	private String bridgingCableConnectionRem;
	
	@Column(name = "BRIDGINGCABLE_MATERIAL_REM")
	private String bridgingCableMaterialRem;
	
	@Column(name = "BRIDGINGCABLE_SIZE_REM")
	private String bridgingCableSizeRem;
	
	@Column(name = "TOTALNO_BRIDGINGCABLE_REM")
	private String totalNoBridgingCableRem;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorsDescriptionRemarks downConductorDescription;

	public Integer getBridgingDescriptionId() {
		return bridgingDescriptionId;
	}

	public void setBridgingDescriptionId(Integer bridgingDescriptionId) {
		this.bridgingDescriptionId = bridgingDescriptionId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEnsureBridgingCableRem() {
		return ensureBridgingCableRem;
	}

	public void setEnsureBridgingCableRem(String ensureBridgingCableRem) {
		this.ensureBridgingCableRem = ensureBridgingCableRem;
	}

	public String getAluminiumConductorSideWallRem() {
		return aluminiumConductorSideWallRem;
	}

	public void setAluminiumConductorSideWallRem(String aluminiumConductorSideWallRem) {
		this.aluminiumConductorSideWallRem = aluminiumConductorSideWallRem;
	}

	public String getBridgingCableConnectionRem() {
		return bridgingCableConnectionRem;
	}

	public void setBridgingCableConnectionRem(String bridgingCableConnectionRem) {
		this.bridgingCableConnectionRem = bridgingCableConnectionRem;
	}

	public String getBridgingCableMaterialRem() {
		return bridgingCableMaterialRem;
	}

	public void setBridgingCableMaterialRem(String bridgingCableMaterialRem) {
		this.bridgingCableMaterialRem = bridgingCableMaterialRem;
	}

	public String getBridgingCableSizeRem() {
		return bridgingCableSizeRem;
	}

	public void setBridgingCableSizeRem(String bridgingCableSizeRem) {
		this.bridgingCableSizeRem = bridgingCableSizeRem;
	}

	public String getTotalNoBridgingCableRem() {
		return totalNoBridgingCableRem;
	}

	public void setTotalNoBridgingCableRem(String totalNoBridgingCableRem) {
		this.totalNoBridgingCableRem = totalNoBridgingCableRem;
	}

	public String getInspectedNoRem() {
		return inspectedNoRem;
	}

	public void setInspectedNoRem(String inspectedNoRem) {
		this.inspectedNoRem = inspectedNoRem;
	}

	public String getInspectionPassedNoRem() {
		return inspectionPassedNoRem;
	}

	public void setInspectionPassedNoRem(String inspectionPassedNoRem) {
		this.inspectionPassedNoRem = inspectionPassedNoRem;
	}

	public String getInspectionFailedNoRem() {
		return inspectionFailedNoRem;
	}

	public void setInspectionFailedNoRem(String inspectionFailedNoRem) {
		this.inspectionFailedNoRem = inspectionFailedNoRem;
	}

	public DownConductorsDescriptionRemarks getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(DownConductorsDescriptionRemarks downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
