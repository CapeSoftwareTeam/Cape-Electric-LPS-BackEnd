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
@Table(name = "CONNECTORS_TABLE")
public class ConnectorRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONNECTORS_ID")
	private Integer connectorId;

	@Column(name = "FLAG")
	private String flag;


	@Column(name = "PHYSICAL_INSPECTION_REM")
	private String physicalInspectionRem;

	@Column(name = "STRAIGHT_CONNECTORCHACK_REM")
	private String strightConnectCheckRem;

	@Column(name = "MATERIAL_CONNECTOR_REM ")
	private String materialConnectorRem;

	@Column(name = "MAXCONNECTORS_DOWNCONDUCTORS_REM")
	private String maxConnectorsDownConductorRem;

	@Column(name = "TOTALNO_CONNECTORS_REM")
	private String totalNoConnectorsRem;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorsDescriptionRemarks downConductorDescription;

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

	public String getPhysicalInspectionRem() {
		return physicalInspectionRem;
	}

	public void setPhysicalInspectionRem(String physicalInspectionRem) {
		this.physicalInspectionRem = physicalInspectionRem;
	}

	public String getStrightConnectCheckRem() {
		return strightConnectCheckRem;
	}

	public void setStrightConnectCheckRem(String strightConnectCheckRem) {
		this.strightConnectCheckRem = strightConnectCheckRem;
	}

	public String getMaterialConnectorRem() {
		return materialConnectorRem;
	}

	public void setMaterialConnectorRem(String materialConnectorRem) {
		this.materialConnectorRem = materialConnectorRem;
	}

	public String getMaxConnectorsDownConductorRem() {
		return maxConnectorsDownConductorRem;
	}

	public void setMaxConnectorsDownConductorRem(String maxConnectorsDownConductorRem) {
		this.maxConnectorsDownConductorRem = maxConnectorsDownConductorRem;
	}

	public String getTotalNoConnectorsRem() {
		return totalNoConnectorsRem;
	}

	public void setTotalNoConnectorsRem(String totalNoConnectorsRem) {
		this.totalNoConnectorsRem = totalNoConnectorsRem;
	}

	public String getInspectedNoRem() {
		return inspectedNoRem;
	}

	public void setInspectedNoRem(String inspectedNoRem) {
		this.inspectedNoRem = inspectedNoRem;
	}

	public String getInspectionPassedNoRem() {
		return inspectionPassedNoRem;
	}

	public void setInspectionPassedNoRem(String inspectionPassedNoRem) {
		this.inspectionPassedNoRem = inspectionPassedNoRem;
	}

	public String getInspectionFailedNoRem() {
		return inspectionFailedNoRem;
	}

	public void setInspectionFailedNoRem(String inspectionFailedNoRem) {
		this.inspectionFailedNoRem = inspectionFailedNoRem;
	}

	public DownConductorsDescriptionRemarks getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(DownConductorsDescriptionRemarks downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
