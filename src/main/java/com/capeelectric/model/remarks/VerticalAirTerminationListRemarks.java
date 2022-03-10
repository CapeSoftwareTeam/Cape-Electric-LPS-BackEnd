/**
 * 
 */
package com.capeelectric.model.remarks;

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
public class VerticalAirTerminationListRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VERTICALAIRTERMINALLIST_ID")
	private Integer verticalAirTerminationListId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "MATERIAL_OF_TERMINALREMARKS")
	private String materialOfTerminalRe;

	@Column(name = "SIZE_OF_TERMINALREMARKS")
	private String sizeOfTerminalRe;

	@Column(name = "HEIGHT_OF_TERMINALREMARKS")
	private String heightOfTerminalRe;

	@Column(name = "ANGLEPROTECTION_HEIGHTREMARKS")
	private String angleProtectionHeightRe;

	@Column(name = "INSTALLATION_TERMINATIONSYSTEM_REM")
	private String installationTerminationsystemRem;

	@Column(name = "SUPPORT_FLATSURFACEREMARKS")
	private String supportFlatSurfaceRe;

	@Column(name = "HEIGHT_FLATSURAFACEREMARKS")
	private String heightFlatSurfaceRe;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "LPSVERTICALAIRTERMINAL_ID")
	private LpsVerticalAirTerminationRemarks lpsVerticalAirTermination;

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

	public String getMaterialOfTerminalRe() {
		return materialOfTerminalRe;
	}

	public void setMaterialOfTerminalRe(String materialOfTerminalRe) {
		this.materialOfTerminalRe = materialOfTerminalRe;
	}

	public String getSizeOfTerminalRe() {
		return sizeOfTerminalRe;
	}

	public void setSizeOfTerminalRe(String sizeOfTerminalRe) {
		this.sizeOfTerminalRe = sizeOfTerminalRe;
	}

	public String getHeightOfTerminalRe() {
		return heightOfTerminalRe;
	}

	public void setHeightOfTerminalRe(String heightOfTerminalRe) {
		this.heightOfTerminalRe = heightOfTerminalRe;
	}

	public String getAngleProtectionHeightRe() {
		return angleProtectionHeightRe;
	}

	public void setAngleProtectionHeightRe(String angleProtectionHeightRe) {
		this.angleProtectionHeightRe = angleProtectionHeightRe;
	}

	public String getInstallationTerminationsystemRem() {
		return installationTerminationsystemRem;
	}

	public void setInstallationTerminationsystemRem(String installationTerminationsystemRem) {
		this.installationTerminationsystemRem = installationTerminationsystemRem;
	}

	public String getSupportFlatSurfaceRe() {
		return supportFlatSurfaceRe;
	}

	public void setSupportFlatSurfaceRe(String supportFlatSurfaceRe) {
		this.supportFlatSurfaceRe = supportFlatSurfaceRe;
	}

	public String getHeightFlatSurfaceRe() {
		return heightFlatSurfaceRe;
	}

	public void setHeightFlatSurfaceRe(String heightFlatSurfaceRe) {
		this.heightFlatSurfaceRe = heightFlatSurfaceRe;
	}

	public LpsVerticalAirTerminationRemarks getLpsVerticalAirTermination() {
		return lpsVerticalAirTermination;
	}

	public void setLpsVerticalAirTermination(LpsVerticalAirTerminationRemarks lpsVerticalAirTermination) {
		this.lpsVerticalAirTermination = lpsVerticalAirTermination;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
