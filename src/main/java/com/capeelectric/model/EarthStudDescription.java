/**
 * 
 */
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

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "EARTH_STUD_DESCRIPTION")
public class EarthStudDescription implements Serializable{

	private static final long serialVersionUID = -7161836502468880542L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTH_STUD_DESC_ID")
	private Integer earthStudDescId;
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "AVAILABLE_EQUIPOTENTIAL_BONDING_OB")
	private String availableEquipotentialBondingOb;
	
	@Column(name = "AVAILABLE_EQUIPOTENTIAL_BONDING_REM")
	private String availableEquipotentialBondingRem;
	
	@Column(name = "NUMBER_OF_EQUIPOTENTIAL_BONDING_OB")
	private String numberOfEquipotentialBondingOb;
	
	@Column(name = "NUMBER_OF_EQUIPOTENTIAL_BONDING_REM")
	private String numberOfEquipotentialBondingRem;
	
	@Column(name = "SIZE_OF_EARTHING_CONDUCTOR_OB")
	private String sizeOfEarthingConductorOb;
	
	@Column(name = "SIZE_OF_EARTHING_CONDUCTOR_REM")
	private String sizeOfEarthingConductorRem;
	
	@Column(name = "CONCEPT_OF_EQUIPOTENTIAL_BONDING_OB")
	private String conceptOfEquipotentialBondingOb;
	
	@Column(name = "CONCEPT_OF_EQUIPOTENTIAL_BONDING_REM")
	private String conceptOfEquipotentialBondingRem;
	
	@Column(name = "MAINPROTECTIVE_EQUIPOTENTIAL_BONDING_OB")
	private String mainProtectiveEquipotentialBondingOb;
	
	@Column(name = "MAINPROTECTIVE_EQUIPOTENTIAL_BONDING_REM")
	private String mainProtectiveEquipotentialBondingRem;
	
	@Column(name = "SIZE_OF_MAINPROTECTIVE_OB")
	private String sizeOfMainProtectiveOb;
	
	@Column(name = "SIZE_OF_MAINPROTECTIVE_REM")
	private String sizeOfMainProtectiveRem;
	
	@Column(name = "SUPPLIMENTARY_MAINPROTECTIVE_OB")
	private String supplimentaryMainProtectiveOb;
	
	@Column(name = "SUPPLIMENTARY_MAINPROTECTIVE_REM")
	private String supplimentaryMainProtectiveRem;
	
	@Column(name = "SIZE_OF_SUPPLIMENTARY_PROTECTIVE_OB")
	private String sizeOfSupplimentaryProtectiveOb;
	
	@Column(name = "SIZE_OF_SUPPLIMENTARY_PROTECTIVE_REM")
	private String sizeOfSupplimentaryProtectiveRem;
	
	@Column(name = "EARTH_STUDVISIBILITYOB")
	private String earthStudVisibilityOb;
	
	@Column(name = "EARTH_STUDVISIBILITYREM")
	private String earthStudVisibilityRem;
	
	@Column(name = "EARTH_STUDBENDOB")
	private String earthStudBendOb;
	
	@Column(name = "EARTH_STUDBENDREM")
	private String earthStudBendRem;
	
	@Column(name = "PROPER_BONDINGRAILOB")
	private String properBondingRailOb;
	
	@Column(name = "PROPER_BONDINGRAILREM")
	private String properBondingRailRem;
	
	@Column(name = "PHYSICAL_DAMAGESTUDOB")
	private String physicalDamageStudOb;
	
	@Column(name = "PHYSICA_LDAMAGESTUDREM")
	private String physicalDamageStudRem;
	
	@Column(name = "CONTINUTY_EXISTAEARTHOB")
	private String continutyExistaEarthOb;
	
	@Column(name = "CONTINUTY_EXISTAEARTHREM")
	private String continutyExistaEarthRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTH_STUD_REPORT_ID")
	private EarthStudReport earthStudReport;
	
	public Integer getEarthStudDescId() {
		return earthStudDescId;
	}

	public void setEarthStudDescId(Integer earthStudDescId) {
		this.earthStudDescId = earthStudDescId;
	}
	

	public String getAvailableEquipotentialBondingOb() {
		return availableEquipotentialBondingOb;
	}

	public void setAvailableEquipotentialBondingOb(String availableEquipotentialBondingOb) {
		this.availableEquipotentialBondingOb = availableEquipotentialBondingOb;
	}

	public String getAvailableEquipotentialBondingRem() {
		return availableEquipotentialBondingRem;
	}

	public void setAvailableEquipotentialBondingRem(String availableEquipotentialBondingRem) {
		this.availableEquipotentialBondingRem = availableEquipotentialBondingRem;
	}

	public String getNumberOfEquipotentialBondingOb() {
		return numberOfEquipotentialBondingOb;
	}

	public void setNumberOfEquipotentialBondingOb(String numberOfEquipotentialBondingOb) {
		this.numberOfEquipotentialBondingOb = numberOfEquipotentialBondingOb;
	}

	public String getNumberOfEquipotentialBondingRem() {
		return numberOfEquipotentialBondingRem;
	}

	public void setNumberOfEquipotentialBondingRem(String numberOfEquipotentialBondingRem) {
		this.numberOfEquipotentialBondingRem = numberOfEquipotentialBondingRem;
	}

	public String getSizeOfEarthingConductorOb() {
		return sizeOfEarthingConductorOb;
	}

	public void setSizeOfEarthingConductorOb(String sizeOfEarthingConductorOb) {
		this.sizeOfEarthingConductorOb = sizeOfEarthingConductorOb;
	}

	public String getSizeOfEarthingConductorRem() {
		return sizeOfEarthingConductorRem;
	}

	public void setSizeOfEarthingConductorRem(String sizeOfEarthingConductorRem) {
		this.sizeOfEarthingConductorRem = sizeOfEarthingConductorRem;
	}

	public String getConceptOfEquipotentialBondingOb() {
		return conceptOfEquipotentialBondingOb;
	}

	public void setConceptOfEquipotentialBondingOb(String conceptOfEquipotentialBondingOb) {
		this.conceptOfEquipotentialBondingOb = conceptOfEquipotentialBondingOb;
	}

	public String getConceptOfEquipotentialBondingRem() {
		return conceptOfEquipotentialBondingRem;
	}

	public void setConceptOfEquipotentialBondingRem(String conceptOfEquipotentialBondingRem) {
		this.conceptOfEquipotentialBondingRem = conceptOfEquipotentialBondingRem;
	}

	public String getMainProtectiveEquipotentialBondingOb() {
		return mainProtectiveEquipotentialBondingOb;
	}

	public void setMainProtectiveEquipotentialBondingOb(String mainProtectiveEquipotentialBondingOb) {
		this.mainProtectiveEquipotentialBondingOb = mainProtectiveEquipotentialBondingOb;
	}

	public String getMainProtectiveEquipotentialBondingRem() {
		return mainProtectiveEquipotentialBondingRem;
	}

	public void setMainProtectiveEquipotentialBondingRem(String mainProtectiveEquipotentialBondingRem) {
		this.mainProtectiveEquipotentialBondingRem = mainProtectiveEquipotentialBondingRem;
	}

	public String getSizeOfMainProtectiveOb() {
		return sizeOfMainProtectiveOb;
	}

	public void setSizeOfMainProtectiveOb(String sizeOfMainProtectiveOb) {
		this.sizeOfMainProtectiveOb = sizeOfMainProtectiveOb;
	}

	public String getSizeOfMainProtectiveRem() {
		return sizeOfMainProtectiveRem;
	}

	public void setSizeOfMainProtectiveRem(String sizeOfMainProtectiveRem) {
		this.sizeOfMainProtectiveRem = sizeOfMainProtectiveRem;
	}

	public String getSupplimentaryMainProtectiveOb() {
		return supplimentaryMainProtectiveOb;
	}

	public void setSupplimentaryMainProtectiveOb(String supplimentaryMainProtectiveOb) {
		this.supplimentaryMainProtectiveOb = supplimentaryMainProtectiveOb;
	}

	public String getSupplimentaryMainProtectiveRem() {
		return supplimentaryMainProtectiveRem;
	}

	public void setSupplimentaryMainProtectiveRem(String supplimentaryMainProtectiveRem) {
		this.supplimentaryMainProtectiveRem = supplimentaryMainProtectiveRem;
	}

	public String getEarthStudVisibilityOb() {
		return earthStudVisibilityOb;
	}

	public void setEarthStudVisibilityOb(String earthStudVisibilityOb) {
		this.earthStudVisibilityOb = earthStudVisibilityOb;
	}

	public String getEarthStudVisibilityRem() {
		return earthStudVisibilityRem;
	}

	public void setEarthStudVisibilityRem(String earthStudVisibilityRem) {
		this.earthStudVisibilityRem = earthStudVisibilityRem;
	}

	public String getEarthStudBendOb() {
		return earthStudBendOb;
	}

	public void setEarthStudBendOb(String earthStudBendOb) {
		this.earthStudBendOb = earthStudBendOb;
	}

	public String getEarthStudBendRem() {
		return earthStudBendRem;
	}

	public void setEarthStudBendRem(String earthStudBendRem) {
		this.earthStudBendRem = earthStudBendRem;
	}

	public String getProperBondingRailOb() {
		return properBondingRailOb;
	}

	public void setProperBondingRailOb(String properBondingRailOb) {
		this.properBondingRailOb = properBondingRailOb;
	}

	public String getProperBondingRailRem() {
		return properBondingRailRem;
	}

	public void setProperBondingRailRem(String properBondingRailRem) {
		this.properBondingRailRem = properBondingRailRem;
	}

	public String getPhysicalDamageStudOb() {
		return physicalDamageStudOb;
	}

	public void setPhysicalDamageStudOb(String physicalDamageStudOb) {
		this.physicalDamageStudOb = physicalDamageStudOb;
	}

	public String getPhysicalDamageStudRem() {
		return physicalDamageStudRem;
	}

	public void setPhysicalDamageStudRem(String physicalDamageStudRem) {
		this.physicalDamageStudRem = physicalDamageStudRem;
	}

	public String getContinutyExistaEarthOb() {
		return continutyExistaEarthOb;
	}

	public void setContinutyExistaEarthOb(String continutyExistaEarthOb) {
		this.continutyExistaEarthOb = continutyExistaEarthOb;
	}

	public String getContinutyExistaEarthRem() {
		return continutyExistaEarthRem;
	}

	public void setContinutyExistaEarthRem(String continutyExistaEarthRem) {
		this.continutyExistaEarthRem = continutyExistaEarthRem;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getBuildingCount() {
		return buildingCount;
	}

	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public EarthStudReport getEarthStudReport() {
		return earthStudReport;
	}

	public void setEarthStudReport(EarthStudReport earthStudReport) {
		this.earthStudReport = earthStudReport;
	}
	
	

	public String getSizeOfSupplimentaryProtectiveOb() {
		return sizeOfSupplimentaryProtectiveOb;
	}

	public void setSizeOfSupplimentaryProtectiveOb(String sizeOfSupplimentaryProtectiveOb) {
		this.sizeOfSupplimentaryProtectiveOb = sizeOfSupplimentaryProtectiveOb;
	}

	public String getSizeOfSupplimentaryProtectiveRem() {
		return sizeOfSupplimentaryProtectiveRem;
	}

	public void setSizeOfSupplimentaryProtectiveRem(String sizeOfSupplimentaryProtectiveRem) {
		this.sizeOfSupplimentaryProtectiveRem = sizeOfSupplimentaryProtectiveRem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
