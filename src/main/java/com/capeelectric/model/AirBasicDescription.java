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

/**
 * @author CAPE-SOFTWARE
 *
 */

@Entity
@Table(name = "AIR_BASIC_DESCRIPTION_TABLE")
public class AirBasicDescription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AIR_BASIC_DESCRIPTION_ID")
	private Integer airBasicDescriptionId;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "CONSULTANT_NAME_OBSERVATION")
	private String consultantNameObserv;
	
	@Column(name = "CONSULTANT_NAME_REMARKS")
	private String consultantNameRemarks;
	
	@Column(name = "ARCHITECT_NAMEINOBSERVATION")
	private String architectNameObserv;
	
	@Column(name = "ARCHITECT_NAMEINREMARKS")
	private String architectNameRemarks;
	
	@Column(name = "DATE_OF_DESIGN_OBSERVATION")
	private String designDateObserv;
	
	@Column(name = "DATE_OF_DESIGN_REMARKS")
	private String designDateRemarks;
	
	@Column(name = "DATEOF_APPROVAL_OB")
	private String dateOfApprovalOb;
	
	@Column(name = "DATEOF_APPROVAL_REM")
	private String dateOfApprovalRem;

	@Column(name = "OBSERVATION_APPROVED_BY")
	private String approvedByObserv;
	
	@Column(name = "REMARKS_APPROVED_BY")
	private String approvedByRemarks;
	
	@Column(name = "OBSERVATION_DRAWING_NUMBER")
	private String drawingObserv;
	
	@Column(name = "REMARKS_DRAWING_NUMBER")
	private String drawingRemarks;
	
	@Column(name = "OBSERVATION_REVISION_NUMBER")
	private String revisionNoObserv;
	
	@Column(name = "REMARKS_REVISION_NUMBER")
	private String revisionNoRemarks;
	
	@Column(name = "OBSERVATION_DEVIATION_CHECKING")
	private String deviationObserv;
	
	@Column(name = "REMARKS_DEVIATION_CHECKING")
	private String deviationRemarks;
	
	@Column(name = "DEVIATION_INSTALLATION_OBSERVATION")
	private String deviationInstallationObserv;
	
	@Column(name = "DEVIATION_INSTALLATION_REMARKS")
	private String deviationInstallationRemarks;
	
	@Column(name = "COMPANY_NAME_OBSERVATION")
	private String companyNameObserv;
	
	@Column(name = "COMPANY_NAME_REMARKS")
	private String companyNameRemarks;
	
	@Column(name = "CONNECTION_MADE_BRAZINGOBSERVATION")
	private String connectionMadeBraOb;
	
	@Column(name = "CONNECTION_MADE_BRAZINGREMARKS")
	private String connectionMadeBraRe;

	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDOBSERVATION")
	private String electricalEquipPlacedOb;

	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDREMARKS")
	private String electricalEquipPlacedRe;
	
	@Column(name = "COMBUSTABLE_PARTOBSERVATION")
	private String combustablePartOb;

	@Column(name = "COMBUSTABLE_PARTREMARKS")
	private String combustablePartRe;
	
	@Column(name = "TERMINATION_MESH_CONDUCTOROBSERVATION")
	private String terminationMeshConductorOb;


	@Column(name = "TERMINATION_MESH_CONDUCTORREMARKS")
	private String terminationMeshConductorRe;
	
	@Column(name = "BONDING_EQUIPOTENTIALOBSERVATION")
	private String bondingEquipotentialOb;


	@Column(name = "BONDING_EQUIPOTENTIALREMARKS")
	private String bondingEquipotentialRe;

		
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDescription;


	public Integer getAirBasicDescriptionId() {
		return airBasicDescriptionId;
	}

	public void setAirBasicDescriptionId(Integer airBasicDescriptionId) {
		this.airBasicDescriptionId = airBasicDescriptionId;
	}


	public LpsAirDiscription getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscription lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}


	public String getArchitectNameObserv() {
		return architectNameObserv;
	}

	public void setArchitectNameObserv(String architectNameObserv) {
		this.architectNameObserv = architectNameObserv;
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

	public String getApprovedByObserv() {
		return approvedByObserv;
	}

	public void setApprovedByObserv(String approvedByObserv) {
		this.approvedByObserv = approvedByObserv;
	}

	public String getApprovedByRemarks() {
		return approvedByRemarks;
	}

	public void setApprovedByRemarks(String approvedByRemarks) {
		this.approvedByRemarks = approvedByRemarks;
	}

	public String getDrawingObserv() {
		return drawingObserv;
	}

	public void setDrawingObserv(String drawingObserv) {
		this.drawingObserv = drawingObserv;
	}

	public String getDrawingRemarks() {
		return drawingRemarks;
	}

	public void setDrawingRemarks(String drawingRemarks) {
		this.drawingRemarks = drawingRemarks;
	}

	public String getRevisionNoObserv() {
		return revisionNoObserv;
	}

	public String getDateOfApprovalRem() {
		return dateOfApprovalRem;
	}

	public void setDateOfApprovalRem(String dateOfApprovalRem) {
		this.dateOfApprovalRem = dateOfApprovalRem;
	}

	public void setRevisionNoObserv(String revisionNoObserv) {
		this.revisionNoObserv = revisionNoObserv;
	}

	public String getRevisionNoRemarks() {
		return revisionNoRemarks;
	}

	public void setRevisionNoRemarks(String revisionNoRemarks) {
		this.revisionNoRemarks = revisionNoRemarks;
	}

	public String getDeviationObserv() {
		return deviationObserv;
	}

	public void setDeviationObserv(String deviationObserv) {
		this.deviationObserv = deviationObserv;
	}

	public String getDeviationRemarks() {
		return deviationRemarks;
	}

	public void setDeviationRemarks(String deviationRemarks) {
		this.deviationRemarks = deviationRemarks;
	}


	public String getConnectionMadeBraOb() {
		return connectionMadeBraOb;
	}

	public void setConnectionMadeBraOb(String connectionMadeBraOb) {
		this.connectionMadeBraOb = connectionMadeBraOb;
	}

	public String getConnectionMadeBraRe() {
		return connectionMadeBraRe;
	}

	public void setConnectionMadeBraRe(String connectionMadeBraRe) {
		this.connectionMadeBraRe = connectionMadeBraRe;
	}

	public String getElectricalEquipPlacedOb() {
		return electricalEquipPlacedOb;
	}

	public void setElectricalEquipPlacedOb(String electricalEquipPlacedOb) {
		this.electricalEquipPlacedOb = electricalEquipPlacedOb;
	}

	public String getElectricalEquipPlacedRe() {
		return electricalEquipPlacedRe;
	}

	public void setElectricalEquipPlacedRe(String electricalEquipPlacedRe) {
		this.electricalEquipPlacedRe = electricalEquipPlacedRe;
	}

	public String getCombustablePartOb() {
		return combustablePartOb;
	}

	public void setCombustablePartOb(String combustablePartOb) {
		this.combustablePartOb = combustablePartOb;
	}

	public String getCombustablePartRe() {
		return combustablePartRe;
	}

	public void setCombustablePartRe(String combustablePartRe) {
		this.combustablePartRe = combustablePartRe;
	}

	public String getTerminationMeshConductorOb() {
		return terminationMeshConductorOb;
	}

	public void setTerminationMeshConductorOb(String terminationMeshConductorOb) {
		this.terminationMeshConductorOb = terminationMeshConductorOb;
	}

	public String getTerminationMeshConductorRe() {
		return terminationMeshConductorRe;
	}

	public void setTerminationMeshConductorRe(String terminationMeshConductorRe) {
		this.terminationMeshConductorRe = terminationMeshConductorRe;
	}

	public String getBondingEquipotentialOb() {
		return bondingEquipotentialOb;
	}

	public void setBondingEquipotentialOb(String bondingEquipotentialOb) {
		this.bondingEquipotentialOb = bondingEquipotentialOb;
	}

	public String getBondingEquipotentialRe() {
		return bondingEquipotentialRe;
	}

	public void setBondingEquipotentialRe(String bondingEquipotentialRe) {
		this.bondingEquipotentialRe = bondingEquipotentialRe;
	}

	public String getConsultantNameObserv() {
		return consultantNameObserv;
	}

	public void setConsultantNameObserv(String consultantNameObserv) {
		this.consultantNameObserv = consultantNameObserv;
	}

	public String getConsultantNameRemarks() {
		return consultantNameRemarks;
	}

	public void setConsultantNameRemarks(String consultantNameRemarks) {
		this.consultantNameRemarks = consultantNameRemarks;
	}


	public String getDesignDateObserv() {
		return designDateObserv;
	}

	public void setDesignDateObserv(String designDateObserv) {
		this.designDateObserv = designDateObserv;
	}

	public String getDateOfApprovalOb() {
		return dateOfApprovalOb;
	}

	public void setDateOfApprovalOb(String dateOfApprovalOb) {
		this.dateOfApprovalOb = dateOfApprovalOb;
	}

	public String getDeviationInstallationObserv() {
		return deviationInstallationObserv;
	}

	public void setDeviationInstallationObserv(String deviationInstallationObserv) {
		this.deviationInstallationObserv = deviationInstallationObserv;
	}

	public String getDeviationInstallationRemarks() {
		return deviationInstallationRemarks;
	}

	public void setDeviationInstallationRemarks(String deviationInstallationRemarks) {
		this.deviationInstallationRemarks = deviationInstallationRemarks;
	}

	public String getCompanyNameObserv() {
		return companyNameObserv;
	}

	public void setCompanyNameObserv(String companyNameObserv) {
		this.companyNameObserv = companyNameObserv;
	}

	public String getCompanyNameRemarks() {
		return companyNameRemarks;
	}

	public void setCompanyNameRemarks(String companyNameRemarks) {
		this.companyNameRemarks = companyNameRemarks;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

}
