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
@Table(name = "DOWNCONDUCTOR_TABLE")
public class DownConductorRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWNCONDUCTOR_ID")
	private Integer downConductorId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTION_REM")
	private String physicalInspectionRem;

	@Column(name = "CONDUCTOR_MATERIAL_REM")
	private String conductMaterialRem;

	@Column(name = "CONDUCTOR_SIZE_REM")
	private String conductSizeRem;
	
	@Column(name = "DOWNCONDUCTOR_EXPOSED_RE")
	private String downConductExposedRem;

	@Column(name = "DOWNCONDUCTOR_LOCATION_REM")
	private String downConductLocationdRem;

	@Column(name = "DOWNCONDUCTOR_GUTTERS_REM")
	private String downConductGutterRem;

	@Column(name = "INSTALLED_SHAFT_DOWNCONDUCTOR_REM")
	private String installedShaftDownConductorRem;

	@Column(name = "ENSURE_DOWNCONDUCTOR_REM")
	private String ensureDownCnoductRem;

	@Column(name = "INSTALLATION_DOWNCONDUCTOR_REM")
	private String installationDownConductRem;

	@Column(name = "MAXIMUM_DOWNCONDUCTOR_REM")
	private String maximumDownConductRem;

	@Column(name = "MINIMUM_DOWNCONDUCTOR_REM")
	private String manimumDownConductRem;

	@Column(name = "TOTALNO_DOWNCONDUCTOR_REM")
	private String totalNoDownConductRem;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;

	@Column(name = "AVG_DANGEROUS_BENDS_REM")
	private String averageBendsRem;

	@Column(name = "TYPE_NATURAL_DOWN_CONDUCTORS_REM")
	private String naturalDownCondutTypeRem;

	@Column(name = "NATURAL_DOWN_CONDUCTORS_DIMENSION_REM")
	private String naturalDownCondDimensionRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorsDescriptionRemarks downConductorDescription;

	public Integer getDownConductorId() {
		return downConductorId;
	}

	public void setDownConductorId(Integer downConductorId) {
		this.downConductorId = downConductorId;
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

	public String getConductMaterialRem() {
		return conductMaterialRem;
	}

	public void setConductMaterialRem(String conductMaterialRem) {
		this.conductMaterialRem = conductMaterialRem;
	}

	public String getConductSizeRem() {
		return conductSizeRem;
	}

	public void setConductSizeRem(String conductSizeRem) {
		this.conductSizeRem = conductSizeRem;
	}

	public String getDownConductExposedRem() {
		return downConductExposedRem;
	}

	public void setDownConductExposedRem(String downConductExposedRem) {
		this.downConductExposedRem = downConductExposedRem;
	}

	public String getDownConductLocationdRem() {
		return downConductLocationdRem;
	}

	public void setDownConductLocationdRem(String downConductLocationdRem) {
		this.downConductLocationdRem = downConductLocationdRem;
	}

	public String getDownConductGutterRem() {
		return downConductGutterRem;
	}

	public void setDownConductGutterRem(String downConductGutterRem) {
		this.downConductGutterRem = downConductGutterRem;
	}

	public String getInstalledShaftDownConductorRem() {
		return installedShaftDownConductorRem;
	}

	public void setInstalledShaftDownConductorRem(String installedShaftDownConductorRem) {
		this.installedShaftDownConductorRem = installedShaftDownConductorRem;
	}

	public String getEnsureDownCnoductRem() {
		return ensureDownCnoductRem;
	}

	public void setEnsureDownCnoductRem(String ensureDownCnoductRem) {
		this.ensureDownCnoductRem = ensureDownCnoductRem;
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

	public String getAverageBendsRem() {
		return averageBendsRem;
	}

	public void setAverageBendsRem(String averageBendsRem) {
		this.averageBendsRem = averageBendsRem;
	}

	public String getNaturalDownCondutTypeRem() {
		return naturalDownCondutTypeRem;
	}

	public void setNaturalDownCondutTypeRem(String naturalDownCondutTypeRem) {
		this.naturalDownCondutTypeRem = naturalDownCondutTypeRem;
	}

	public String getNaturalDownCondDimensionRem() {
		return naturalDownCondDimensionRem;
	}

	public void setNaturalDownCondDimensionRem(String naturalDownCondDimensionRem) {
		this.naturalDownCondDimensionRem = naturalDownCondDimensionRem;
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
