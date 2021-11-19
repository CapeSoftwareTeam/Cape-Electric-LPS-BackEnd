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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "SPD_TABLE")
public class SPD implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SPD_ID")
	private Integer spdId;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "MAINS_INCOMING_OB")
	private String mainsIncomingOb;
	
	@Column(name = "MAINS_INCOMING_REM")
	private String mainsIncomingRem;
	
	@Column(name = "TOTALMAINS_INCOMING_OB")
	private String totalMainsIncomingOb;
	
	
	@Column(name = "TOTALMAINS_INCOMING_REM")
	private String totalMainsIncomingRem;
	
	@Column(name = "NO_PANNELSUPPLITTING_OB")
	private String noPannelSupplittingOb;
	
	@Column(name = "NO_PANNELSUPPLITTING_REM")
	private String noPannelSupplittingRem;

	@Column(name = "TOTAL_NO_OUTDOOREQUIPMENT_OB")
	private String totalNoOutDoorRequipmentOb;
	
	@Column(name = "TOTAL_NO_OUTDOOREQUIPMENT_REM")
	private String totalNoOutDoorRequipmentRem;
	
		
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "spd", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SpdDescription> spdDescription;

	public Integer getSpdId() {
		return spdId;
	}

	public void setSpdId(Integer spdId) {
		this.spdId = spdId;
	}

	public Integer getBasicLpsId() {
		return basicLpsId;
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

	public String getMainsIncomingOb() {
		return mainsIncomingOb;
	}

	public void setMainsIncomingOb(String mainsIncomingOb) {
		this.mainsIncomingOb = mainsIncomingOb;
	}

	public String getMainsIncomingRem() {
		return mainsIncomingRem;
	}

	public void setMainsIncomingRem(String mainsIncomingRem) {
		this.mainsIncomingRem = mainsIncomingRem;
	}

	public String getTotalMainsIncomingOb() {
		return totalMainsIncomingOb;
	}

	public void setTotalMainsIncomingOb(String totalMainsIncomingOb) {
		this.totalMainsIncomingOb = totalMainsIncomingOb;
	}

	public String getTotalMainsIncomingRem() {
		return totalMainsIncomingRem;
	}

	public void setTotalMainsIncomingRem(String totalMainsIncomingRem) {
		this.totalMainsIncomingRem = totalMainsIncomingRem;
	}

	public String getNoPannelSupplittingOb() {
		return noPannelSupplittingOb;
	}

	public void setNoPannelSupplittingOb(String noPannelSupplittingOb) {
		this.noPannelSupplittingOb = noPannelSupplittingOb;
	}

	public String getNoPannelSupplittingRem() {
		return noPannelSupplittingRem;
	}

	public void setNoPannelSupplittingRem(String noPannelSupplittingRem) {
		this.noPannelSupplittingRem = noPannelSupplittingRem;
	}

	public String getTotalNoOutDoorRequipmentOb() {
		return totalNoOutDoorRequipmentOb;
	}

	public void setTotalNoOutDoorRequipmentOb(String totalNoOutDoorRequipmentOb) {
		this.totalNoOutDoorRequipmentOb = totalNoOutDoorRequipmentOb;
	}

	public String getTotalNoOutDoorRequipmentRem() {
		return totalNoOutDoorRequipmentRem;
	}

	public void setTotalNoOutDoorRequipmentRem(String totalNoOutDoorRequipmentRem) {
		this.totalNoOutDoorRequipmentRem = totalNoOutDoorRequipmentRem;
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

	public List<SpdDescription> getSpdDescription() {
		return spdDescription;
	}

	public void setSpdDescription(List<SpdDescription> spdDescription) {
		this.spdDescription = spdDescription;
	}

	
}
