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
@Table(name = "EARTHDESCRIPTION_TABLE")
public class EarthingDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHDESCRIPTION_ID")
	private Integer earthDescriptionId;

	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "SOIL_RESISTIVITYINOB")
	private Integer soilResistivityInOb;

	@Column(name = "SOIL_RESISTIVITYINREM")
	private String soilResistivityInRem;

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

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEWALLINOB")
	private Integer earthelectMaxiDistWallInOb;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEWALLINREM")
	private String earthelectMaxiDistWallInRem;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEWALLINOB")
	private Integer earthelectManimumDistanceWallInOb;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEWALLINREM")
	private String earthelectManimumDistanceWallInRem;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEINOB")
	private Integer earthelectMaxiDistOb;

	@Column(name = "EARTHELECT_MAXIMUMDISTANCEINREM")
	private String earthelectMaxiDistRem;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEINOB")
	private Integer earthelectManiDistOb;

	@Column(name = "EARTHELECT_MINIMUMDISTANCEINREM")
	private String earthelectManiDistRem;

	@Column(name = "TOTALNUMBER_OF_ELECTRODEINOB")
	private Integer totalNumberOfElectrodeOb;

	@Column(name = "TOTALNUMBER_OF_ELECTRODEINREM")
	private String totalNumberOfElectrodeRem;

	@Column(name = "INSPECTED_NO_OB")
	private Integer inspectedNoOb;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private Integer inspectedPassedNoOb;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectedPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_OB")
	private Integer inspectedFailedNoOb;
	
	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectedFailedNoRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingDescriptionList> earthingDescriptionList;

	public Integer getEarthDescriptionId() {
		return earthDescriptionId;
	}

	public void setEarthDescriptionId(Integer earthDescriptionId) {
		this.earthDescriptionId = earthDescriptionId;
	}

	

	public Integer getSoilResistivityInOb() {
		return soilResistivityInOb;
	}

	public void setSoilResistivityInOb(Integer soilResistivityInOb) {
		this.soilResistivityInOb = soilResistivityInOb;
	}

	public String getSoilResistivityInRem() {
		return soilResistivityInRem;
	}

	public void setSoilResistivityInRem(String soilResistivityInRem) {
		this.soilResistivityInRem = soilResistivityInRem;
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

	public String getEarthelectMaxiDistWallInRem() {
		return earthelectMaxiDistWallInRem;
	}

	public void setEarthelectMaxiDistWallInRem(String earthelectMaxiDistWallInRem) {
		this.earthelectMaxiDistWallInRem = earthelectMaxiDistWallInRem;
	}


	public String getEarthelectManimumDistanceWallInRem() {
		return earthelectManimumDistanceWallInRem;
	}

	public void setEarthelectManimumDistanceWallInRem(String earthelectManimumDistanceWallInRem) {
		this.earthelectManimumDistanceWallInRem = earthelectManimumDistanceWallInRem;
	}

	public String getEarthelectMaxiDistRem() {
		return earthelectMaxiDistRem;
	}

	public void setEarthelectMaxiDistRem(String earthelectMaxiDistRem) {
		this.earthelectMaxiDistRem = earthelectMaxiDistRem;
	}

	public String getEarthelectManiDistRem() {
		return earthelectManiDistRem;
	}

	public void setEarthelectManiDistRem(String earthelectManiDistRem) {
		this.earthelectManiDistRem = earthelectManiDistRem;
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

	public String getInspectedNoRem() {
		return inspectedNoRem;
	}

	public void setInspectedNoRem(String inspectedNoRem) {
		this.inspectedNoRem = inspectedNoRem;
	}

	public String getInspectedPassedNoRem() {
		return inspectedPassedNoRem;
	}

	public void setInspectedPassedNoRem(String inspectedPassedNoRem) {
		this.inspectedPassedNoRem = inspectedPassedNoRem;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getEarthelectMaxiDistWallInOb() {
		return earthelectMaxiDistWallInOb;
	}

	public void setEarthelectMaxiDistWallInOb(Integer earthelectMaxiDistWallInOb) {
		this.earthelectMaxiDistWallInOb = earthelectMaxiDistWallInOb;
	}

	public Integer getEarthelectManimumDistanceWallInOb() {
		return earthelectManimumDistanceWallInOb;
	}

	public void setEarthelectManimumDistanceWallInOb(Integer earthelectManimumDistanceWallInOb) {
		this.earthelectManimumDistanceWallInOb = earthelectManimumDistanceWallInOb;
	}

	public Integer getEarthelectMaxiDistOb() {
		return earthelectMaxiDistOb;
	}

	public void setEarthelectMaxiDistOb(Integer earthelectMaxiDistOb) {
		this.earthelectMaxiDistOb = earthelectMaxiDistOb;
	}

	public Integer getEarthelectManiDistOb() {
		return earthelectManiDistOb;
	}

	public void setEarthelectManiDistOb(Integer earthelectManiDistOb) {
		this.earthelectManiDistOb = earthelectManiDistOb;
	}

	public Integer getTotalNumberOfElectrodeOb() {
		return totalNumberOfElectrodeOb;
	}

	public void setTotalNumberOfElectrodeOb(Integer totalNumberOfElectrodeOb) {
		this.totalNumberOfElectrodeOb = totalNumberOfElectrodeOb;
	}

	public Integer getInspectedNoOb() {
		return inspectedNoOb;
	}

	public void setInspectedNoOb(Integer inspectedNoOb) {
		this.inspectedNoOb = inspectedNoOb;
	}

	public Integer getInspectedPassedNoOb() {
		return inspectedPassedNoOb;
	}

	public void setInspectedPassedNoOb(Integer inspectedPassedNoOb) {
		this.inspectedPassedNoOb = inspectedPassedNoOb;
	}

	public Integer getInspectedFailedNoOb() {
		return inspectedFailedNoOb;
	}

	public void setInspectedFailedNoOb(Integer inspectedFailedNoOb) {
		this.inspectedFailedNoOb = inspectedFailedNoOb;
	}

	public List<EarthingDescriptionList> getEarthingDescriptionList() {
		return earthingDescriptionList;
	}

	public void setEarthingDescriptionList(List<EarthingDescriptionList> earthingDescriptionList) {
		this.earthingDescriptionList = earthingDescriptionList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}