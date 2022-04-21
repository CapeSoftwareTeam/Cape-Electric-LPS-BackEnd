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

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTION_OBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;

	@Column(name = "STRIGHTCONNECTOR_PIECS_OBSERVATION")
	private String strightConnectorPiecOb;

	@Column(name = "STRIGHTCONNECTOR_PIECS_REMARKS")
	private String strightConnectorPiecRe;

	@Column(name = "MATERIAL_OF_CONNECTOR_OBSERVATION")
	private String materialOfConnectorOb;

	@Column(name = "MATERIAL_OF_CONNECTOR_REMARKS")
	private String materialOfConnectorRe;

	@Column(name = "MATERIAL_OF_EXPANSIONPIECE_OBSERVATION")
	private String materialOfExpansionOb;

	@Column(name = "MATERIAL_OF_EXPANSIONPIECE_REMARKS")
	private String materialOfExpansionRe;

	@Column(name = "INTERVAL_BETWEEN_EXPANSION_OBSERVATION")
	private Integer intervalBwExpansionOb;

	@Column(name = "INTERVAL_BETWEEN_EXPANSION_REMARKS")
	private String intervalBwExpansionRe;

	@Column(name = "TOTALNO_EXPANSIONPIECE_OBSERVATION")
	private Integer totalNoExpansionOb;

	@Column(name = "TOTALNO_EXPANSIONPIECE_REMARKS")
	private String totalNoExpansionRe;

	@Column(name = "INSP_NO_OBS")
	private Integer inspectionNoOb;

	@Column(name = "INSP_NO_REM")
	private String inspectionNoRe;

	@Column(name = "INSP_PASSED_NO_OBS")
	private Integer inspectionPassedNoOb;

	@Column(name = "INSP_PASSED_NO_REM ")
	private String inspectionPassedNoRe;

	@Column(name = "INSP_FAILED_NO_OBS")
	private Integer inspectionFailedNoOb;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspectionFailedNoRe;

	@Column(name = "FILE_NAME_EP")
	private String fileName_EP;

	@Column(name = "FILE_TYPE_EP")
	private String fileType_EP;

	@Column(name = "FILE_ID_EP")
	private Integer fileId_EP;
	
	@Column(name = "FILE_SIZE")
	private String fileSize;
	
	@Column(name = "FILE_INDEX_EP")
	private Integer fileIndex_EP;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDescription;

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

	public String getTotalNoExpansionRe() {
		return totalNoExpansionRe;
	}

	public void setTotalNoExpansionRe(String totalNoExpansionRe) {
		this.totalNoExpansionRe = totalNoExpansionRe;
	}

	public String getInspectionNoRe() {
		return inspectionNoRe;
	}

	public void setInspectionNoRe(String inspectionNoRe) {
		this.inspectionNoRe = inspectionNoRe;
	}

	public String getInspectionPassedNoRe() {
		return inspectionPassedNoRe;
	}

	public void setInspectionPassedNoRe(String inspectionPassedNoRe) {
		this.inspectionPassedNoRe = inspectionPassedNoRe;
	}

	public String getInspectionFailedNoRe() {
		return inspectionFailedNoRe;
	}

	public void setInspectionFailedNoRe(String inspectionFailedNoRe) {
		this.inspectionFailedNoRe = inspectionFailedNoRe;
	}

	public LpsAirDiscription getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscription lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMaterialOfConnectorOb() {
		return materialOfConnectorOb;
	}

	public void setMaterialOfConnectorOb(String materialOfConnectorOb) {
		this.materialOfConnectorOb = materialOfConnectorOb;
	}

	public String getMaterialOfConnectorRe() {
		return materialOfConnectorRe;
	}

	public void setMaterialOfConnectorRe(String materialOfConnectorRe) {
		this.materialOfConnectorRe = materialOfConnectorRe;
	}

	public Integer getIntervalBwExpansionOb() {
		return intervalBwExpansionOb;
	}

	public void setIntervalBwExpansionOb(Integer intervalBwExpansionOb) {
		this.intervalBwExpansionOb = intervalBwExpansionOb;
	}

	public String getIntervalBwExpansionRe() {
		return intervalBwExpansionRe;
	}

	public void setIntervalBwExpansionRe(String intervalBwExpansionRe) {
		this.intervalBwExpansionRe = intervalBwExpansionRe;
	}

	public Integer getTotalNoExpansionOb() {
		return totalNoExpansionOb;
	}

	public void setTotalNoExpansionOb(Integer totalNoExpansionOb) {
		this.totalNoExpansionOb = totalNoExpansionOb;
	}

	public Integer getInspectionNoOb() {
		return inspectionNoOb;
	}

	public void setInspectionNoOb(Integer inspectionNoOb) {
		this.inspectionNoOb = inspectionNoOb;
	}

	public Integer getInspectionPassedNoOb() {
		return inspectionPassedNoOb;
	}

	public void setInspectionPassedNoOb(Integer inspectionPassedNoOb) {
		this.inspectionPassedNoOb = inspectionPassedNoOb;
	}

	public Integer getInspectionFailedNoOb() {
		return inspectionFailedNoOb;
	}

	public void setInspectionFailedNoOb(Integer inspectionFailedNoOb) {
		this.inspectionFailedNoOb = inspectionFailedNoOb;
	}

	public String getFileName_EP() {
		return fileName_EP;
	}

	public void setFileName_EP(String fileName_EP) {
		this.fileName_EP = fileName_EP;
	}

	public String getFileType_EP() {
		return fileType_EP;
	}

	public void setFileType_EP(String fileType_EP) {
		this.fileType_EP = fileType_EP;
	}

	public Integer getFileId_EP() {
		return fileId_EP;
	}

	public void setFileId_EP(Integer fileId_EP) {
		this.fileId_EP = fileId_EP;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getFileIndex_EP() {
		return fileIndex_EP;
	}

	public void setFileIndex_EP(Integer fileIndex_EP) {
		this.fileIndex_EP = fileIndex_EP;
	}

}
