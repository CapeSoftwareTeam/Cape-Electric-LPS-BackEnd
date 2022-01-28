/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
