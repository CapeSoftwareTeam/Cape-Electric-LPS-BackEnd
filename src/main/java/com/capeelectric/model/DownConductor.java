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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "DOWNCONDUCTOR_TABLE")
public class DownConductor implements Serializable  {
     
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWNCONDUCTOR_ID")
	private Integer downConductorId;
	
	@Column(name = "LOCATION_NO_OB")
	private String lacationNoOb;
	
	
	@Column(name = "LOCATION_NO_REM")
	private String lacationNoRe;
	
	@Column(name = "PHYSICAL_INSPECTION_OB")
	private String physicalInspectionOb;
	
	@Column(name = "PHYSICAL_INSPECTION_REM")
	private String physicalInspectionRem;
	
	@Column(name = "CONDUCTOR_MATERIAL_OB")
	private String conductMaterialOb;
	
	@Column(name = "CONDUCTOR_MATERIAL_REM")
	private String conductMaterialRem;
	
	@Column(name = "CONDUCTOR_SIZE_OB")
	private String conductSizeOb;
	
	@Column(name = "CONDUCTOR_SIZE_REM")
	private String conductSizeRem;
	
	@Column(name = "DOWNCONDUCTOR_EXPOSED_OB")
	private String downConductExposedOb;
	
	@Column(name = "DOWNCONDUCTOR_EXPOSED_RE")
	private String downConductExposedRem;
	
	@Column(name = "DOWNCONDUCTOR_LOCATION_OB")
	private String downConductLocationdOb;
	
	@Column(name = "DOWNCONDUCTOR_LOCATION_REM")
	private String downConductLocationdRem;
	
	@Column(name = "DOWNCONDUCTOR_GUTTERS_OB")
	private String downConductGutterOb;
	
	@Column(name = "DOWNCONDUCTOR_GUTTERS_REM")
	private String downConductGutterRem;
	
	@Column(name = "ENSURE_DOWNCONDUCTOR_OB")
	private String ensureDownCnoductOb;

	@Column(name = "ENSURE_DOWNCONDUCTOR_REM")
	private String ensureDownCnoductRem;
	
	
	@Column(name = "INSTALLATION_DOWNCONDUCTOR_OB")
	private String installationDownConductOb;
	
	@Column(name = "INSTALLATION_DOWNCONDUCTOR_REM")
	private String installationDownConductRem;
	
	@Column(name = "MAXIMUM_DOWNCONDUCTOR_OB")
	private String maximumDownConductOb;
	
	@Column(name = "MAXIMUM_DOWNCONDUCTOR_REM")
	private String maximumDownConductRem;
	
	@Column(name = "MINIMUM_DOWNCONDUCTOR_OB")
	private String manimumDownConductOb;
	
	@Column(name = "MINIMUM_DOWNCONDUCTOR_REM")
	private String manimumDownConductRem;

	@Column(name = "TOTALNO_DOWNCONDUCTOR_OB")
	private String totalNoDownConductOb;
	
	@Column(name = "TOTALNO_DOWNCONDUCTOR_REM")
	private String totalNoDownConductRem;
	
	@Column(name = "INSPECTED_NO_OB")
	private String inspectedNoOb;
	
	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;
	
	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private String inspectionPassedNoOb;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;
	
	@Column(name = "INSPECTIONFAILED_NO_OB")
	private String inspectionFailedNoOb;
	
	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorDescription downConductorDescription;

	public Integer getDownConductorId() {
		return downConductorId;
	}

	public void setDownConductorId(Integer downConductorId) {
		this.downConductorId = downConductorId;
	}

	public String getLacationNoOb() {
		return lacationNoOb;
	}

	public void setLacationNoOb(String lacationNoOb) {
		this.lacationNoOb = lacationNoOb;
	}

	public String getLacationNoRe() {
		return lacationNoRe;
	}

	public void setLacationNoRe(String lacationNoRe) {
		this.lacationNoRe = lacationNoRe;
	}

	public String getPhysicalInspectionOb() {
		return physicalInspectionOb;
	}

	public void setPhysicalInspectionOb(String physicalInspectionOb) {
		this.physicalInspectionOb = physicalInspectionOb;
	}

	public String getPhysicalInspectionRem() {
		return physicalInspectionRem;
	}

	public void setPhysicalInspectionRem(String physicalInspectionRem) {
		this.physicalInspectionRem = physicalInspectionRem;
	}

	public String getConductMaterialOb() {
		return conductMaterialOb;
	}

	public void setConductMaterialOb(String conductMaterialOb) {
		this.conductMaterialOb = conductMaterialOb;
	}

	public String getConductMaterialRem() {
		return conductMaterialRem;
	}

	public void setConductMaterialRem(String conductMaterialRem) {
		this.conductMaterialRem = conductMaterialRem;
	}

	public String getConductSizeOb() {
		return conductSizeOb;
	}

	public void setConductSizeOb(String conductSizeOb) {
		this.conductSizeOb = conductSizeOb;
	}

	public String getConductSizeRem() {
		return conductSizeRem;
	}

	public void setConductSizeRem(String conductSizeRem) {
		this.conductSizeRem = conductSizeRem;
	}

	public String getDownConductExposedOb() {
		return downConductExposedOb;
	}

	public void setDownConductExposedOb(String downConductExposedOb) {
		this.downConductExposedOb = downConductExposedOb;
	}

	public String getDownConductExposedRem() {
		return downConductExposedRem;
	}

	public void setDownConductExposedRem(String downConductExposedRem) {
		this.downConductExposedRem = downConductExposedRem;
	}

	public String getDownConductLocationdOb() {
		return downConductLocationdOb;
	}

	public void setDownConductLocationdOb(String downConductLocationdOb) {
		this.downConductLocationdOb = downConductLocationdOb;
	}

	public String getDownConductLocationdRem() {
		return downConductLocationdRem;
	}

	public void setDownConductLocationdRem(String downConductLocationdRem) {
		this.downConductLocationdRem = downConductLocationdRem;
	}

	public String getDownConductGutterOb() {
		return downConductGutterOb;
	}

	public void setDownConductGutterOb(String downConductGutterOb) {
		this.downConductGutterOb = downConductGutterOb;
	}

	public String getDownConductGutterRem() {
		return downConductGutterRem;
	}

	public void setDownConductGutterRem(String downConductGutterRem) {
		this.downConductGutterRem = downConductGutterRem;
	}

	public String getEnsureDownCnoductOb() {
		return ensureDownCnoductOb;
	}

	public void setEnsureDownCnoductOb(String ensureDownCnoductOb) {
		this.ensureDownCnoductOb = ensureDownCnoductOb;
	}

	public String getEnsureDownCnoductRem() {
		return ensureDownCnoductRem;
	}

	public void setEnsureDownCnoductRem(String ensureDownCnoductRem) {
		this.ensureDownCnoductRem = ensureDownCnoductRem;
	}

	public String getInstallationDownConductOb() {
		return installationDownConductOb;
	}

	public void setInstallationDownConductOb(String installationDownConductOb) {
		this.installationDownConductOb = installationDownConductOb;
	}

	public String getInstallationDownConductRem() {
		return installationDownConductRem;
	}

	public void setInstallationDownConductRem(String installationDownConductRem) {
		this.installationDownConductRem = installationDownConductRem;
	}

	public String getMaximumDownConductOb() {
		return maximumDownConductOb;
	}

	public void setMaximumDownConductOb(String maximumDownConductOb) {
		this.maximumDownConductOb = maximumDownConductOb;
	}

	public String getMaximumDownConductRem() {
		return maximumDownConductRem;
	}

	public void setMaximumDownConductRem(String maximumDownConductRem) {
		this.maximumDownConductRem = maximumDownConductRem;
	}

	public String getManimumDownConductOb() {
		return manimumDownConductOb;
	}

	public void setManimumDownConductOb(String manimumDownConductOb) {
		this.manimumDownConductOb = manimumDownConductOb;
	}

	public String getManimumDownConductRem() {
		return manimumDownConductRem;
	}

	public void setManimumDownConductRem(String manimumDownConductRem) {
		this.manimumDownConductRem = manimumDownConductRem;
	}

	public String getTotalNoDownConductOb() {
		return totalNoDownConductOb;
	}

	public void setTotalNoDownConductOb(String totalNoDownConductOb) {
		this.totalNoDownConductOb = totalNoDownConductOb;
	}

	public String getTotalNoDownConductRem() {
		return totalNoDownConductRem;
	}

	public void setTotalNoDownConductRem(String totalNoDownConductRem) {
		this.totalNoDownConductRem = totalNoDownConductRem;
	}

	public String getInspectedNoOb() {
		return inspectedNoOb;
	}

	public void setInspectedNoOb(String inspectedNoOb) {
		this.inspectedNoOb = inspectedNoOb;
	}

	public String getInspectedNoRem() {
		return inspectedNoRem;
	}

	public void setInspectedNoRem(String inspectedNoRem) {
		this.inspectedNoRem = inspectedNoRem;
	}

	public String getInspectionPassedNoOb() {
		return inspectionPassedNoOb;
	}

	public void setInspectionPassedNoOb(String inspectionPassedNoOb) {
		this.inspectionPassedNoOb = inspectionPassedNoOb;
	}

	public String getInspectionPassedNoRem() {
		return inspectionPassedNoRem;
	}

	public void setInspectionPassedNoRem(String inspectionPassedNoRem) {
		this.inspectionPassedNoRem = inspectionPassedNoRem;
	}

	public String getInspectionFailedNoOb() {
		return inspectionFailedNoOb;
	}

	public void setInspectionFailedNoOb(String inspectionFailedNoOb) {
		this.inspectionFailedNoOb = inspectionFailedNoOb;
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

	
	
}
