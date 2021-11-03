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

	public List<EarthingClamps> getEarthingClamps() {
		return earthingClamps;
	}

	public void setEarthingClamps(List<EarthingClamps> earthingClamps) {
		this.earthingClamps = earthingClamps;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
