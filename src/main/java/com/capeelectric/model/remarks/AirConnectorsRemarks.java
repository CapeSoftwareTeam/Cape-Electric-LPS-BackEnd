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
@Table(name = "AIR_CONNECTORS_TABLE")
public class AirConnectorsRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONNECTORS_ID")
	private Integer connectorId;
	
	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;


	@Column(name = "CHECKCONNECTION_CONNECTORS_REMARKS")
	private String checkConnectionConnectorsRe;

	@Column(name = "MATERIAL_OF_CONNECTORS_REMARKS")
	private String materialOfConnectorRe;


	@Column(name = "STRIGHT_CONNECTORS_REMARKS")
	private String strightConnectorRe;

	@Column(name = "T_CONNECTORS_REMARKS")
	private String tConnectorRe;

	@Column(name = "L_CONNECTORS_REMARKS")
	private String lConnectorRe;

	@Column(name = "TOTALNO_CONNECTORS_REMARKS")
	private String totalNoConnectorRe;

	@Column(name = "INSP_NO_REM")
	private String inspectionNoRe;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspectionPassedNoRe;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspectionFailedRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscriptionRemarks lpsAirDescription;

	public Integer getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(Integer connectorId) {
		this.connectorId = connectorId;
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

	public String getCheckConnectionConnectorsRe() {
		return checkConnectionConnectorsRe;
	}

	public void setCheckConnectionConnectorsRe(String checkConnectionConnectorsRe) {
		this.checkConnectionConnectorsRe = checkConnectionConnectorsRe;
	}

	public String getMaterialOfConnectorRe() {
		return materialOfConnectorRe;
	}

	public void setMaterialOfConnectorRe(String materialOfConnectorRe) {
		this.materialOfConnectorRe = materialOfConnectorRe;
	}

	public String getStrightConnectorRe() {
		return strightConnectorRe;
	}

	public void setStrightConnectorRe(String strightConnectorRe) {
		this.strightConnectorRe = strightConnectorRe;
	}

	public String gettConnectorRe() {
		return tConnectorRe;
	}

	public void settConnectorRe(String tConnectorRe) {
		this.tConnectorRe = tConnectorRe;
	}

	public String getlConnectorRe() {
		return lConnectorRe;
	}

	public void setlConnectorRe(String lConnectorRe) {
		this.lConnectorRe = lConnectorRe;
	}

	public String getTotalNoConnectorRe() {
		return totalNoConnectorRe;
	}

	public void setTotalNoConnectorRe(String totalNoConnectorRe) {
		this.totalNoConnectorRe = totalNoConnectorRe;
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

	public String getInspectionFailedRe() {
		return inspectionFailedRe;
	}

	public void setInspectionFailedRe(String inspectionFailedRe) {
		this.inspectionFailedRe = inspectionFailedRe;
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
