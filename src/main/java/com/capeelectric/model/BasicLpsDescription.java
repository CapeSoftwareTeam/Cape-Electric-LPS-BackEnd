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
@Table(name = "BASIC_LPS_DESCRIPTION_TABLE")
public class BasicLpsDescription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BASIC_LPS_DESCRIPTION_ID")
	private Integer basicLpsDescriptionId;
	
	@Column(name = "APPROVED_COPYOF_DRAWING_OBSERVATION")
	private String approvedDrawingObserv;
	
	@Column(name = "APPROVED_COPYOF_DRAWING_REMARKS")
	private String approvedDrawingRemarks;
	
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
	
	@Column(name = "OBSERVATION_QUALITY_OF_INSTALLATION")
	private String installationQualityObserv;
	
	@Column(name = "REMARKS_QUALITY_OF_INSTALLATION")
	private String installationQualityRemarks;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BASIC_LPS_ID")
	private BasicLps basicLps;

	
	
	public Integer getBasicLpsDescriptionId() {
		return basicLpsDescriptionId;
	}

	public void setBasicLpsDescriptionId(Integer basicLpsDescriptionId) {
		this.basicLpsDescriptionId = basicLpsDescriptionId;
	}

	public String getApprovedDrawingObserv() {
		return approvedDrawingObserv;
	}

	public void setApprovedDrawingObserv(String approvedDrawingObserv) {
		this.approvedDrawingObserv = approvedDrawingObserv;
	}

	public String getApprovedDrawingRemarks() {
		return approvedDrawingRemarks;
	}

	public void setApprovedDrawingRemarks(String approvedDrawingRemarks) {
		this.approvedDrawingRemarks = approvedDrawingRemarks;
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

	public String getDesignDateObserv() {
		return designDateObserv;
	}

	public void setDesignDateObserv(String designDateObserv) {
		this.designDateObserv = designDateObserv;
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

	public String getDateOfApprovalOb() {
		return dateOfApprovalOb;
	}

	public void setDateOfApprovalOb(String dateOfApprovalOb) {
		this.dateOfApprovalOb = dateOfApprovalOb;
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

	public String getInstallationQualityObserv() {
		return installationQualityObserv;
	}

	public void setInstallationQualityObserv(String installationQualityObserv) {
		this.installationQualityObserv = installationQualityObserv;
	}

	public String getInstallationQualityRemarks() {
		return installationQualityRemarks;
	}

	public void setInstallationQualityRemarks(String installationQualityRemarks) {
		this.installationQualityRemarks = installationQualityRemarks;
	}

	public BasicLps getBasicLps() {
		return basicLps;
	}

	public void setBasicLps(BasicLps basicLps) {
		this.basicLps = basicLps;
	}
	
	
}
