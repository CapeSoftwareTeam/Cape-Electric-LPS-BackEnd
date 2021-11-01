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
@Table(name = "EARTHELECTRODECHAMBER_TABLE")
public class EarthElectrodeChamber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHELECTRODECHAMBER_ID")
	private Integer earthingElectrodeChamberId;

	@Column(name = "PSYSICAL_INSPECTIONOB")
	private String psysicalInspeOb;

	@Column(name = "PSYSICAL_INSPECTIONREM")
	private String psysicalInspeRem;

	@Column(name = "CHAMBER_TYPEOB")
	private String chamberTypeOb;

	@Column(name = "CHAMBER_TYPEREM")
	private String chamberTypeRem;

	@Column(name = "CHAMBER_SIZEOB")
	private String chamberSizeOb;

	@Column(name = "CHAMBER_SIZEREM")
	private String chamberSizeRem;

	@Column(name = "MAXIMUMWITHSTAND_LOADOB")
	private String maximumWithStandLoadOb;

	@Column(name = "MAXIMUMWITHSTAND_LOADREM")
	private String maximumWithStandLoadRem;

	@Column(name = "CHAMBER_PLACED_SOILOB")
	private String maximumPlacedSoilOb;

	@Column(name = "CHAMBER_PLACED_SOILREM")
	private String maximumPlacedSoilRem;

	@Column(name = "TOTAL_CHAMBERSNOOB")
	private String totalChamberNoOb;

	@Column(name = "TOTAL_CHAMBERSNOREM")
	private String totalChamberNoRem;

	@Column(name = "EARTH_ELECTRODEBURIEDOB")
	private String earthElectrodeBuriedOb;

	@Column(name = "EARTH_ELECTRODEBURIEDREM")
	private String earthElectrodeBuriedRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthingElectrodeChamberId() {
		return earthingElectrodeChamberId;
	}

	public void setEarthingElectrodeChamberId(Integer earthingElectrodeChamberId) {
		this.earthingElectrodeChamberId = earthingElectrodeChamberId;
	}

	public String getPsysicalInspeOb() {
		return psysicalInspeOb;
	}

	public void setPsysicalInspeOb(String psysicalInspeOb) {
		this.psysicalInspeOb = psysicalInspeOb;
	}

	public String getPsysicalInspeRem() {
		return psysicalInspeRem;
	}

	public void setPsysicalInspeRem(String psysicalInspeRem) {
		this.psysicalInspeRem = psysicalInspeRem;
	}

	public String getChamberTypeOb() {
		return chamberTypeOb;
	}

	public void setChamberTypeOb(String chamberTypeOb) {
		this.chamberTypeOb = chamberTypeOb;
	}

	public String getChamberTypeRem() {
		return chamberTypeRem;
	}

	public void setChamberTypeRem(String chamberTypeRem) {
		this.chamberTypeRem = chamberTypeRem;
	}

	public String getChamberSizeOb() {
		return chamberSizeOb;
	}

	public void setChamberSizeOb(String chamberSizeOb) {
		this.chamberSizeOb = chamberSizeOb;
	}

	public String getChamberSizeRem() {
		return chamberSizeRem;
	}

	public void setChamberSizeRem(String chamberSizeRem) {
		this.chamberSizeRem = chamberSizeRem;
	}

	public String getMaximumWithStandLoadOb() {
		return maximumWithStandLoadOb;
	}

	public void setMaximumWithStandLoadOb(String maximumWithStandLoadOb) {
		this.maximumWithStandLoadOb = maximumWithStandLoadOb;
	}

	public String getMaximumWithStandLoadRem() {
		return maximumWithStandLoadRem;
	}

	public void setMaximumWithStandLoadRem(String maximumWithStandLoadRem) {
		this.maximumWithStandLoadRem = maximumWithStandLoadRem;
	}

	public String getMaximumPlacedSoilOb() {
		return maximumPlacedSoilOb;
	}

	public void setMaximumPlacedSoilOb(String maximumPlacedSoilOb) {
		this.maximumPlacedSoilOb = maximumPlacedSoilOb;
	}

	public String getMaximumPlacedSoilRem() {
		return maximumPlacedSoilRem;
	}

	public void setMaximumPlacedSoilRem(String maximumPlacedSoilRem) {
		this.maximumPlacedSoilRem = maximumPlacedSoilRem;
	}

	public String getTotalChamberNoOb() {
		return totalChamberNoOb;
	}

	public void setTotalChamberNoOb(String totalChamberNoOb) {
		this.totalChamberNoOb = totalChamberNoOb;
	}

	public String getTotalChamberNoRem() {
		return totalChamberNoRem;
	}

	public void setTotalChamberNoRem(String totalChamberNoRem) {
		this.totalChamberNoRem = totalChamberNoRem;
	}

	public String getEarthElectrodeBuriedOb() {
		return earthElectrodeBuriedOb;
	}

	public void setEarthElectrodeBuriedOb(String earthElectrodeBuriedOb) {
		this.earthElectrodeBuriedOb = earthElectrodeBuriedOb;
	}

	public String getEarthElectrodeBuriedRem() {
		return earthElectrodeBuriedRem;
	}

	public void setEarthElectrodeBuriedRem(String earthElectrodeBuriedRem) {
		this.earthElectrodeBuriedRem = earthElectrodeBuriedRem;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

}