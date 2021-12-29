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
@Table(name = "LPSVERTICALAIRTERMINAL_TABLE")

public class LpsVerticalAirTermination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LPSVERTICALAIRTERMINAL_ID")
	private Integer lpsVerticalAirTerminationId;

	@Column(name = "FLAG")
	private Boolean flag;
	
	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "PHYSICAL_INSPECTIONOBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTIONREMARKS")
	private String physicalInspectionRe;
	
	@Column(name = "MATERIAL_OF_TERMINALOBSERVATION")
	private String materialOfTerminalOb;

	@Column(name = "MATERIAL_OF_TERMINALREMARKS")
	private String materialOfTerminalRe;
	
	@Column(name = "SIZE_OF_TERMINALOBSERVATION")
	private String sizeOfTerminalOb;

	@Column(name = "SIZE_OF_TERMINALREMARKS")
	private String sizeOfTerminalRe;

	@Column(name = "HEIGHT_OF_TERMINALOBSERVATION")
	private String heightOfTerminalOb;

	@Column(name = "HEIGHT_OF_TERMINALREMARKS")
	private String heightOfTerminalRe;

	@Column(name = "ANGLEPROTECTION_HEIGHTOBSERVATION")
	private String angleProtectionHeightOb;

	@Column(name = "ANGLEPROTECTION_HEIGHTREMARKS")
	private String angleProtectionHeightRe;
	
	@Column(name = "INSTALLATION_TERMINATIONSYSTEM_OB")
	private String installationTerminationsystemOb;

	@Column(name = "INSTALLATION_TERMINATIONSYSTEM_REM")
	private String installationTerminationsystemRem;
	
	@Column(name = "SUPPORT_FLATSURFACEOBSERVATION")
	private String supportFlatSurfaceOb;

	@Column(name = "SUPPORT_FLATSURFACEREMARKS")
	private String supportFlatSurfaceRe;

	@Column(name = "HEIGHT_FLATSURAFACEOBSERVATION")
	private String heightFlatSurfaceOb;

	@Column(name = "HEIGHT_FLATSURAFACEREMARKS")
	private String heightFlatSurfaceRe;

	@Column(name = "VAT_TO_ROOFCONDUCTOROBSERVATION")
	private String vatToRoofConductorOB;

	@Column(name = "VAT_TO_ROOFCONDUCTORREMARKS")
	private String vatToRoofConductorRe;

	@Column(name = "TOTAL_NUMBEROBSERVATION")
	private String totalNumberOb;

	@Column(name = "TOTAL_NUMBERREMARKS")
	private String totalNumberRe;

	@Column(name = "INSP_NO_OBS")
	private String inspNoOb;

	@Column(name = "INSP_NO_REM")
	private String inspNoRe;

	@Column(name = "INSP_PASSED_NO_OBS")
	private String inspPassedNoOb;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspPassedNoRe;

	@Column(name = "INSP_FAILED_NO_OBS")
	private String inspFaileddNoOb;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspFaileddNoRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDes;

	public Integer getLpsVerticalAirTerminationId() {
		return lpsVerticalAirTerminationId;
	}

	public String getInstallationTerminationsystemOb() {
		return installationTerminationsystemOb;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public void setInstallationTerminationsystemOb(String installationTerminationsystemOb) {
		this.installationTerminationsystemOb = installationTerminationsystemOb;
	}

	public String getInstallationTerminationsystemRem() {
		return installationTerminationsystemRem;
	}

	public void setInstallationTerminationsystemRem(String installationTerminationsystemRem) {
		this.installationTerminationsystemRem = installationTerminationsystemRem;
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

	public void setLpsVerticalAirTerminationId(Integer lpsVerticalAirTerminationId) {
		this.lpsVerticalAirTerminationId = lpsVerticalAirTerminationId;
	}

	public String getSizeOfTerminalOb() {
		return sizeOfTerminalOb;
	}

	public void setSizeOfTerminalOb(String sizeOfTerminalOb) {
		this.sizeOfTerminalOb = sizeOfTerminalOb;
	}

	public String getSizeOfTerminalRe() {
		return sizeOfTerminalRe;
	}

	public void setSizeOfTerminalRe(String sizeOfTerminalRe) {
		this.sizeOfTerminalRe = sizeOfTerminalRe;
	}

	public String getHeightOfTerminalOb() {
		return heightOfTerminalOb;
	}

	public void setHeightOfTerminalOb(String heightOfTerminalOb) {
		this.heightOfTerminalOb = heightOfTerminalOb;
	}

	public String getHeightOfTerminalRe() {
		return heightOfTerminalRe;
	}

	public void setHeightOfTerminalRe(String heightOfTerminalRe) {
		this.heightOfTerminalRe = heightOfTerminalRe;
	}

	public String getAngleProtectionHeightOb() {
		return angleProtectionHeightOb;
	}

	public void setAngleProtectionHeightOb(String angleProtectionHeightOb) {
		this.angleProtectionHeightOb = angleProtectionHeightOb;
	}

	public String getAngleProtectionHeightRe() {
		return angleProtectionHeightRe;
	}

	public void setAngleProtectionHeightRe(String angleProtectionHeightRe) {
		this.angleProtectionHeightRe = angleProtectionHeightRe;
	}

	public String getMaterialOfTerminalOb() {
		return materialOfTerminalOb;
	}

	public void setMaterialOfTerminalOb(String materialOfTerminalOb) {
		this.materialOfTerminalOb = materialOfTerminalOb;
	}

	public String getMaterialOfTerminalRe() {
		return materialOfTerminalRe;
	}

	public void setMaterialOfTerminalRe(String materialOfTerminalRe) {
		this.materialOfTerminalRe = materialOfTerminalRe;
	}

	public String getPhysicalInspectionOb() {
		return physicalInspectionOb;
	}

	public void setPhysicalInspectionOb(String physicalInspectionOb) {
		this.physicalInspectionOb = physicalInspectionOb;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getSupportFlatSurfaceOb() {
		return supportFlatSurfaceOb;
	}

	public void setSupportFlatSurfaceOb(String supportFlatSurfaceOb) {
		this.supportFlatSurfaceOb = supportFlatSurfaceOb;
	}

	public String getSupportFlatSurfaceRe() {
		return supportFlatSurfaceRe;
	}

	public void setSupportFlatSurfaceRe(String supportFlatSurfaceRe) {
		this.supportFlatSurfaceRe = supportFlatSurfaceRe;
	}

	public String getHeightFlatSurfaceOb() {
		return heightFlatSurfaceOb;
	}

	public void setHeightFlatSurfaceOb(String heightFlatSurfaceOb) {
		this.heightFlatSurfaceOb = heightFlatSurfaceOb;
	}

	public String getHeightFlatSurfaceRe() {
		return heightFlatSurfaceRe;
	}

	public void setHeightFlatSurfaceRe(String heightFlatSurfaceRe) {
		this.heightFlatSurfaceRe = heightFlatSurfaceRe;
	}

	public String getVatToRoofConductorOB() {
		return vatToRoofConductorOB;
	}

	public void setVatToRoofConductorOB(String vatToRoofConductorOB) {
		this.vatToRoofConductorOB = vatToRoofConductorOB;
	}

	public String getVatToRoofConductorRe() {
		return vatToRoofConductorRe;
	}

	public void setVatToRoofConductorRe(String vatToRoofConductorRe) {
		this.vatToRoofConductorRe = vatToRoofConductorRe;
	}

	public String getTotalNumberOb() {
		return totalNumberOb;
	}

	public void setTotalNumberOb(String totalNumberOb) {
		this.totalNumberOb = totalNumberOb;
	}

	public String getTotalNumberRe() {
		return totalNumberRe;
	}

	public void setTotalNumberRe(String totalNumberRe) {
		this.totalNumberRe = totalNumberRe;
	}

	public String getInspNoOb() {
		return inspNoOb;
	}

	public void setInspNoOb(String inspNoOb) {
		this.inspNoOb = inspNoOb;
	}

	public String getInspNoRe() {
		return inspNoRe;
	}

	public void setInspNoRe(String inspNoRe) {
		this.inspNoRe = inspNoRe;
	}

	public String getInspPassedNoOb() {
		return inspPassedNoOb;
	}

	public void setInspPassedNoOb(String inspPassedNoOb) {
		this.inspPassedNoOb = inspPassedNoOb;
	}

	public String getInspPassedNoRe() {
		return inspPassedNoRe;
	}

	public void setInspPassedNoRe(String inspPassedNoRe) {
		this.inspPassedNoRe = inspPassedNoRe;
	}

	public String getInspFaileddNoOb() {
		return inspFaileddNoOb;
	}

	public void setInspFaileddNoOb(String inspFaileddNoOb) {
		this.inspFaileddNoOb = inspFaileddNoOb;
	}

	public String getInspFaileddNoRe() {
		return inspFaileddNoRe;
	}

	public void setInspFaileddNoRe(String inspFaileddNoRe) {
		this.inspFaileddNoRe = inspFaileddNoRe;
	}

	public LpsAirDiscription getLpsAirDes() {
		return lpsAirDes;
	}

	public void setLpsAirDes(LpsAirDiscription lpsAirDes) {
		this.lpsAirDes = lpsAirDes;
	}

}
