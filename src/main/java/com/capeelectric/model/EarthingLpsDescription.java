/**
 * 
 */
package com.capeelectric.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Entity
@Table(name = "EARTHING_LPS_DESCRIPTION")
public class EarthingLpsDescription {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_ID")
	private Integer earthingId;
	
	@Column(name = "EARTHING_TYPEINOB")
	private String earthingTypeInOb;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
	@Column(name = "EARTHING_TYPEINREM")
	private String earthingTypeInRem;
	
	@Column(name = "BIMETALLIC_ISSUEINOB")
	private String bimetallicIssueInOb;
	
	@Column(name = "BIMETALLIC_ISSUEINREM")
	private String bimetallicIssueInRem;
	
	@Column(name = "BRAZING_CONNECTIONSINOB")
	private String brazingConnectInOb;
	
	@Column(name = "BRAZING_CONNECTIONSINREM")
	private String brazingConnectInRem;
	
	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;
	
	@Column(name = "LOCATION_NAME")
	private String locationName;
	
	@Column(name = "SOIL_RESISTIVITYINOB")
	private String soilResistivityInOb;
	
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
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingDescription> earthingDescription;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingClamps> earthingClamps;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthElectrodeChamber> earthingElectrodeChamber;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingSystem> earthingSystem;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingInspection> earthingInspection;

	public Integer getEarthingId() {
		return earthingId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<EarthingDescription> getEarthingDescription() {
		return earthingDescription;
	}

	public void setEarthingDescription(List<EarthingDescription> earthingDescription) {
		this.earthingDescription = earthingDescription;
	}

	public List<EarthElectrodeChamber> getEarthingElectrodeChamber() {
		return earthingElectrodeChamber;
	}

	public void setEarthingElectrodeChamber(List<EarthElectrodeChamber> earthingElectrodeChamber) {
		this.earthingElectrodeChamber = earthingElectrodeChamber;
	}

	public List<EarthingSystem> getEarthingSystem() {
		return earthingSystem;
	}

	public void setEarthingSystem(List<EarthingSystem> earthingSystem) {
		this.earthingSystem = earthingSystem;
	}

	public List<EarthingInspection> getEarthingInspection() {
		return earthingInspection;
	}

	public void setEarthingInspection(List<EarthingInspection> earthingInspection) {
		this.earthingInspection = earthingInspection;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setEarthingId(Integer earthingId) {
		this.earthingId = earthingId;
	}

	public String getEarthingTypeInOb() {
		return earthingTypeInOb;
	}

	public void setEarthingTypeInOb(String earthingTypeInOb) {
		this.earthingTypeInOb = earthingTypeInOb;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getBasicLpsId() {
		return basicLpsId;
	}

	public void setBasicLpsId(Integer basicLpsId) {
		this.basicLpsId = basicLpsId;
	}

	public String getEarthingTypeInRem() {
		return earthingTypeInRem;
	}

	public void setEarthingTypeInRem(String earthingTypeInRem) {
		this.earthingTypeInRem = earthingTypeInRem;
	}

	public String getBimetallicIssueInOb() {
		return bimetallicIssueInOb;
	}

	public void setBimetallicIssueInOb(String bimetallicIssueInOb) {
		this.bimetallicIssueInOb = bimetallicIssueInOb;
	}

	public String getBimetallicIssueInRem() {
		return bimetallicIssueInRem;
	}

	public void setBimetallicIssueInRem(String bimetallicIssueInRem) {
		this.bimetallicIssueInRem = bimetallicIssueInRem;
	}

	public String getBrazingConnectInOb() {
		return brazingConnectInOb;
	}

	public void setBrazingConnectInOb(String brazingConnectInOb) {
		this.brazingConnectInOb = brazingConnectInOb;
	}

	public String getBrazingConnectInRem() {
		return brazingConnectInRem;
	}

	public void setBrazingConnectInRem(String brazingConnectInRem) {
		this.brazingConnectInRem = brazingConnectInRem;
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

	public List<EarthingClamps> getEarthingClamps() {
		return earthingClamps;
	}

	public void setEarthingClamps(List<EarthingClamps> earthingClamps) {
		this.earthingClamps = earthingClamps;
	}

	public void setUpdatedDate(LocalDateTime now) {
		// TODO Auto-generated method stub
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
	
	
}
