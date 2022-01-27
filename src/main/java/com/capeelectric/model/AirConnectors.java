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
@Table(name = "AIR_CONNECTORS_TABLE")
public class AirConnectors implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONNECTORS_ID")
	private Integer connectorId;
	
	@Column(name = "FLAG")
	private Boolean flag;

	@Column(name = "PHYSICAL_INSPECTION_OBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;

	@Column(name = "CHECKCONNECTION_CONNECTORS_OBSERVATION")
	private String checkConnectionConnectorsOb;

	@Column(name = "CHECKCONNECTION_CONNECTORS_REMARKS")
	private String checkConnectionConnectorsRe;

	@Column(name = "MATERIAL_OF_CONNECTORS_OBSERVATION")
	private String materialOfConnectorOb;

	@Column(name = "MATERIAL_OF_CONNECTORS_REMARKS")
	private String materialOfConnectorRe;

	@Column(name = "STRIGHT_CONNECTORS_OBSERVATION")
	private String strightConnectorOb;

	@Column(name = "STRIGHT_CONNECTORS_REMARKS")
	private String strightConnectorRe;

	@Column(name = "T_CONNECTORS_OBSERVATION")
	private String tConnectorOb;

	@Column(name = "T_CONNECTORS_REMARKS")
	private String tConnectorRe;

	@Column(name = "L_CONNECTORS_OBSERVATION")
	private String lConnectorOb;

	@Column(name = "L_CONNECTORS_REMARKS")
	private String lConnectorRe;

	@Column(name = "TOTALNO_CONNECTORS_OBSERVATION")
	private String totalNoConnectorOb;

	@Column(name = "TOTALNO_CONNECTORS_REMARKS")
	private String totalNoConnectorRe;

	@Column(name = "INSP_NO_OBS")
	private String inspectionNoOb;

	@Column(name = "INSP_NO_REM")
	private String inspectionNoRe;

	@Column(name = "INSP_PASSED_NO_OBS")
	private String inspectionPassedNoOb;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspectionPassedNoRe;

	@Column(name = "INSP_FAILED_NO_OBS")
	private String inspectionFailedOb;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspectionFailedRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDescription;

	public Integer getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(Integer connectorId) {
		this.connectorId = connectorId;
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

	public String getCheckConnectionConnectorsOb() {
		return checkConnectionConnectorsOb;
	}

	public void setCheckConnectionConnectorsOb(String checkConnectionConnectorsOb) {
		this.checkConnectionConnectorsOb = checkConnectionConnectorsOb;
	}

	public String getCheckConnectionConnectorsRe() {
		return checkConnectionConnectorsRe;
	}

	public void setCheckConnectionConnectorsRe(String checkConnectionConnectorsRe) {
		this.checkConnectionConnectorsRe = checkConnectionConnectorsRe;
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

	public String getStrightConnectorOb() {
		return strightConnectorOb;
	}

	public void setStrightConnectorOb(String strightConnectorOb) {
		this.strightConnectorOb = strightConnectorOb;
	}

	public String getStrightConnectorRe() {
		return strightConnectorRe;
	}

	public void setStrightConnectorRe(String strightConnectorRe) {
		this.strightConnectorRe = strightConnectorRe;
	}

	public String gettConnectorOb() {
		return tConnectorOb;
	}

	public void settConnectorOb(String tConnectorOb) {
		this.tConnectorOb = tConnectorOb;
	}

	public String gettConnectorRe() {
		return tConnectorRe;
	}

	public void settConnectorRe(String tConnectorRe) {
		this.tConnectorRe = tConnectorRe;
	}

	public String getlConnectorOb() {
		return lConnectorOb;
	}

	public void setlConnectorOb(String lConnectorOb) {
		this.lConnectorOb = lConnectorOb;
	}

	public String getlConnectorRe() {
		return lConnectorRe;
	}

	public void setlConnectorRe(String lConnectorRe) {
		this.lConnectorRe = lConnectorRe;
	}

	public String getTotalNoConnectorOb() {
		return totalNoConnectorOb;
	}

	public void setTotalNoConnectorOb(String totalNoConnectorOb) {
		this.totalNoConnectorOb = totalNoConnectorOb;
	}

	public String getTotalNoConnectorRe() {
		return totalNoConnectorRe;
	}

	public void setTotalNoConnectorRe(String totalNoConnectorRe) {
		this.totalNoConnectorRe = totalNoConnectorRe;
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

	public String getInspectionFailedOb() {
		return inspectionFailedOb;
	}

	public void setInspectionFailedOb(String inspectionFailedOb) {
		this.inspectionFailedOb = inspectionFailedOb;
	}

	public String getInspectionFailedRe() {
		return inspectionFailedRe;
	}

	public void setInspectionFailedRe(String inspectionFailedRe) {
		this.inspectionFailedRe = inspectionFailedRe;
	}

	public LpsAirDiscription getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscription lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
