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
@Table(name = "EARTHDESCRIPTION_TABLE")
public class EarthingDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHDESCRIPTION_ID")
	private Integer earthDescriptionId;

	@Column(name = "FLAG")
	private Boolean flag;
	
	@Column(name = "SOIL_RESISTIVITYINOB")
	private String soilResistivityInOb;

	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "SOIL_RESISTIVITYINREM")
	private String soilResistivityInRem;

	@Column(name = "EARTHPIT_DIGGINGINOB")
	private String earthPitDigOb;

	@Column(name = "EARTHPIT_DIGGINGINREM")
	private String earthPitDigRem;

	@Column(name = "EARTHELECTRODE_LESSTHAN_DOWNCONDUCTORSINOB")
	private String earthElectrodeLesthanDownConductorInOb;

	@Column(name = "EARTHELECTRODE_LESSTHAN_DOWNCONDUCTORSINREM")
	private String earthElectrodeLesthanDownConductorInRem;

	@Column(name = "CONNECTED_EARTHTERMINATIONINOB")
	private String connectedEarthTerminalInOb;

	@Column(name = "CONNECTED_EARTHTERMINATIONINREM")
	private String connectedEarthTerminalInRem;

	@Column(name = "TESTJOINT_EARTHELECTRODEINOB")
	private String testJointEarthElectrodeInOb;

	@Column(name = "TESTJOINT_EARTHELECTRODEINREM")
	private String testJointEarthElectrodeInRem;

	@Column(name = "GROUNTLEVEL_COMPOUNTFILLEDINOB")
	private String grountLevelComponentFilledInOb;

	@Column(name = "GROUNTLEVEL_COMPOUNTFILLEDINREM")
	private String grountLevelComponentFilledInRem;

	@Column(name = "EARTHELCTRODE_LOCATIONINOB")
	private String earthElectrodeLocationInOb;

	@Column(name = "EARTHELCTRODE_LOCATIONINREM")
	private String earthElectrodeLocationInRem;

	@Column(name = "EARTHELECTRODE_MATERIALINOB")
	private String earthElectrodeMaterialInOb;

	@Column(name = "EARTHELECTRODE_MATERIALINREM")
	private String earthElectrodeMaterialInRem;

	@Column(name = "EARTHELECTRODE_SIZEINOB")
	private String earthElectrodeSizeInOb;

	@Column(name = "EARTHELECTRODE_SIZEINREM")
	private String earthElectrodeSizeInRem;

	@Column(name = "EARTHELECTRODE_LENGTHINOB")
	private String earthElectrodeLengthingOb;

	@Column(name = "EARTHELECTRODE_LENGTHINREM")
	private String earthElectrodeLengthingRem;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEWALLINOB")
	private String earthelectMaxiDistWallInOb;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEWALLINREM")
	private String earthelectMaxiDistWallInRem;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEWALLINOB")
	private String earthelectManimumDistanceWallInOb;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEWALLINREM")
	private String earthelectManimumDistanceWallInRem;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEINOB")
	private String earthelectMaxiDistOb;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEINREM")
	private String earthelectMaxiDistRem;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEINOB")
	private String earthelectManiDistOb;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEINREM")
	private String earthelectManiDistRem;

	@Column(name = "TOTALNUMBER_OF_ELECTRODEINOB")
	private String totalNumberOfElectrodeOb;

	@Column(name = "TOTALNUMBER_OF_ELECTRODEINREM")
	private String totalNumberOfElectrodeRem;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectedFailedNoRem;

	@Column(name = "INSPECTED_NO_OB")
	private String inspectedNoOb;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private String inspectedPassedNoOb;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectedPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_OB")
	private String inspectedFailedNoOb;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthDescriptionId() {
		return earthDescriptionId;
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

	public void setEarthDescriptionId(Integer earthDescriptionId) {
		this.earthDescriptionId = earthDescriptionId;
	}

	public String getSoilResistivityInOb() {
		return soilResistivityInOb;
	}

	public void setSoilResistivityInOb(String soilResistivityInOb) {
		this.soilResistivityInOb = soilResistivityInOb;
	}

	public String getSoilResistivityInRem() {
		return soilResistivityInRem;
	}

	public void setSoilResistivityInRem(String soilResistivityInRem) {
		this.soilResistivityInRem = soilResistivityInRem;
	}

	public String getEarthPitDigOb() {
		return earthPitDigOb;
	}

	public void setEarthPitDigOb(String earthPitDigOb) {
		this.earthPitDigOb = earthPitDigOb;
	}

	public String getEarthPitDigRem() {
		return earthPitDigRem;
	}

	public void setEarthPitDigRem(String earthPitDigRem) {
		this.earthPitDigRem = earthPitDigRem;
	}

	public String getEarthElectrodeLesthanDownConductorInOb() {
		return earthElectrodeLesthanDownConductorInOb;
	}

	public void setEarthElectrodeLesthanDownConductorInOb(String earthElectrodeLesthanDownConductorInOb) {
		this.earthElectrodeLesthanDownConductorInOb = earthElectrodeLesthanDownConductorInOb;
	}

	public String getEarthElectrodeLesthanDownConductorInRem() {
		return earthElectrodeLesthanDownConductorInRem;
	}

	public void setEarthElectrodeLesthanDownConductorInRem(String earthElectrodeLesthanDownConductorInRem) {
		this.earthElectrodeLesthanDownConductorInRem = earthElectrodeLesthanDownConductorInRem;
	}

	public String getConnectedEarthTerminalInOb() {
		return connectedEarthTerminalInOb;
	}

	public void setConnectedEarthTerminalInOb(String connectedEarthTerminalInOb) {
		this.connectedEarthTerminalInOb = connectedEarthTerminalInOb;
	}

	public String getConnectedEarthTerminalInRem() {
		return connectedEarthTerminalInRem;
	}

	public void setConnectedEarthTerminalInRem(String connectedEarthTerminalInRem) {
		this.connectedEarthTerminalInRem = connectedEarthTerminalInRem;
	}

	public String getTestJointEarthElectrodeInOb() {
		return testJointEarthElectrodeInOb;
	}

	public void setTestJointEarthElectrodeInOb(String testJointEarthElectrodeInOb) {
		this.testJointEarthElectrodeInOb = testJointEarthElectrodeInOb;
	}

	public String getTestJointEarthElectrodeInRem() {
		return testJointEarthElectrodeInRem;
	}

	public void setTestJointEarthElectrodeInRem(String testJointEarthElectrodeInRem) {
		this.testJointEarthElectrodeInRem = testJointEarthElectrodeInRem;
	}

	public String getGrountLevelComponentFilledInOb() {
		return grountLevelComponentFilledInOb;
	}

	public void setGrountLevelComponentFilledInOb(String grountLevelComponentFilledInOb) {
		this.grountLevelComponentFilledInOb = grountLevelComponentFilledInOb;
	}

	public String getGrountLevelComponentFilledInRem() {
		return grountLevelComponentFilledInRem;
	}

	public void setGrountLevelComponentFilledInRem(String grountLevelComponentFilledInRem) {
		this.grountLevelComponentFilledInRem = grountLevelComponentFilledInRem;
	}

	public String getEarthElectrodeLocationInOb() {
		return earthElectrodeLocationInOb;
	}

	public void setEarthElectrodeLocationInOb(String earthElectrodeLocationInOb) {
		this.earthElectrodeLocationInOb = earthElectrodeLocationInOb;
	}

	public String getEarthElectrodeLocationInRem() {
		return earthElectrodeLocationInRem;
	}

	public void setEarthElectrodeLocationInRem(String earthElectrodeLocationInRem) {
		this.earthElectrodeLocationInRem = earthElectrodeLocationInRem;
	}

	public String getEarthElectrodeMaterialInOb() {
		return earthElectrodeMaterialInOb;
	}

	public void setEarthElectrodeMaterialInOb(String earthElectrodeMaterialInOb) {
		this.earthElectrodeMaterialInOb = earthElectrodeMaterialInOb;
	}

	public String getEarthElectrodeMaterialInRem() {
		return earthElectrodeMaterialInRem;
	}

	public void setEarthElectrodeMaterialInRem(String earthElectrodeMaterialInRem) {
		this.earthElectrodeMaterialInRem = earthElectrodeMaterialInRem;
	}

	public String getEarthElectrodeSizeInOb() {
		return earthElectrodeSizeInOb;
	}

	public void setEarthElectrodeSizeInOb(String earthElectrodeSizeInOb) {
		this.earthElectrodeSizeInOb = earthElectrodeSizeInOb;
	}

	public String getEarthElectrodeSizeInRem() {
		return earthElectrodeSizeInRem;
	}

	public void setEarthElectrodeSizeInRem(String earthElectrodeSizeInRem) {
		this.earthElectrodeSizeInRem = earthElectrodeSizeInRem;
	}

	public String getEarthElectrodeLengthingOb() {
		return earthElectrodeLengthingOb;
	}

	public void setEarthElectrodeLengthingOb(String earthElectrodeLengthingOb) {
		this.earthElectrodeLengthingOb = earthElectrodeLengthingOb;
	}

	public String getEarthElectrodeLengthingRem() {
		return earthElectrodeLengthingRem;
	}

	public void setEarthElectrodeLengthingRem(String earthElectrodeLengthingRem) {
		this.earthElectrodeLengthingRem = earthElectrodeLengthingRem;
	}

	public String getEarthelectMaxiDistWallInOb() {
		return earthelectMaxiDistWallInOb;
	}

	public void setEarthelectMaxiDistWallInOb(String earthelectMaxiDistWallInOb) {
		this.earthelectMaxiDistWallInOb = earthelectMaxiDistWallInOb;
	}

	public String getEarthelectMaxiDistWallInRem() {
		return earthelectMaxiDistWallInRem;
	}

	public void setEarthelectMaxiDistWallInRem(String earthelectMaxiDistWallInRem) {
		this.earthelectMaxiDistWallInRem = earthelectMaxiDistWallInRem;
	}

	public String getEarthelectManimumDistanceWallInOb() {
		return earthelectManimumDistanceWallInOb;
	}

	public void setEarthelectManimumDistanceWallInOb(String earthelectManimumDistanceWallInOb) {
		this.earthelectManimumDistanceWallInOb = earthelectManimumDistanceWallInOb;
	}


	public String getEarthelectManimumDistanceWallInRem() {
		return earthelectManimumDistanceWallInRem;
	}

	public void setEarthelectManimumDistanceWallInRem(String earthelectManimumDistanceWallInRem) {
		this.earthelectManimumDistanceWallInRem = earthelectManimumDistanceWallInRem;
	}

	public String getEarthelectMaxiDistOb() {
		return earthelectMaxiDistOb;
	}

	public void setEarthelectMaxiDistOb(String earthelectMaxiDistOb) {
		this.earthelectMaxiDistOb = earthelectMaxiDistOb;
	}

	public String getEarthelectMaxiDistRem() {
		return earthelectMaxiDistRem;
	}

	public void setEarthelectMaxiDistRem(String earthelectMaxiDistRem) {
		this.earthelectMaxiDistRem = earthelectMaxiDistRem;
	}

	public String getEarthelectManiDistOb() {
		return earthelectManiDistOb;
	}

	public void setEarthelectManiDistOb(String earthelectManiDistOb) {
		this.earthelectManiDistOb = earthelectManiDistOb;
	}

	public String getEarthelectManiDistRem() {
		return earthelectManiDistRem;
	}

	public void setEarthelectManiDistRem(String earthelectManiDistRem) {
		this.earthelectManiDistRem = earthelectManiDistRem;
	}

	public String getTotalNumberOfElectrodeOb() {
		return totalNumberOfElectrodeOb;
	}

	public void setTotalNumberOfElectrodeOb(String totalNumberOfElectrodeOb) {
		this.totalNumberOfElectrodeOb = totalNumberOfElectrodeOb;
	}

	public String getTotalNumberOfElectrodeRem() {
		return totalNumberOfElectrodeRem;
	}

	public void setTotalNumberOfElectrodeRem(String totalNumberOfElectrodeRem) {
		this.totalNumberOfElectrodeRem = totalNumberOfElectrodeRem;
	}

	public String getInspectedFailedNoRem() {
		return inspectedFailedNoRem;
	}

	public void setInspectedFailedNoRem(String inspectedFailedNoRem) {
		this.inspectedFailedNoRem = inspectedFailedNoRem;
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

	public String getInspectedPassedNoOb() {
		return inspectedPassedNoOb;
	}

	public void setInspectedPassedNoOb(String inspectedPassedNoOb) {
		this.inspectedPassedNoOb = inspectedPassedNoOb;
	}

	public String getInspectedPassedNoRem() {
		return inspectedPassedNoRem;
	}

	public void setInspectedPassedNoRem(String inspectedPassedNoRem) {
		this.inspectedPassedNoRem = inspectedPassedNoRem;
	}

	public String getInspectedFailedNoOb() {
		return inspectedFailedNoOb;
	}

	public void setInspectedFailedNoOb(String inspectedFailedNoOb) {
		this.inspectedFailedNoOb = inspectedFailedNoOb;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}