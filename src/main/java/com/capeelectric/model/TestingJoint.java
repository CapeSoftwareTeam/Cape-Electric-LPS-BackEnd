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
@Table(name = "TESTJOINTS_TABLE")
public class TestingJoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTJOINT_ID")
	private Integer testJointId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "TESTJOINT_TYPE_OB")
	private String testJointTypeOb;

	@Column(name = "TESTJOINT_TYPE_REM")
	private String testJointTypeRem;

	@Column(name = "MATERIAL_TESTJOINT_OB")
	private String materialTestJointOb;

	@Column(name = "MATERIAL_TESTJOINT_REM")
	private String materialTestJointRem;

	@Column(name = "ACCESSIBILITY_OF_TESTJOINT_OB")
	private String accessibilityOfTestJointOb;

	@Column(name = "ACCESSIBILITY_OF_TESTJOINT_REM")
	private String accessibilityOfTestJointRem;

	@Column(name = "NONMETALICCASING_PROTECTION_OB")
	private String nonMetalicProtectionOb;

	@Column(name = "NONMETALICCASING_PROTECTION_REM")
	private String nonMetalicProtectionRem;

	@Column(name = "TESTJOINTPLACED_GROUNTLEVEL_OB")
	private String testJointPlacedGroungLevelOb;

	@Column(name = "TESTJOINTPLACED_GROUNTLEVEL_REM")
	private String testJointPlacedGroungLevelRem;

	@Column(name = "BIMETALLICISSUE_CHECK_OB")
	private String bimetallicIssueCheckOb;

	@Column(name = "BIMETALLICISSUE_CHECK_REM")
	private String bimetallicIssueCheckRem;
	
	@Column(name = "TOUCHING_CONDUCTORS_OB")
	private String touchingConductorsOb;

	@Column(name = "TOUCHING_CONDUCTORS_REM")
	private String touchingConductorsRem;

	@Column(name = "TOTALNO_OF_TESTJOINT_OB")
	private Integer totalNoOfTestJointOB;

	@Column(name = "TOTALNO_OF_TESTJOINT_REM")
	private String totalNoOfTestJointRem;

	@Column(name = "INSPECTED_NO_OB")
	private Integer inspectedNoOb;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private Integer inspectionPassedNoOb;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_OB")
	private Integer inspectionFailedNoOb;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorDescription downConductorDescription;

	public Integer getTestJointId() {
		return testJointId;
	}

	public void setTestJointId(Integer testJointId) {
		this.testJointId = testJointId;
	}

	public String getTestJointTypeOb() {
		return testJointTypeOb;
	}

	public void setTestJointTypeOb(String testJointTypeOb) {
		this.testJointTypeOb = testJointTypeOb;
	}

	public String getTestJointTypeRem() {
		return testJointTypeRem;
	}

	public void setTestJointTypeRem(String testJointTypeRem) {
		this.testJointTypeRem = testJointTypeRem;
	}

	public String getMaterialTestJointOb() {
		return materialTestJointOb;
	}

	public void setMaterialTestJointOb(String materialTestJointOb) {
		this.materialTestJointOb = materialTestJointOb;
	}

	public String getMaterialTestJointRem() {
		return materialTestJointRem;
	}

	public void setMaterialTestJointRem(String materialTestJointRem) {
		this.materialTestJointRem = materialTestJointRem;
	}

	public String getAccessibilityOfTestJointOb() {
		return accessibilityOfTestJointOb;
	}

	public void setAccessibilityOfTestJointOb(String accessibilityOfTestJointOb) {
		this.accessibilityOfTestJointOb = accessibilityOfTestJointOb;
	}

	public String getAccessibilityOfTestJointRem() {
		return accessibilityOfTestJointRem;
	}

	public void setAccessibilityOfTestJointRem(String accessibilityOfTestJointRem) {
		this.accessibilityOfTestJointRem = accessibilityOfTestJointRem;
	}

	public String getNonMetalicProtectionOb() {
		return nonMetalicProtectionOb;
	}

	public void setNonMetalicProtectionOb(String nonMetalicProtectionOb) {
		this.nonMetalicProtectionOb = nonMetalicProtectionOb;
	}

	public String getNonMetalicProtectionRem() {
		return nonMetalicProtectionRem;
	}

	public void setNonMetalicProtectionRem(String nonMetalicProtectionRem) {
		this.nonMetalicProtectionRem = nonMetalicProtectionRem;
	}

	public String getTestJointPlacedGroungLevelOb() {
		return testJointPlacedGroungLevelOb;
	}

	public void setTestJointPlacedGroungLevelOb(String testJointPlacedGroungLevelOb) {
		this.testJointPlacedGroungLevelOb = testJointPlacedGroungLevelOb;
	}

	public String getTestJointPlacedGroungLevelRem() {
		return testJointPlacedGroungLevelRem;
	}

	public void setTestJointPlacedGroungLevelRem(String testJointPlacedGroungLevelRem) {
		this.testJointPlacedGroungLevelRem = testJointPlacedGroungLevelRem;
	}

	public String getBimetallicIssueCheckOb() {
		return bimetallicIssueCheckOb;
	}

	public void setBimetallicIssueCheckOb(String bimetallicIssueCheckOb) {
		this.bimetallicIssueCheckOb = bimetallicIssueCheckOb;
	}

	public String getBimetallicIssueCheckRem() {
		return bimetallicIssueCheckRem;
	}

	public void setBimetallicIssueCheckRem(String bimetallicIssueCheckRem) {
		this.bimetallicIssueCheckRem = bimetallicIssueCheckRem;
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

	public DownConductorDescription getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(DownConductorDescription downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTouchingConductorsOb() {
		return touchingConductorsOb;
	}

	public void setTouchingConductorsOb(String touchingConductorsOb) {
		this.touchingConductorsOb = touchingConductorsOb;
	}

	public String getTouchingConductorsRem() {
		return touchingConductorsRem;
	}

	public void setTouchingConductorsRem(String touchingConductorsRem) {
		this.touchingConductorsRem = touchingConductorsRem;
	}

	public Integer getTotalNoOfTestJointOB() {
		return totalNoOfTestJointOB;
	}

	public void setTotalNoOfTestJointOB(Integer totalNoOfTestJointOB) {
		this.totalNoOfTestJointOB = totalNoOfTestJointOB;
	}

	public Integer getInspectedNoOb() {
		return inspectedNoOb;
	}

	public void setInspectedNoOb(Integer inspectedNoOb) {
		this.inspectedNoOb = inspectedNoOb;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
