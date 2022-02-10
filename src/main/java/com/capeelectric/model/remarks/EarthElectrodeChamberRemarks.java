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
@Table(name = "EARTHELECTRODECHAMBER_TABLE")
public class EarthElectrodeChamberRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHELECTRODECHAMBER_ID")
	private Integer earthingElectrodeChamberId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTIONREM")
	private String physicalInspeRem;

	@Column(name = "CHAMBER_TYPEREM")
	private String chamberTypeRem;
	
	@Column(name = "CHAMBER_SIZEREM")
	private String chamberSizeRem;

	@Column(name = "MAXIMUMWITHSTAND_LOADREM")
	private String maximumWithStandLoadRem;

	@Column(name = "CHAMBER_LOCATION_REM")
	private String chamberLocationRem;

	@Column(name = "CHAMBER_PLACED_SOILREM")
	private String maximumPlacedSoilRem;

	@Column(name = "TOTAL_CHAMBERSNOREM")
	private String totalChamberNoRem;

	@Column(name = "INSPECTED_CHAMBERINREM")
	private String inspectedChamberInRem;

	@Column(name = "INSPECTED_PASSEDINREM")
	private String inspectionPassedInRem;

	@Column(name = "INSPECTED_FAILEDINREM")
	private String inspectionFailedInRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescriptionRemarks earthingLpsDescription;

	public Integer getEarthingElectrodeChamberId() {
		return earthingElectrodeChamberId;
	}

	public void setEarthingElectrodeChamberId(Integer earthingElectrodeChamberId) {
		this.earthingElectrodeChamberId = earthingElectrodeChamberId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPhysicalInspeRem() {
		return physicalInspeRem;
	}

	public void setPhysicalInspeRem(String physicalInspeRem) {
		this.physicalInspeRem = physicalInspeRem;
	}

	public String getChamberTypeRem() {
		return chamberTypeRem;
	}

	public void setChamberTypeRem(String chamberTypeRem) {
		this.chamberTypeRem = chamberTypeRem;
	}

	public String getChamberSizeRem() {
		return chamberSizeRem;
	}

	public void setChamberSizeRem(String chamberSizeRem) {
		this.chamberSizeRem = chamberSizeRem;
	}

	public String getMaximumWithStandLoadRem() {
		return maximumWithStandLoadRem;
	}

	public void setMaximumWithStandLoadRem(String maximumWithStandLoadRem) {
		this.maximumWithStandLoadRem = maximumWithStandLoadRem;
	}

	public String getChamberLocationRem() {
		return chamberLocationRem;
	}

	public void setChamberLocationRem(String chamberLocationRem) {
		this.chamberLocationRem = chamberLocationRem;
	}

	public String getMaximumPlacedSoilRem() {
		return maximumPlacedSoilRem;
	}

	public void setMaximumPlacedSoilRem(String maximumPlacedSoilRem) {
		this.maximumPlacedSoilRem = maximumPlacedSoilRem;
	}

	public String getTotalChamberNoRem() {
		return totalChamberNoRem;
	}

	public void setTotalChamberNoRem(String totalChamberNoRem) {
		this.totalChamberNoRem = totalChamberNoRem;
	}

	public String getInspectedChamberInRem() {
		return inspectedChamberInRem;
	}

	public void setInspectedChamberInRem(String inspectedChamberInRem) {
		this.inspectedChamberInRem = inspectedChamberInRem;
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
