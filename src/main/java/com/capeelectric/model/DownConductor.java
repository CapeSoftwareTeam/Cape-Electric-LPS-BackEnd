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
@Table(name = "DOWNCONDUCTOR_TABLE")
public class DownConductor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWNCONDUCTOR_ID")
	private Integer downConductorId;

	@Column(name = "FLAG")
	private String flag;

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

	@Column(name = "INSTALLED_SHAFT_DOWNCONDUCTOR_OB")
	private String installedShaftDownConductorOb;

	@Column(name = "INSTALLED_SHAFT_DOWNCONDUCTOR_REM")
	private String installedShaftDownConductorRem;

	@Column(name = "ENSURE_DOWNCONDUCTOR_OB")
	private String ensureDownCnoductOb;

	@Column(name = "ENSURE_DOWNCONDUCTOR_REM")
	private String ensureDownCnoductRem;

	@Column(name = "INSTALLATION_DOWNCONDUCTOR_OB")
	private String installationDownConductOb;

	@Column(name = "INSTALLATION_DOWNCONDUCTOR_REM")
	private String installationDownConductRem;

	@Column(name = "MAXIMUM_DOWNCONDUCTOR_OB")
	private Integer maximumDownConductOb;

	@Column(name = "MAXIMUM_DOWNCONDUCTOR_REM")
	private String maximumDownConductRem;

	@Column(name = "MINIMUM_DOWNCONDUCTOR_OB")
	private Integer manimumDownConductOb;

	@Column(name = "MINIMUM_DOWNCONDUCTOR_REM")
	private String manimumDownConductRem;

	@Column(name = "TOTALNO_DOWNCONDUCTOR_OB")
	private Integer totalNoDownConductOb;

	@Column(name = "TOTALNO_DOWNCONDUCTOR_REM")
	private String totalNoDownConductRem;

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

	@Column(name = "AVG_DANGEROUS_BENDS_OB")
	private Integer averageBendsOb;

	@Column(name = "AVG_DANGEROUS_BENDS_REM")
	private String averageBendsRem;

	@Column(name = "TYPE_NATURAL_DOWN_CONDUCTORS_OB")
	private String naturalDownCondutTypeOb;

	@Column(name = "TYPE_NATURAL_DOWN_CONDUCTORS_REM")
	private String naturalDownCondutTypeRem;

	@Column(name = "NATURAL_DOWN_CONDUCTORS_DIMENSION_OB")
	private String naturalDownCondDimensionOb;

	@Column(name = "NATURAL_DOWN_CONDUCTORS_DIMENSION_REM")
	private String naturalDownCondDimensionRem;

	@Column(name = "ITR_INDEX")
	private Integer index;
	
	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "FILE_ID")
	private Integer fileId;

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

	public String getMaximumDownConductRem() {
		return maximumDownConductRem;
	}

	public void setMaximumDownConductRem(String maximumDownConductRem) {
		this.maximumDownConductRem = maximumDownConductRem;
	}

	public String getManimumDownConductRem() {
		return manimumDownConductRem;
	}

	public void setManimumDownConductRem(String manimumDownConductRem) {
		this.manimumDownConductRem = manimumDownConductRem;
	}

	public String getTotalNoDownConductRem() {
		return totalNoDownConductRem;
	}

	public void setTotalNoDownConductRem(String totalNoDownConductRem) {
		this.totalNoDownConductRem = totalNoDownConductRem;
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

	public String getInstalledShaftDownConductorOb() {
		return installedShaftDownConductorOb;
	}

	public void setInstalledShaftDownConductorOb(String installedShaftDownConductorOb) {
		this.installedShaftDownConductorOb = installedShaftDownConductorOb;
	}

	public String getInstalledShaftDownConductorRem() {
		return installedShaftDownConductorRem;
	}

	public void setInstalledShaftDownConductorRem(String installedShaftDownConductorRem) {
		this.installedShaftDownConductorRem = installedShaftDownConductorRem;
	}

	public Integer getMaximumDownConductOb() {
		return maximumDownConductOb;
	}

	public void setMaximumDownConductOb(Integer maximumDownConductOb) {
		this.maximumDownConductOb = maximumDownConductOb;
	}

	public Integer getManimumDownConductOb() {
		return manimumDownConductOb;
	}

	public void setManimumDownConductOb(Integer manimumDownConductOb) {
		this.manimumDownConductOb = manimumDownConductOb;
	}

	public Integer getTotalNoDownConductOb() {
		return totalNoDownConductOb;
	}

	public void setTotalNoDownConductOb(Integer totalNoDownConductOb) {
		this.totalNoDownConductOb = totalNoDownConductOb;
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

	public Integer getAverageBendsOb() {
		return averageBendsOb;
	}

	public void setAverageBendsOb(Integer averageBendsOb) {
		this.averageBendsOb = averageBendsOb;
	}

	public String getAverageBendsRem() {
		return averageBendsRem;
	}

	public void setAverageBendsRem(String averageBendsRem) {
		this.averageBendsRem = averageBendsRem;
	}

	public String getNaturalDownCondutTypeOb() {
		return naturalDownCondutTypeOb;
	}

	public void setNaturalDownCondutTypeOb(String naturalDownCondutTypeOb) {
		this.naturalDownCondutTypeOb = naturalDownCondutTypeOb;
	}

	public String getNaturalDownCondutTypeRem() {
		return naturalDownCondutTypeRem;
	}

	public void setNaturalDownCondutTypeRem(String naturalDownCondutTypeRem) {
		this.naturalDownCondutTypeRem = naturalDownCondutTypeRem;
	}

	public String getNaturalDownCondDimensionOb() {
		return naturalDownCondDimensionOb;
	}

	public void setNaturalDownCondDimensionOb(String naturalDownCondDimensionOb) {
		this.naturalDownCondDimensionOb = naturalDownCondDimensionOb;
	}

	public String getNaturalDownCondDimensionRem() {
		return naturalDownCondDimensionRem;
	}

	public void setNaturalDownCondDimensionRem(String naturalDownCondDimensionRem) {
		this.naturalDownCondDimensionRem = naturalDownCondDimensionRem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
