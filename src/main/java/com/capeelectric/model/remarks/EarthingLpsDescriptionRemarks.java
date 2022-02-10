/**
 * 
 */
package com.capeelectric.model.remarks;

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

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "EARTHING_LPS_DESCRIPTION")
public class EarthingLpsDescriptionRemarks implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_ID")
	private Integer earthingId;
	
	@Column(name = "EARTHING_TYPEINREM")
	private String earthingTypeInRem;
		
	@Column(name = "BIMETALLIC_ISSUEINREM")
	private String bimetallicIssueInRem;
		
	@Column(name = "BRAZING_CONNECTIONSINREM")
	private String brazingConnectInRem;
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "FLAG")
	private String flag;
		
	@Column(name = "EARTHING_DESC_AVAILABILITY_REM")
	private String earthingDescriptionAvailabilityRem;
		
	@Column(name = "EARTHING_CLAMPS_AVAILABILITY_REM")
	private String earthingClampsAvailabilityRem;
		
	@Column(name = "EARTHING_ELECTRODE_CHAMBER_AVAILABILITY_REM")
	private String earthingElectrodeChamberAvailabilityRem;
		
	@Column(name = "EARTHING_SYSTEM_AVAILABILITY_REM")
	private String earthingSystemAvailabilityRem;
		
	@Column(name = "EARTHING_ELECTRODE_TESTING_AVAILABILITY_REM")
	private String earthingElectrodeTestingAvailabilityRem;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingDescriptionRemarks> earthingDescription;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingClampsRemarks> earthingClamps;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthElectrodeChamberRemarks> earthingElectrodeChamber;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthingSystemRemarks> earthingSystem;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "earthingLpsDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthElectrodeTestingRemarks> earthElectrodeTesting;
	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_REPORT_ID")
	private EarthingReportRemarks earthingReport;


	public Integer getEarthingId() {
		return earthingId;
	}


	public void setEarthingId(Integer earthingId) {
		this.earthingId = earthingId;
	}


	public String getEarthingTypeInRem() {
		return earthingTypeInRem;
	}


	public void setEarthingTypeInRem(String earthingTypeInRem) {
		this.earthingTypeInRem = earthingTypeInRem;
	}


	public String getBimetallicIssueInRem() {
		return bimetallicIssueInRem;
	}


	public void setBimetallicIssueInRem(String bimetallicIssueInRem) {
		this.bimetallicIssueInRem = bimetallicIssueInRem;
	}


	public String getBrazingConnectInRem() {
		return brazingConnectInRem;
	}


	public void setBrazingConnectInRem(String brazingConnectInRem) {
		this.brazingConnectInRem = brazingConnectInRem;
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


	public String getEarthingDescriptionAvailabilityRem() {
		return earthingDescriptionAvailabilityRem;
	}


	public void setEarthingDescriptionAvailabilityRem(String earthingDescriptionAvailabilityRem) {
		this.earthingDescriptionAvailabilityRem = earthingDescriptionAvailabilityRem;
	}


	public String getEarthingClampsAvailabilityRem() {
		return earthingClampsAvailabilityRem;
	}


	public void setEarthingClampsAvailabilityRem(String earthingClampsAvailabilityRem) {
		this.earthingClampsAvailabilityRem = earthingClampsAvailabilityRem;
	}


	public String getEarthingElectrodeChamberAvailabilityRem() {
		return earthingElectrodeChamberAvailabilityRem;
	}


	public void setEarthingElectrodeChamberAvailabilityRem(String earthingElectrodeChamberAvailabilityRem) {
		this.earthingElectrodeChamberAvailabilityRem = earthingElectrodeChamberAvailabilityRem;
	}


	public String getEarthingSystemAvailabilityRem() {
		return earthingSystemAvailabilityRem;
	}


	public void setEarthingSystemAvailabilityRem(String earthingSystemAvailabilityRem) {
		this.earthingSystemAvailabilityRem = earthingSystemAvailabilityRem;
	}


	public String getEarthingElectrodeTestingAvailabilityRem() {
		return earthingElectrodeTestingAvailabilityRem;
	}


	public void setEarthingElectrodeTestingAvailabilityRem(String earthingElectrodeTestingAvailabilityRem) {
		this.earthingElectrodeTestingAvailabilityRem = earthingElectrodeTestingAvailabilityRem;
	}


	public List<EarthingDescriptionRemarks> getEarthingDescription() {
		return earthingDescription;
	}


	public void setEarthingDescription(List<EarthingDescriptionRemarks> earthingDescription) {
		this.earthingDescription = earthingDescription;
	}


	public List<EarthingClampsRemarks> getEarthingClamps() {
		return earthingClamps;
	}


	public void setEarthingClamps(List<EarthingClampsRemarks> earthingClamps) {
		this.earthingClamps = earthingClamps;
	}


	public List<EarthElectrodeChamberRemarks> getEarthingElectrodeChamber() {
		return earthingElectrodeChamber;
	}


	public void setEarthingElectrodeChamber(List<EarthElectrodeChamberRemarks> earthingElectrodeChamber) {
		this.earthingElectrodeChamber = earthingElectrodeChamber;
	}


	public List<EarthingSystemRemarks> getEarthingSystem() {
		return earthingSystem;
	}


	public void setEarthingSystem(List<EarthingSystemRemarks> earthingSystem) {
		this.earthingSystem = earthingSystem;
	}


	public List<EarthElectrodeTestingRemarks> getEarthElectrodeTesting() {
		return earthElectrodeTesting;
	}


	public void setEarthElectrodeTesting(List<EarthElectrodeTestingRemarks> earthElectrodeTesting) {
		this.earthElectrodeTesting = earthElectrodeTesting;
	}


	public EarthingReportRemarks getEarthingReport() {
		return earthingReport;
	}


	public void setEarthingReport(EarthingReportRemarks earthingReport) {
		this.earthingReport = earthingReport;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
