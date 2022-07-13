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
@Table(name = "AIR_BASIC_DESCRIPTION_TABLE")
public class AirBasicDescRemarks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AIR_BASIC_DESCRIPTION_ID")
	private Integer airBasicDescriptionId;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "CONSULTANT_NAME_REMARKS")
	private String consultantNameRemarks;
	
	@Column(name = "ARCHITECT_NAMEINREMARKS")
	private String architectNameRemarks;
	
	@Column(name = "DATE_OF_DESIGN_REMARKS")
	private String designDateRemarks;
	
	@Column(name = "DATEOF_APPROVAL_REM")
	private String dateOfApprovalRem;

	@Column(name = "REMARKS_APPROVED_BY")
	private String approvedByRemarks;

	@Column(name = "REMARKS_DRAWING_NUMBER")
	private String drawingRemarks;
	
	@Column(name = "REMARKS_REVISION_NUMBER")
	private String revisionNoRemarks;

	@Column(name = "REMARKS_DEVIATION_CHECKING")
	private String deviationRemarks;
	
	@Column(name = "DEVIATION_INSTALLATION_REMARKS")
	private String deviationInstallationRemarks;

	@Column(name = "COMPANY_NAME_REMARKS")
	private String companyNameRemarks;

	@Column(name = "CONNECTION_MADE_BRAZINGREMARKS")
	private String connectionMadeBraRe;

	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDREMARKS")
	private String electricalEquipPlacedRe;

	@Column(name = "COMBUSTABLE_PARTREMARKS")
	private String combustablePartRe;

	@Column(name = "TERMINATION_MESH_CONDUCTORREMARKS")
	private String terminationMeshConductorRe;
	
	@Column(name = "BONDING_EQUIPOTENTIALREMARKS")
	private String bondingEquipotentialRe;

		
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscriptionRemarks lpsAirDescription;


	public Integer getAirBasicDescriptionId() {
		return airBasicDescriptionId;
	}


	public void setAirBasicDescriptionId(Integer airBasicDescriptionId) {
		this.airBasicDescriptionId = airBasicDescriptionId;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getConsultantNameRemarks() {
		return consultantNameRemarks;
	}


	public void setConsultantNameRemarks(String consultantNameRemarks) {
		this.consultantNameRemarks = consultantNameRemarks;
	}


	public String getArchitectNameRemarks() {
		return architectNameRemarks;
	}


	public void setArchitectNameRemarks(String architectNameRemarks) {
		this.architectNameRemarks = architectNameRemarks;
	}


	public String getDesignDateRemarks() {
		return designDateRemarks;
	}


	public void setDesignDateRemarks(String designDateRemarks) {
		this.designDateRemarks = designDateRemarks;
	}


	public String getDateOfApprovalRem() {
		return dateOfApprovalRem;
	}


	public void setDateOfApprovalRem(String dateOfApprovalRem) {
		this.dateOfApprovalRem = dateOfApprovalRem;
	}


	public String getApprovedByRemarks() {
		return approvedByRemarks;
	}


	public void setApprovedByRemarks(String approvedByRemarks) {
		this.approvedByRemarks = approvedByRemarks;
	}


	public String getDrawingRemarks() {
		return drawingRemarks;
	}


	public void setDrawingRemarks(String drawingRemarks) {
		this.drawingRemarks = drawingRemarks;
	}


	public String getRevisionNoRemarks() {
		return revisionNoRemarks;
	}


	public void setRevisionNoRemarks(String revisionNoRemarks) {
		this.revisionNoRemarks = revisionNoRemarks;
	}


	public String getDeviationRemarks() {
		return deviationRemarks;
	}


	public void setDeviationRemarks(String deviationRemarks) {
		this.deviationRemarks = deviationRemarks;
	}


	public String getDeviationInstallationRemarks() {
		return deviationInstallationRemarks;
	}


	public void setDeviationInstallationRemarks(String deviationInstallationRemarks) {
		this.deviationInstallationRemarks = deviationInstallationRemarks;
	}


	public String getCompanyNameRemarks() {
		return companyNameRemarks;
	}


	public void setCompanyNameRemarks(String companyNameRemarks) {
		this.companyNameRemarks = companyNameRemarks;
	}


	public String getConnectionMadeBraRe() {
		return connectionMadeBraRe;
	}


	public void setConnectionMadeBraRe(String connectionMadeBraRe) {
		this.connectionMadeBraRe = connectionMadeBraRe;
	}


	public String getElectricalEquipPlacedRe() {
		return electricalEquipPlacedRe;
	}


	public void setElectricalEquipPlacedRe(String electricalEquipPlacedRe) {
		this.electricalEquipPlacedRe = electricalEquipPlacedRe;
	}


	public String getCombustablePartRe() {
		return combustablePartRe;
	}


	public void setCombustablePartRe(String combustablePartRe) {
		this.combustablePartRe = combustablePartRe;
	}


	public String getTerminationMeshConductorRe() {
		return terminationMeshConductorRe;
	}


	public void setTerminationMeshConductorRe(String terminationMeshConductorRe) {
		this.terminationMeshConductorRe = terminationMeshConductorRe;
	}


	public String getBondingEquipotentialRe() {
		return bondingEquipotentialRe;
	}


	public void setBondingEquipotentialRe(String bondingEquipotentialRe) {
		this.bondingEquipotentialRe = bondingEquipotentialRe;
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
