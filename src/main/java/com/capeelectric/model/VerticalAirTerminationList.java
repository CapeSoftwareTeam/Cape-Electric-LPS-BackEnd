/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;

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
@Table(name = "VERTICAL_AIR_TERMINAL_LIST_TABLE")
public class VerticalAirTerminationList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VERTICALAIRTERMINALLIST_ID")
	private Integer verticalAirTerminationListId;

	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "HEIGHT_OF_TERMINALOBSERVATION")
	private Integer heightOfTerminalOb;

	@Column(name = "HEIGHT_OF_TERMINALREMARKS")
	private String heightOfTerminalRe;

	@Column(name = "ANGLEPROTECTION_HEIGHTOBSERVATION")
	private String angleProtectionHeightOb;

	@Column(name = "ANGLEPROTECTION_HEIGHTREMARKS")
	private String angleProtectionHeightRe;
	
	@Column(name = "INSTALLATION_TERMINATIONSYSTEM_OB")
	private String installationTerminationsystemOb;

	@Column(name = "INSTALLATION_TERMINATIONSYSTEM_REM")
	private String installationTerminationsystemRem;
	
	@Column(name = "SUPPORT_FLATSURFACEOBSERVATION")
	private String supportFlatSurfaceOb;

	@Column(name = "SUPPORT_FLATSURFACEREMARKS")
	private String supportFlatSurfaceRe;

	@Column(name = "HEIGHT_FLATSURAFACEOBSERVATION")
	private String heightFlatSurfaceOb;

	@Column(name = "HEIGHT_FLATSURAFACEREMARKS")
	private String heightFlatSurfaceRe;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "LPSVERTICALAIRTERMINAL_ID")
	private LpsVerticalAirTermination lpsVerticalAirTermination;

	public Integer getVerticalAirTerminationListId() {
		return verticalAirTerminationListId;
	}

	public void setVerticalAirTerminationListId(Integer verticalAirTerminationListId) {
		this.verticalAirTerminationListId = verticalAirTerminationListId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getHeightOfTerminalOb() {
		return heightOfTerminalOb;
	}

	public void setHeightOfTerminalOb(Integer heightOfTerminalOb) {
		this.heightOfTerminalOb = heightOfTerminalOb;
	}

	public String getHeightOfTerminalRe() {
		return heightOfTerminalRe;
	}

	public void setHeightOfTerminalRe(String heightOfTerminalRe) {
		this.heightOfTerminalRe = heightOfTerminalRe;
	}

	public String getAngleProtectionHeightOb() {
		return angleProtectionHeightOb;
	}

	public void setAngleProtectionHeightOb(String angleProtectionHeightOb) {
		this.angleProtectionHeightOb = angleProtectionHeightOb;
	}

	public String getAngleProtectionHeightRe() {
		return angleProtectionHeightRe;
	}

	public void setAngleProtectionHeightRe(String angleProtectionHeightRe) {
		this.angleProtectionHeightRe = angleProtectionHeightRe;
	}

	public String getInstallationTerminationsystemOb() {
		return installationTerminationsystemOb;
	}

	public void setInstallationTerminationsystemOb(String installationTerminationsystemOb) {
		this.installationTerminationsystemOb = installationTerminationsystemOb;
	}

	public String getInstallationTerminationsystemRem() {
		return installationTerminationsystemRem;
	}

	public void setInstallationTerminationsystemRem(String installationTerminationsystemRem) {
		this.installationTerminationsystemRem = installationTerminationsystemRem;
	}

	public String getSupportFlatSurfaceOb() {
		return supportFlatSurfaceOb;
	}

	public void setSupportFlatSurfaceOb(String supportFlatSurfaceOb) {
		this.supportFlatSurfaceOb = supportFlatSurfaceOb;
	}

	public String getSupportFlatSurfaceRe() {
		return supportFlatSurfaceRe;
	}

	public void setSupportFlatSurfaceRe(String supportFlatSurfaceRe) {
		this.supportFlatSurfaceRe = supportFlatSurfaceRe;
	}

	public String getHeightFlatSurfaceOb() {
		return heightFlatSurfaceOb;
	}

	public void setHeightFlatSurfaceOb(String heightFlatSurfaceOb) {
		this.heightFlatSurfaceOb = heightFlatSurfaceOb;
	}

	public String getHeightFlatSurfaceRe() {
		return heightFlatSurfaceRe;
	}

	public void setHeightFlatSurfaceRe(String heightFlatSurfaceRe) {
		this.heightFlatSurfaceRe = heightFlatSurfaceRe;
	}

	public LpsVerticalAirTermination getLpsVerticalAirTermination() {
		return lpsVerticalAirTermination;
	}

	public void setLpsVerticalAirTermination(LpsVerticalAirTermination lpsVerticalAirTermination) {
		this.lpsVerticalAirTermination = lpsVerticalAirTermination;
	}
	
}
