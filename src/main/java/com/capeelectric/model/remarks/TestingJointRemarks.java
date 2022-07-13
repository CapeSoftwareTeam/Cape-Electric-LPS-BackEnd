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
@Table(name = "TESTJOINTS_TABLE")
public class TestingJointRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTJOINT_ID")
	private Integer testJointId;

	@Column(name = "FLAG")
	private String flag;


	@Column(name = "MATERIAL_TESTJOINT_REM")
	private String materialTestJointRem;

	@Column(name = "ACCESSIBILITY_OF_TESTJOINT_REM")
	private String accessibilityOfTestJointRem;

	@Column(name = "NONMETALICCASING_PROTECTION_REM")
	private String nonMetalicProtectionRem;

	@Column(name = "TESTJOINTPLACED_GROUNTLEVEL_REM")
	private String testJointPlacedGroungLevelRem;

	@Column(name = "BIMETALLICISSUE_CHECK_REM")
	private String bimetallicIssueCheckRem;

	@Column(name = "TOUCHING_CONDUCTORS_REM")
	private String touchingConductorsRem;

	@Column(name = "TOTALNO_OF_TESTJOINT_REM")
	private String totalNoOfTestJointRem;

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

	public Integer getTestJointId() {
		return testJointId;
	}

	public void setTestJointId(Integer testJointId) {
		this.testJointId = testJointId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMaterialTestJointRem() {
		return materialTestJointRem;
	}

	public void setMaterialTestJointRem(String materialTestJointRem) {
		this.materialTestJointRem = materialTestJointRem;
	}

	public String getAccessibilityOfTestJointRem() {
		return accessibilityOfTestJointRem;
	}

	public void setAccessibilityOfTestJointRem(String accessibilityOfTestJointRem) {
		this.accessibilityOfTestJointRem = accessibilityOfTestJointRem;
	}

	public String getNonMetalicProtectionRem() {
		return nonMetalicProtectionRem;
	}

	public void setNonMetalicProtectionRem(String nonMetalicProtectionRem) {
		this.nonMetalicProtectionRem = nonMetalicProtectionRem;
	}

	public String getTestJointPlacedGroungLevelRem() {
		return testJointPlacedGroungLevelRem;
	}

	public void setTestJointPlacedGroungLevelRem(String testJointPlacedGroungLevelRem) {
		this.testJointPlacedGroungLevelRem = testJointPlacedGroungLevelRem;
	}

	public String getBimetallicIssueCheckRem() {
		return bimetallicIssueCheckRem;
	}

	public void setBimetallicIssueCheckRem(String bimetallicIssueCheckRem) {
		this.bimetallicIssueCheckRem = bimetallicIssueCheckRem;
	}

	public String getTouchingConductorsRem() {
		return touchingConductorsRem;
	}

	public void setTouchingConductorsRem(String touchingConductorsRem) {
		this.touchingConductorsRem = touchingConductorsRem;
	}

	public String getTotalNoOfTestJointRem() {
		return totalNoOfTestJointRem;
	}

	public void setTotalNoOfTestJointRem(String totalNoOfTestJointRem) {
		this.totalNoOfTestJointRem = totalNoOfTestJointRem;
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
