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
@Table(name = "EARTHINGCLAMPS_TABLE")
public class EarthingClampsRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHCLAMPS_ID")
	private Integer earthingClampsId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PSYSICAL_INSPECTIONINREM")
	private String psysicalInspectionInRem;

	@Column(name = "CLAMPS_FIRMLYINREM")
	private String clampsFirmlyRem;

	@Column(name = "INTERCONNECTION_OF_EARTHCLAMPSINREM")
	private String interConnectOfEarthClampInRem;

	@Column(name = "TYPE_OF_CLAMPSINREM")
	private String typeOfClampsInRem;

	@Column(name = "MATERIAL_OF_CLAMPSINREM")
	private String materialOfClampsInRem;

	@Column(name = "TOTALNO_CLAMPSINREM")
	private String totalNoClampsInRem;

	@Column(name = "INSPECTED_CLAMPSINREM")
	private String inspectedClampsInRem;

	@Column(name = "INSPECTED_PASSEDINREM")
	private String inspectionPassedInRem;

	@Column(name = "INSPECTED_FAILEDINREM")
	private String inspectionFailedInRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescriptionRemarks earthingLpsDescription;

	public Integer getEarthingClampsId() {
		return earthingClampsId;
	}

	public void setEarthingClampsId(Integer earthingClampsId) {
		this.earthingClampsId = earthingClampsId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPsysicalInspectionInRem() {
		return psysicalInspectionInRem;
	}

	public void setPsysicalInspectionInRem(String psysicalInspectionInRem) {
		this.psysicalInspectionInRem = psysicalInspectionInRem;
	}

	public String getClampsFirmlyRem() {
		return clampsFirmlyRem;
	}

	public void setClampsFirmlyRem(String clampsFirmlyRem) {
		this.clampsFirmlyRem = clampsFirmlyRem;
	}

	public String getInterConnectOfEarthClampInRem() {
		return interConnectOfEarthClampInRem;
	}

	public void setInterConnectOfEarthClampInRem(String interConnectOfEarthClampInRem) {
		this.interConnectOfEarthClampInRem = interConnectOfEarthClampInRem;
	}

	public String getTypeOfClampsInRem() {
		return typeOfClampsInRem;
	}

	public void setTypeOfClampsInRem(String typeOfClampsInRem) {
		this.typeOfClampsInRem = typeOfClampsInRem;
	}

	public String getMaterialOfClampsInRem() {
		return materialOfClampsInRem;
	}

	public void setMaterialOfClampsInRem(String materialOfClampsInRem) {
		this.materialOfClampsInRem = materialOfClampsInRem;
	}

	public String getTotalNoClampsInRem() {
		return totalNoClampsInRem;
	}

	public void setTotalNoClampsInRem(String totalNoClampsInRem) {
		this.totalNoClampsInRem = totalNoClampsInRem;
	}

	public String getInspectedClampsInRem() {
		return inspectedClampsInRem;
	}

	public void setInspectedClampsInRem(String inspectedClampsInRem) {
		this.inspectedClampsInRem = inspectedClampsInRem;
	}

	public String getInspectionPassedInRem() {
		return inspectionPassedInRem;
	}

	public void setInspectionPassedInRem(String inspectionPassedInRem) {
		this.inspectionPassedInRem = inspectionPassedInRem;
	}

	public String getInspectionFailedInRem() {
		return inspectionFailedInRem;
	}

	public void setInspectionFailedInRem(String inspectionFailedInRem) {
		this.inspectionFailedInRem = inspectionFailedInRem;
	}

	public EarthingLpsDescriptionRemarks getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescriptionRemarks earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
