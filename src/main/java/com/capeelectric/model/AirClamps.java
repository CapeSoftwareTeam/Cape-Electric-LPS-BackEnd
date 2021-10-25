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
@Table(name = "AIR_CLAMPS_TABLE")
public class AirClamps implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLAMPS_ID")
	private Integer clampsId;
	 
	@Column(name = "PHYSICAL_INSPECTION_OBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;

	@Column(name = "CONDUCTORCLAMPS_FLATSURAFACE_OBSERVATION ")
	private String conductorClampsFlatSurafaceOb;

	@Column(name = "CONDUCTORCLAMPS_FLATSURAFACE_REMARKS")
	private String conductorClampsFlatSurafaceRe;

	@Column(name = "CLAMP_TYPE_OBSERVATION")
	private String clampTypeOb;

	@Column(name = "CLAMP_TYPE__REMARKS")
	private String clampTypRe;
	 
	@Column(name = "INTERCONNECTION_OF_CLAMPS_OBSERVATION")
	private String  interConnectionOfClampsOb;
	
	@Column(name = "INTERCONNECTION_OF_CLAMPS_REMARKS")
	private String  interConnectionOfClampsRe;
	
	@Column(name = "MATERIAL_OF_CLAMPS_OBSERVATION")
	private String  materialOfClampsOb;
	
	@Column(name = "MATERIAL_OF_CLAMPS_REMARKS")
	private String materialOfClampsRe;

	@Column(name = "TOTAL_CLAMPSNO_OBSERVATION")
	private String totalClampsNoOb;

	@Column(name = "TOTAL_CLAMPSNO_REMARKS")
	private String totalClampsNoRe;
	
	@Column(name = "INSP_NO_OBS")
	private String  inspectionNoOb;
	
	@Column(name = "INSP_NO_REM")
	private String inspectionNoRe;
 
	@Column(name = "INSP_PASSED_NO_OBS")
	private String inspectionPassedOb;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspectionPassedRe;
	
	
	@Column(name = "INSP_FAILED_NO_OBS")
	private String inspectionFailedReOb;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspectionFailedReRe;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDes;

	public Integer getClampsId() {
		return clampsId;
	}

	public void setClampsId(Integer clampsId) {
		this.clampsId = clampsId;
	}

	public String getPhysicalInspectionOb() {
		return physicalInspectionOb;
	}

	public void setPhysicalInspectionOb(String physicalInspectionOb) {
		this.physicalInspectionOb = physicalInspectionOb;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getConductorClampsFlatSurafaceOb() {
		return conductorClampsFlatSurafaceOb;
	}

	public void setConductorClampsFlatSurafaceOb(String conductorClampsFlatSurafaceOb) {
		this.conductorClampsFlatSurafaceOb = conductorClampsFlatSurafaceOb;
	}

	public String getConductorClampsFlatSurafaceRe() {
		return conductorClampsFlatSurafaceRe;
	}

	public void setConductorClampsFlatSurafaceRe(String conductorClampsFlatSurafaceRe) {
		this.conductorClampsFlatSurafaceRe = conductorClampsFlatSurafaceRe;
	}

	public String getClampTypeOb() {
		return clampTypeOb;
	}

	public void setClampTypeOb(String clampTypeOb) {
		this.clampTypeOb = clampTypeOb;
	}

	public String getClampTypRe() {
		return clampTypRe;
	}

	public void setClampTypRe(String clampTypRe) {
		this.clampTypRe = clampTypRe;
	}

	public String getInterConnectionOfClampsOb() {
		return interConnectionOfClampsOb;
	}

	public void setInterConnectionOfClampsOb(String interConnectionOfClampsOb) {
		this.interConnectionOfClampsOb = interConnectionOfClampsOb;
	}

	public String getInterConnectionOfClampsRe() {
		return interConnectionOfClampsRe;
	}

	public void setInterConnectionOfClampsRe(String interConnectionOfClampsRe) {
		this.interConnectionOfClampsRe = interConnectionOfClampsRe;
	}

	public String getMaterialOfClampsOb() {
		return materialOfClampsOb;
	}

	public void setMaterialOfClampsOb(String materialOfClampsOb) {
		this.materialOfClampsOb = materialOfClampsOb;
	}

	public String getMaterialOfClampsRe() {
		return materialOfClampsRe;
	}

	public void setMaterialOfClampsRe(String materialOfClampsRe) {
		this.materialOfClampsRe = materialOfClampsRe;
	}

	public String getTotalClampsNoOb() {
		return totalClampsNoOb;
	}

	public void setTotalClampsNoOb(String totalClampsNoOb) {
		this.totalClampsNoOb = totalClampsNoOb;
	}

	public String getTotalClampsNoRe() {
		return totalClampsNoRe;
	}

	public void setTotalClampsNoRe(String totalClampsNoRe) {
		this.totalClampsNoRe = totalClampsNoRe;
	}

	

	public String getInspectionNoOb() {
		return inspectionNoOb;
	}

	public void setInspectionNoOb(String inspectionNoOb) {
		this.inspectionNoOb = inspectionNoOb;
	}

	public String getInspectionNoRe() {
		return inspectionNoRe;
	}

	public void setInspectionNoRe(String inspectionNoRe) {
		this.inspectionNoRe = inspectionNoRe;
	}

	public String getInspectionPassedOb() {
		return inspectionPassedOb;
	}

	public void setInspectionPassedOb(String inspectionPassedOb) {
		this.inspectionPassedOb = inspectionPassedOb;
	}

	public String getInspectionPassedRe() {
		return inspectionPassedRe;
	}

	public void setInspectionPassedRe(String inspectionPassedRe) {
		this.inspectionPassedRe = inspectionPassedRe;
	}

	public String getInspectionFailedReOb() {
		return inspectionFailedReOb;
	}

	public void setInspectionFailedReOb(String inspectionFailedReOb) {
		this.inspectionFailedReOb = inspectionFailedReOb;
	}

	public String getInspectionFailedReRe() {
		return inspectionFailedReRe;
	}

	public void setInspectionFailedReRe(String inspectionFailedReRe) {
		this.inspectionFailedReRe = inspectionFailedReRe;
	}

	public LpsAirDiscription getLpsAirDes() {
		return lpsAirDes;
	}

	public void setLpsAirDes(LpsAirDiscription lpsAirDes) {
		this.lpsAirDes = lpsAirDes;
	}


}
