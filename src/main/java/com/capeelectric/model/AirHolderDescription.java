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
@Table(name = "AIR_HOLDERS_DESCRIPTION_TABLE")
public class AirHolderDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOLDERS_DESCRIPTION_ID")
	private Integer holderDescriptionId;

	@Column(name = "PHYSICAL_INSPECTION_OBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;

	@Column(name = "CONDUCTORHOLDER_FLATSURAFACE_OBSERVATION")
	private String conductorHolderFlatSurfaceOb;

	@Column(name = "CONDUCTORHOLDER_FLATSURAFACE_REMARKS")
	private String conductorHolderFlatSurfaceRe;

	@Column(name = "CODUCTOR_HOLDER_OBSERVATION")
	private String conductorHolderOb;

	@Column(name = "CODUCTOR_HOLDER_REMARKS")
	private String conductorHolderRe;

	@Column(name = "MATERIAL_OF_HOLDEROB")
	private String materailOfHolderOb;

	@Column(name = "MATERIAL_OF_HOLDERREM")
	private String materailOfHolderRem;

	@Column(name = "MATERIAL_OFPARPET_HOLDEROB")
	private String materailOfParpetHolderOb;

	@Column(name = "MATERIAL_OFPARPET_HOLDERREM")
	private String materailOfParpetHolderRem;

	@Column(name = "HOLDER_TYPE_OBSERVATION")
	private String holderTypeOb;

	@Column(name = "HOLDER_TYPE_REMARKS")
	private String holderTypeRe;

//	@Column(name = "MATERIAL_HOLDER_OBSERVATION")
//	private String materialHolderOb;
//
//	@Column(name = "MATERIAL_HOLDER_REMARKS")
//	private String materialHolderRe;

	@Column(name = "TOTAL_HOLDERSNO_OBSERVATION")
	private String totalHolderNoOb;

	@Column(name = "TOTAL_HOLDERSNO_REMARKS")
	private String totalHolderNoRe;

	@Column(name = "TOTALPARPET_HOLDER_OBSERVATION")
	private String totalParpetHolderNoOb;

	@Column(name = "TOTALPARPET_HOLDER_REMARKS")
	private String totalParpetHolderNoRe;

	@Column(name = "HO_INSP_NO_OBS")
	private String holderInspNoOb;

	@Column(name = "HO_INSP_NO_REM")
	private String holderInspNoRe;

	@Column(name = "HO_INSP_PASSED_NO_OBS")
	private String holderInspPassedNoOb;

	@Column(name = "HO_INSP_PASSED_NO_REM")
	private String holderInspPassedNoRe;

	@Column(name = "HO_INSP_FAILED_NO_OBS")
	private String holderInspFailedNoOb;

	@Column(name = "HO_INSP_FAILED_NO_REM")
	private String holderInspFailedNoRe;

	@Column(name = "PH_INSP_NO_OBS")
	private String parpetInspectionNoOb;

	@Column(name = "PH_INSP_NO_REM")
	private String parpetInspectionNoRe;

	@Column(name = "PH_INSP_PASSED_NO_OBS")
	private String parpetInspectionPassedNoOb;

	@Column(name = "PH_INSP_PASSED_NO_REM")
	private String parpetInspectionPassedNoRe;

	@Column(name = "PH_INSP_FAILED_NO_OBS")
	private String parpetInspectionFailedNoOb;

	@Column(name = "PH_INSP_FAILED_NO_REM")
	private String parpetInspectionFailedNoRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDes;

	public Integer getHolderDescriptionId() {
		return holderDescriptionId;
	}

	public String getMaterailOfHolderOb() {
		return materailOfHolderOb;
	}

	public void setMaterailOfHolderOb(String materailOfHolderOb) {
		this.materailOfHolderOb = materailOfHolderOb;
	}

	public String getMaterailOfHolderRem() {
		return materailOfHolderRem;
	}

	public void setMaterailOfHolderRem(String materailOfHolderRem) {
		this.materailOfHolderRem = materailOfHolderRem;
	}

	public String getMaterailOfParpetHolderOb() {
		return materailOfParpetHolderOb;
	}

	public void setMaterailOfParpetHolderOb(String materailOfParpetHolderOb) {
		this.materailOfParpetHolderOb = materailOfParpetHolderOb;
	}

	public String getMaterailOfParpetHolderRem() {
		return materailOfParpetHolderRem;
	}

	public void setMaterailOfParpetHolderRem(String materailOfParpetHolderRem) {
		this.materailOfParpetHolderRem = materailOfParpetHolderRem;
	}

	public String getHolderInspFailedNoRe() {
		return holderInspFailedNoRe;
	}

	public void setHolderInspFailedNoRe(String holderInspFailedNoRe) {
		this.holderInspFailedNoRe = holderInspFailedNoRe;
	}

	public void setHolderDescriptionId(Integer holderDescriptionId) {
		this.holderDescriptionId = holderDescriptionId;
	}

	public String getPhysicalInspectionOb() {
		return physicalInspectionOb;
	}

	public void setPhysicalInspectionOb(String physicalInspectionOb) {
		this.physicalInspectionOb = physicalInspectionOb;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getConductorHolderFlatSurfaceOb() {
		return conductorHolderFlatSurfaceOb;
	}

	public void setConductorHolderFlatSurfaceOb(String conductorHolderFlatSurfaceOb) {
		this.conductorHolderFlatSurfaceOb = conductorHolderFlatSurfaceOb;
	}

	public String getConductorHolderFlatSurfaceRe() {
		return conductorHolderFlatSurfaceRe;
	}

	public void setConductorHolderFlatSurfaceRe(String conductorHolderFlatSurfaceRe) {
		this.conductorHolderFlatSurfaceRe = conductorHolderFlatSurfaceRe;
	}

	public String getConductorHolderOb() {
		return conductorHolderOb;
	}

	public void setConductorHolderOb(String conductorHolderOb) {
		this.conductorHolderOb = conductorHolderOb;
	}

	public String getConductorHolderRe() {
		return conductorHolderRe;
	}

	public void setConductorHolderRe(String conductorHolderRe) {
		this.conductorHolderRe = conductorHolderRe;
	}

	public String getHolderTypeOb() {
		return holderTypeOb;
	}

	public void setHolderTypeOb(String holderTypeOb) {
		this.holderTypeOb = holderTypeOb;
	}

	public String getHolderTypeRe() {
		return holderTypeRe;
	}

	public void setHolderTypeRe(String holderTypeRe) {
		this.holderTypeRe = holderTypeRe;
	}

//	public String getMaterialHolderOb() {
//		return materialHolderOb;
//	}
//
//	public void setMaterialHolderOb(String materialHolderOb) {
//		this.materialHolderOb = materialHolderOb;
//	}
//
//	public String getMaterialHolderRe() {
//		return materialHolderRe;
//	}
//
//	public void setMaterialHolderRe(String materialHolderRe) {
//		this.materialHolderRe = materialHolderRe;
//	}

	public String getTotalHolderNoOb() {
		return totalHolderNoOb;
	}

	public void setTotalHolderNoOb(String totalHolderNoOb) {
		this.totalHolderNoOb = totalHolderNoOb;
	}

	public String getTotalHolderNoRe() {
		return totalHolderNoRe;
	}

	public void setTotalHolderNoRe(String totalHolderNoRe) {
		this.totalHolderNoRe = totalHolderNoRe;
	}

	public String getTotalParpetHolderNoOb() {
		return totalParpetHolderNoOb;
	}

	public void setTotalParpetHolderNoOb(String totalParpetHolderNoOb) {
		this.totalParpetHolderNoOb = totalParpetHolderNoOb;
	}

	public String getTotalParpetHolderNoRe() {
		return totalParpetHolderNoRe;
	}

	public void setTotalParpetHolderNoRe(String totalParpetHolderNoRe) {
		this.totalParpetHolderNoRe = totalParpetHolderNoRe;
	}

	public String getHolderInspNoOb() {
		return holderInspNoOb;
	}

	public void setHolderInspNoOb(String holderInspNoOb) {
		this.holderInspNoOb = holderInspNoOb;
	}

	public String getHolderInspNoRe() {
		return holderInspNoRe;
	}

	public void setHolderInspNoRe(String holderInspNoRe) {
		this.holderInspNoRe = holderInspNoRe;
	}

	public String getHolderInspPassedNoOb() {
		return holderInspPassedNoOb;
	}

	public void setHolderInspPassedNoOb(String holderInspPassedNoOb) {
		this.holderInspPassedNoOb = holderInspPassedNoOb;
	}

	public String getHolderInspFailedNoOb() {
		return holderInspFailedNoOb;
	}

	public void setHolderInspFailedNoOb(String holderInspFailedNoOb) {
		this.holderInspFailedNoOb = holderInspFailedNoOb;
	}

	public String getParpetInspectionNoOb() {
		return parpetInspectionNoOb;
	}

	public void setParpetInspectionNoOb(String parpetInspectionNoOb) {
		this.parpetInspectionNoOb = parpetInspectionNoOb;
	}

	public String getParpetInspectionNoRe() {
		return parpetInspectionNoRe;
	}

	public void setParpetInspectionNoRe(String parpetInspectionNoRe) {
		this.parpetInspectionNoRe = parpetInspectionNoRe;
	}

	public String getParpetInspectionPassedNoOb() {
		return parpetInspectionPassedNoOb;
	}

	public void setParpetInspectionPassedNoOb(String parpetInspectionPassedNoOb) {
		this.parpetInspectionPassedNoOb = parpetInspectionPassedNoOb;
	}

	public String getParpetInspectionPassedNoRe() {
		return parpetInspectionPassedNoRe;
	}

	public void setParpetInspectionPassedNoRe(String parpetInspectionPassedNoRe) {
		this.parpetInspectionPassedNoRe = parpetInspectionPassedNoRe;
	}

	public String getParpetInspectionFailedNoOb() {
		return parpetInspectionFailedNoOb;
	}

	public void setParpetInspectionFailedNoOb(String parpetInspectionFailedNoOb) {
		this.parpetInspectionFailedNoOb = parpetInspectionFailedNoOb;
	}

	public String getParpetInspectionFailedNoRe() {
		return parpetInspectionFailedNoRe;
	}

	public void setParpetInspectionFailedNoRe(String parpetInspectionFailedNoRe) {
		this.parpetInspectionFailedNoRe = parpetInspectionFailedNoRe;
	}

	public String getHolderInspPassedNoRe() {
		return holderInspPassedNoRe;
	}

	public void setHolderInspPassedNoRe(String holderInspPassedNoRe) {
		this.holderInspPassedNoRe = holderInspPassedNoRe;
	}

	public LpsAirDiscription getLpsAirDes() {
		return lpsAirDes;
	}

	public void setLpsAirDes(LpsAirDiscription lpsAirDes) {
		this.lpsAirDes = lpsAirDes;
	}

}
