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
@Table(name = "AIR_EXPANSION_TABLE")
public class AirExpansion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EXPANSION_ID")
	private Integer expansionId;	
	
	@Column(name = "PHYSICAL_INSPECTION_OBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;
	
	@Column(name = "STRIGHTCONNECTOR_PIECS_OBSERVATION")
	private String strightConnectorPiecOb;

	@Column(name = "STRIGHTCONNECTOR_PIECS_REMARKS")
	private String strightConnectorPiecRe;
	 
	@Column(name = "MATERIAL_OF_EXPANSIONPIECE_OBSERVATION")
	private String materialOfExpansionOb;

	@Column(name = "MATERIAL_OF_EXPANSIONPIECE_REMARKS")
	private String materialOfExpansionRe;
	 
	@Column(name = "TOTALNO_EXPANSIONPIECE_OBSERVATION")
	private String totalNoExpansionOb;

	@Column(name = "TOTALNO_EXPANSIONPIECE_REMARKS")
	private String totalNoExpansionRe;
	 
	@Column(name = "INSP_NO_OBS")
	private String inspectionNoOb;

	@Column(name = "INSP_NO_REM")
	private String inspectionNoRe;
	
	@Column(name = "INSP_PASSED_NO_OBS")
	private String inspectionPassedNoOb;

	@Column(name = "INSP_PASSED_NO_REM ")
	private String inspectionPassedNoRe;
	
	@Column(name = "INSP_FAILED_NO_OBS")
	private String inspectionFailedNoOb;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspectionFailedNoRe;
	
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDes;


	public Integer getExpansionId() {
		return expansionId;
	}


	public void setExpansionId(Integer expansionId) {
		this.expansionId = expansionId;
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


	public String getStrightConnectorPiecOb() {
		return strightConnectorPiecOb;
	}


	public void setStrightConnectorPiecOb(String strightConnectorPiecOb) {
		this.strightConnectorPiecOb = strightConnectorPiecOb;
	}


	public String getStrightConnectorPiecRe() {
		return strightConnectorPiecRe;
	}


	public void setStrightConnectorPiecRe(String strightConnectorPiecRe) {
		this.strightConnectorPiecRe = strightConnectorPiecRe;
	}


	public String getMaterialOfExpansionOb() {
		return materialOfExpansionOb;
	}


	public void setMaterialOfExpansionOb(String materialOfExpansionOb) {
		this.materialOfExpansionOb = materialOfExpansionOb;
	}


	public String getMaterialOfExpansionRe() {
		return materialOfExpansionRe;
	}


	public void setMaterialOfExpansionRe(String materialOfExpansionRe) {
		this.materialOfExpansionRe = materialOfExpansionRe;
	}


	public String getTotalNoExpansionOb() {
		return totalNoExpansionOb;
	}


	public void setTotalNoExpansionOb(String totalNoExpansionOb) {
		this.totalNoExpansionOb = totalNoExpansionOb;
	}


	public String getTotalNoExpansionRe() {
		return totalNoExpansionRe;
	}


	public void setTotalNoExpansionRe(String totalNoExpansionRe) {
		this.totalNoExpansionRe = totalNoExpansionRe;
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


	public String getInspectionPassedNoOb() {
		return inspectionPassedNoOb;
	}


	public void setInspectionPassedNoOb(String inspectionPassedNoOb) {
		this.inspectionPassedNoOb = inspectionPassedNoOb;
	}


	public String getInspectionPassedNoRe() {
		return inspectionPassedNoRe;
	}


	public void setInspectionPassedNoRe(String inspectionPassedNoRe) {
		this.inspectionPassedNoRe = inspectionPassedNoRe;
	}


	public String getInspectionFailedNoOb() {
		return inspectionFailedNoOb;
	}


	public void setInspectionFailedNoOb(String inspectionFailedNoOb) {
		this.inspectionFailedNoOb = inspectionFailedNoOb;
	}


	public String getInspectionFailedNoRe() {
		return inspectionFailedNoRe;
	}


	public void setInspectionFailedNoRe(String inspectionFailedNoRe) {
		this.inspectionFailedNoRe = inspectionFailedNoRe;
	}


	public LpsAirDiscription getLpsAirDes() {
		return lpsAirDes;
	}


	public void setLpsAirDes(LpsAirDiscription lpsAirDes) {
		this.lpsAirDes = lpsAirDes;
	}


}
