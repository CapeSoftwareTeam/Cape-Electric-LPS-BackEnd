/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
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
	
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	
	public Integer getEarthStudDescId() {
		return earthStudDescId;
	}

	public void setEarthStudDescId(Integer earthStudDescId) {
		this.earthStudDescId = earthStudDescId;
	}

	public Integer getBasicLpsId() {
		return basicLpsId;
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

	public void setBasicLpsId(Integer basicLpsId) {
		this.basicLpsId = basicLpsId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
