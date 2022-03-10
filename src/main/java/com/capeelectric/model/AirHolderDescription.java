package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "AIR_HOLDERS_DESCRIPTION_TABLE")
public class AirHolderDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOLDERS_DESCRIPTION_ID")
	private Integer holderDescriptionId;

	@Column(name = "FLAG")
	private String flag;

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
		
	@Column(name = "MATERIAL_OFPARPET_HOLDEROB")
	private String materailOfParpetHolderOb;

	@Column(name = "MATERIAL_OFPARPET_HOLDERREM")
	private String materailOfParpetHolderRem;

	@Column(name = "TOTALPARPET_HOLDER_OBSERVATION")
	private Integer totalParpetHolderNoOb;

	@Column(name = "TOTALPARPET_HOLDER_REMARKS")
	private String totalParpetHolderNoRe;

	@Column(name = "PH_INSP_NO_OBS")
	private Integer parpetInspectionNoOb;

	@Column(name = "PH_INSP_NO_REM")
	private String parpetInspectionNoRe;

	@Column(name = "PH_INSP_PASSED_NO_OBS")
	private Integer parpetInspectionPassedNoOb;

	@Column(name = "PH_INSP_PASSED_NO_REM")
	private String parpetInspectionPassedNoRe;

	@Column(name = "PH_INSP_FAILED_NO_OBS")
	private Integer parpetInspectionFailedNoOb;

	@Column(name = "PH_INSP_FAILED_NO_REM")
	private String parpetInspectionFailedNoRe;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "airHolderDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirHolderList> airHolderList;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDescription;

	public Integer getHolderDescriptionId() {
		return holderDescriptionId;
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


	public String getTotalParpetHolderNoRe() {
		return totalParpetHolderNoRe;
	}

	public void setTotalParpetHolderNoRe(String totalParpetHolderNoRe) {
		this.totalParpetHolderNoRe = totalParpetHolderNoRe;
	}


	public String getParpetInspectionNoRe() {
		return parpetInspectionNoRe;
	}

	public void setParpetInspectionNoRe(String parpetInspectionNoRe) {
		this.parpetInspectionNoRe = parpetInspectionNoRe;
	}

	public String getParpetInspectionPassedNoRe() {
		return parpetInspectionPassedNoRe;
	}

	public void setParpetInspectionPassedNoRe(String parpetInspectionPassedNoRe) {
		this.parpetInspectionPassedNoRe = parpetInspectionPassedNoRe;
	}

	public String getParpetInspectionFailedNoRe() {
		return parpetInspectionFailedNoRe;
	}

	public void setParpetInspectionFailedNoRe(String parpetInspectionFailedNoRe) {
		this.parpetInspectionFailedNoRe = parpetInspectionFailedNoRe;
	}

	
	public LpsAirDiscription getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscription lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getTotalParpetHolderNoOb() {
		return totalParpetHolderNoOb;
	}

	public void setTotalParpetHolderNoOb(Integer totalParpetHolderNoOb) {
		this.totalParpetHolderNoOb = totalParpetHolderNoOb;
	}

	public Integer getParpetInspectionNoOb() {
		return parpetInspectionNoOb;
	}

	public void setParpetInspectionNoOb(Integer parpetInspectionNoOb) {
		this.parpetInspectionNoOb = parpetInspectionNoOb;
	}

	public Integer getParpetInspectionPassedNoOb() {
		return parpetInspectionPassedNoOb;
	}

	public void setParpetInspectionPassedNoOb(Integer parpetInspectionPassedNoOb) {
		this.parpetInspectionPassedNoOb = parpetInspectionPassedNoOb;
	}

	public Integer getParpetInspectionFailedNoOb() {
		return parpetInspectionFailedNoOb;
	}

	public void setParpetInspectionFailedNoOb(Integer parpetInspectionFailedNoOb) {
		this.parpetInspectionFailedNoOb = parpetInspectionFailedNoOb;
	}

	public List<AirHolderList> getAirHolderList() {
		return airHolderList;
	}

	public void setAirHolderList(List<AirHolderList> airHolderList) {
		this.airHolderList = airHolderList;
	}
	
	
}
