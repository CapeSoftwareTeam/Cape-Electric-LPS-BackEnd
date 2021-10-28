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

@Entity
@Table(name = "SPD_DESCRIPTION_TABLE")
public class SpdDescription implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SPDDESCRIPTION_ID")
	private Integer SpdDescriptionId;
	
	@Column(name = "SPDDESCRIPTION_ROLE")
	private String spdDescriptionRole;
	
	@Column(name = "SPD_TYPE_OB")
	private String spdTypeOb;
	
	@Column(name = "SPD_TYPE_RE")
	private String spdTypeRe;

    @Column(name = "SPD_APPLICATION_OB")
	private String spdApplicationOb;
	
	@Column(name = "SPD_APPLICATION_REM")
	private String spdApplicationRem;

	@Column(name = "PANEL_NAME_OB")
	private String panelNameOb;

	@Column(name = "PANEL_NAME_REM")
	private String panelNameRem;
	
	@Column(name = "INCOMING_RATING_OB")
	private String incomingRatingOb;

	@Column(name = "INCOMING_RATING_REM")
	private String incomingRatingRem;
	
	@Column(name = "BACKUPFUSE_CHECK_OB")
	private String backupFuseCheckOb;
	
	@Column(name = "BACKUPFUSE_CHECK_REM")
	private String backupFuseCheckRem;
	
	@Column(name = "CONNECTING_WIRELENGTH_OB")
	private String connectingWireLengthOb;
	
    @Column(name = "CONNECTING_WIRELENGTH_REM ")
	private String connectingWireLengthRem;
	
	@Column(name = "CONNECTING_WIRESIZE_OB")
	private String connectingWireSizeOb;

	@Column(name = "CONNECTING_WIRESIZE_REM")
	private String connectingWireSizeRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SPD_ID")
	private SPD spd;
	
	

	public Integer getSpdDescriptionId() {
		return SpdDescriptionId;
	}

	public void setSpdDescriptionId(Integer spdDescriptionId) {
		SpdDescriptionId = spdDescriptionId;
	}

	
	public String getSpdDescriptionRole() {
		return spdDescriptionRole;
	}

	public void setSpdDescriptionRole(String spdDescriptionRole) {
		this.spdDescriptionRole = spdDescriptionRole;
	}

	public String getSpdTypeOb() {
		return spdTypeOb;
	}

	public void setSpdTypeOb(String spdTypeOb) {
		this.spdTypeOb = spdTypeOb;
	}

	public String getSpdTypeRe() {
		return spdTypeRe;
	}

	public void setSpdTypeRe(String spdTypeRe) {
		this.spdTypeRe = spdTypeRe;
	}

	public String getSpdApplicationOb() {
		return spdApplicationOb;
	}

	public void setSpdApplicationOb(String spdApplicationOb) {
		this.spdApplicationOb = spdApplicationOb;
	}

	public String getSpdApplicationRem() {
		return spdApplicationRem;
	}

	public void setSpdApplicationRem(String spdApplicationRem) {
		this.spdApplicationRem = spdApplicationRem;
	}

	public String getPanelNameOb() {
		return panelNameOb;
	}

	public void setPanelNameOb(String panelNameOb) {
		this.panelNameOb = panelNameOb;
	}

	public String getPanelNameRem() {
		return panelNameRem;
	}

	public void setPanelNameRem(String panelNameRem) {
		this.panelNameRem = panelNameRem;
	}

	public String getIncomingRatingOb() {
		return incomingRatingOb;
	}

	public void setIncomingRatingOb(String incomingRatingOb) {
		this.incomingRatingOb = incomingRatingOb;
	}

	public String getIncomingRatingRem() {
		return incomingRatingRem;
	}

	public void setIncomingRatingRem(String incomingRatingRem) {
		this.incomingRatingRem = incomingRatingRem;
	}

	public String getBackupFuseCheckOb() {
		return backupFuseCheckOb;
	}

	public void setBackupFuseCheckOb(String backupFuseCheckOb) {
		this.backupFuseCheckOb = backupFuseCheckOb;
	}

	public String getBackupFuseCheckRem() {
		return backupFuseCheckRem;
	}

	public void setBackupFuseCheckRem(String backupFuseCheckRem) {
		this.backupFuseCheckRem = backupFuseCheckRem;
	}

	public String getConnectingWireLengthOb() {
		return connectingWireLengthOb;
	}

	public void setConnectingWireLengthOb(String connectingWireLengthOb) {
		this.connectingWireLengthOb = connectingWireLengthOb;
	}

	public String getConnectingWireLengthRem() {
		return connectingWireLengthRem;
	}

	public void setConnectingWireLengthRem(String connectingWireLengthRem) {
		this.connectingWireLengthRem = connectingWireLengthRem;
	}

	public String getConnectingWireSizeOb() {
		return connectingWireSizeOb;
	}

	public void setConnectingWireSizeOb(String connectingWireSizeOb) {
		this.connectingWireSizeOb = connectingWireSizeOb;
	}

	public String getConnectingWireSizeRem() {
		return connectingWireSizeRem;
	}

	public void setConnectingWireSizeRem(String connectingWireSizeRem) {
		this.connectingWireSizeRem = connectingWireSizeRem;
	}

	public SPD getSpd() {
		return spd;
	}

	public void setSpd(SPD spd) {
		this.spd = spd;
	}	
	
	
}
