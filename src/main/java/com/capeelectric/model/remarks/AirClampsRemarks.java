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
@Table(name = "AIR_CLAMPS_TABLE")
public class AirClampsRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLAMPS_ID")
	private Integer clampsId;
	
	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;

	@Column(name = "CONDUCTORCLAMPS_FLATSURAFACE_REMARKS")
	private String conductorClampsFlatSurafaceRe;

	@Column(name = "CLAMP_TYPE__REMARKS")
	private String clampTypRe;

	@Column(name = "INTERCONNECTION_OF_CLAMPS_REMARKS")
	private String interConnectionOfClampsRe;

	@Column(name = "MATERIAL_OF_WALL_CLAMPS_REMARKS")
	private String materialOfWallClampsRe;

	@Column(name = "MATERIAL_OF_FOLDING_CLAMPS_REMARKS")
	private String materialOfFoldingClampsRe;

	@Column(name = "TOTAL_CLAMPSNO_REMARKS")
	private String totalClampsNoRe;

	@Column(name = "INSP_NO_REM")
	private String inspectionNoRe;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspectionPassedRe;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspectionFailedReRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscriptionRemarks lpsAirDescription;

	public Integer getClampsId() {
		return clampsId;
	}

	public void setClampsId(Integer clampsId) {
		this.clampsId = clampsId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getConductorClampsFlatSurafaceRe() {
		return conductorClampsFlatSurafaceRe;
	}

	public void setConductorClampsFlatSurafaceRe(String conductorClampsFlatSurafaceRe) {
		this.conductorClampsFlatSurafaceRe = conductorClampsFlatSurafaceRe;
	}

	public String getClampTypRe() {
		return clampTypRe;
	}

	public void setClampTypRe(String clampTypRe) {
		this.clampTypRe = clampTypRe;
	}

	public String getInterConnectionOfClampsRe() {
		return interConnectionOfClampsRe;
	}

	public void setInterConnectionOfClampsRe(String interConnectionOfClampsRe) {
		this.interConnectionOfClampsRe = interConnectionOfClampsRe;
	}

	public String getMaterialOfWallClampsRe() {
		return materialOfWallClampsRe;
	}

	public void setMaterialOfWallClampsRe(String materialOfWallClampsRe) {
		this.materialOfWallClampsRe = materialOfWallClampsRe;
	}

	public String getMaterialOfFoldingClampsRe() {
		return materialOfFoldingClampsRe;
	}

	public void setMaterialOfFoldingClampsRe(String materialOfFoldingClampsRe) {
		this.materialOfFoldingClampsRe = materialOfFoldingClampsRe;
	}

	public String getTotalClampsNoRe() {
		return totalClampsNoRe;
	}

	public void setTotalClampsNoRe(String totalClampsNoRe) {
		this.totalClampsNoRe = totalClampsNoRe;
	}

	public String getInspectionNoRe() {
		return inspectionNoRe;
	}

	public void setInspectionNoRe(String inspectionNoRe) {
		this.inspectionNoRe = inspectionNoRe;
	}

	public String getInspectionPassedRe() {
		return inspectionPassedRe;
	}

	public void setInspectionPassedRe(String inspectionPassedRe) {
		this.inspectionPassedRe = inspectionPassedRe;
	}

	public String getInspectionFailedReRe() {
		return inspectionFailedReRe;
	}

	public void setInspectionFailedReRe(String inspectionFailedReRe) {
		this.inspectionFailedReRe = inspectionFailedReRe;
	}

	public LpsAirDiscriptionRemarks getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscriptionRemarks lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
