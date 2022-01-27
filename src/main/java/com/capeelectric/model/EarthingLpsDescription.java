/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

/**
 * @author CAPE-SOFTWARE
 *
 */

@Entity
@Table(name = "EARTHING_LPS_DESCRIPTION")
public class EarthingLpsDescription implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_ID")
	private Integer earthingId;
	
	@Column(name = "EARTHING_TYPEINOB")
	private String earthingTypeInOb;
	
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
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
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
	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<EarthingInspection> earthingInspection;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_REPORT_ID")
	private EarthingReport earthingReport;

	public Integer getEarthingId() {
		return earthingId;
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

//	public List<EarthingInspection> getEarthingInspection() {
//		return earthingInspection;
//	}
//
//	public void setEarthingInspection(List<EarthingInspection> earthingInspection) {
//		this.earthingInspection = earthingInspection;
//	}


	public void setEarthingId(Integer earthingId) {
		this.earthingId = earthingId;
	}

	public String getEarthingTypeInOb() {
		return earthingTypeInOb;
	}

	public void setEarthingTypeInOb(String earthingTypeInOb) {
		this.earthingTypeInOb = earthingTypeInOb;
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

	public List<EarthingClamps> getEarthingClamps() {
		return earthingClamps;
	}

	public void setEarthingClamps(List<EarthingClamps> earthingClamps) {
		this.earthingClamps = earthingClamps;
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

	public EarthingReport getEarthingReport() {
		return earthingReport;
	}

	public void setEarthingReport(EarthingReport earthingReport) {
		this.earthingReport = earthingReport;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
