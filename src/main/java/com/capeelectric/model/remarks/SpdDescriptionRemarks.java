/**
 * 
 */
package com.capeelectric.model.remarks;

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
@Table(name = "SPD_DESCRIPTION_TABLE")
public class SpdDescriptionRemarks implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SPDDESCRIPTION_ID")
	private Integer SpdDescriptionId;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "PANEL_NAME")
	private String panelName;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "SPD_MAKE_REMARKS")
	private String spdMakeRem;
	
	@Column(name = "SPD_MODEL_REMARKS")
	private String spdModelRem;
	
	@Column(name = "SPD_CLASS_TYPE_REMARKS")
	private String spdClassTypeRem;
	
	@Column(name = "SPD_APPLICATION_REMARKS")
	private String spdApplicationRem;
	
	@Column(name = "SPD_MAIN_APPLICATION_REMARKS")
	private String spdMainApplicationRem;
	
	@Column(name = "PROPERCONNECTION_REMARKS")
	private String properConnectionRem;
	
	@Column(name = "INCOMER_RATING_REMARKS")
	private String incomerRatingRem;
	
	@Column(name = "FUSE_BACKUP_REMARKS")
	private String fuseBackUpRem;

//	@Column(name = "INLINE_FUSE_BACKUP_REMARKS")
//	private String inLinefuseBackUpRem;
	
	@Column(name = "CONNECTING_WIRE_LENGTH_PHASE_REMARKS")
	private String lengthOfConnectingWirePhaseRem;
	
	@Column(name = "CONNECTING_WIRE_LENGTH_PROTECTIVE_REMARKS")
	private String lengthOfConnectingWireProtectiveRem;
	
	@Column(name = "CONNECTING_WIRE_SIZE_PHASE_REMARKS")
	private String sizeOfConnectingWirePhaseRem;
	
	@Column(name = "CONNECTING_WIRE_SIZE_PROTECTIVE_REMARKS")
	private String sizeOfConnectingWireProtectiveRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SPD_ID")
	private SPDRemarks spd;

	public Integer getSpdDescriptionId() {
		return SpdDescriptionId;
	}

	public void setSpdDescriptionId(Integer spdDescriptionId) {
		SpdDescriptionId = spdDescriptionId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSpdMakeRem() {
		return spdMakeRem;
	}

	public void setSpdMakeRem(String spdMakeRem) {
		this.spdMakeRem = spdMakeRem;
	}

	public String getSpdModelRem() {
		return spdModelRem;
	}

	public void setSpdModelRem(String spdModelRem) {
		this.spdModelRem = spdModelRem;
	}

	public String getSpdClassTypeRem() {
		return spdClassTypeRem;
	}

	public void setSpdClassTypeRem(String spdClassTypeRem) {
		this.spdClassTypeRem = spdClassTypeRem;
	}

	public String getSpdApplicationRem() {
		return spdApplicationRem;
	}

	public void setSpdApplicationRem(String spdApplicationRem) {
		this.spdApplicationRem = spdApplicationRem;
	}

	public String getSpdMainApplicationRem() {
		return spdMainApplicationRem;
	}

	public void setSpdMainApplicationRem(String spdMainApplicationRem) {
		this.spdMainApplicationRem = spdMainApplicationRem;
	}

	public String getProperConnectionRem() {
		return properConnectionRem;
	}

	public void setProperConnectionRem(String properConnectionRem) {
		this.properConnectionRem = properConnectionRem;
	}

	public String getIncomerRatingRem() {
		return incomerRatingRem;
	}

	public void setIncomerRatingRem(String incomerRatingRem) {
		this.incomerRatingRem = incomerRatingRem;
	}

	public String getFuseBackUpRem() {
		return fuseBackUpRem;
	}

	public void setFuseBackUpRem(String fuseBackUpRem) {
		this.fuseBackUpRem = fuseBackUpRem;
	}

	public String getLengthOfConnectingWirePhaseRem() {
		return lengthOfConnectingWirePhaseRem;
	}

	public void setLengthOfConnectingWirePhaseRem(String lengthOfConnectingWirePhaseRem) {
		this.lengthOfConnectingWirePhaseRem = lengthOfConnectingWirePhaseRem;
	}

	public String getLengthOfConnectingWireProtectiveRem() {
		return lengthOfConnectingWireProtectiveRem;
	}

	public void setLengthOfConnectingWireProtectiveRem(String lengthOfConnectingWireProtectiveRem) {
		this.lengthOfConnectingWireProtectiveRem = lengthOfConnectingWireProtectiveRem;
	}

	public String getSizeOfConnectingWirePhaseRem() {
		return sizeOfConnectingWirePhaseRem;
	}

	public void setSizeOfConnectingWirePhaseRem(String sizeOfConnectingWirePhaseRem) {
		this.sizeOfConnectingWirePhaseRem = sizeOfConnectingWirePhaseRem;
	}

	public String getSizeOfConnectingWireProtectiveRem() {
		return sizeOfConnectingWireProtectiveRem;
	}

	public void setSizeOfConnectingWireProtectiveRem(String sizeOfConnectingWireProtectiveRem) {
		this.sizeOfConnectingWireProtectiveRem = sizeOfConnectingWireProtectiveRem;
	}

	public SPDRemarks getSpd() {
		return spd;
	}

	public void setSpd(SPDRemarks spd) {
		this.spd = spd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
