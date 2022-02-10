/**
 * 
 */
package com.capeelectric.model.remarks;

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

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "AIR_HOLDERS_DESCRIPTION_TABLE")
public class AirHoldersRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOLDERS_DESCRIPTION_ID")
	private Integer holderDescriptionId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTION_REMARKS")
	private String physicalInspectionRe;

	@Column(name = "CONDUCTORHOLDER_FLATSURAFACE_REMARKS")
	private String conductorHolderFlatSurfaceRe;

	@Column(name = "CODUCTOR_HOLDER_REMARKS")
	private String conductorHolderRe;

	@Column(name = "MATERIAL_OFPARPET_HOLDERREM")
	private String materailOfParpetHolderRem;

	@Column(name = "TOTALPARPET_HOLDER_REMARKS")
	private String totalParpetHolderNoRe;

	@Column(name = "PH_INSP_NO_REM")
	private String parpetInspectionNoRe;

	@Column(name = "PH_INSP_PASSED_NO_REM")
	private String parpetInspectionPassedNoRe;

	@Column(name = "PH_INSP_FAILED_NO_REM")
	private String parpetInspectionFailedNoRe;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "airHolderDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirHoldersListRemarks> airHolderList;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscriptionRemarks lpsAirDescription;

	public Integer getHolderDescriptionId() {
		return holderDescriptionId;
	}

	public void setHolderDescriptionId(Integer holderDescriptionId) {
		this.holderDescriptionId = holderDescriptionId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getConductorHolderFlatSurfaceRe() {
		return conductorHolderFlatSurfaceRe;
	}

	public void setConductorHolderFlatSurfaceRe(String conductorHolderFlatSurfaceRe) {
		this.conductorHolderFlatSurfaceRe = conductorHolderFlatSurfaceRe;
	}

	public String getConductorHolderRe() {
		return conductorHolderRe;
	}

	public void setConductorHolderRe(String conductorHolderRe) {
		this.conductorHolderRe = conductorHolderRe;
	}

	public String getMaterailOfParpetHolderRem() {
		return materailOfParpetHolderRem;
	}

	public void setMaterailOfParpetHolderRem(String materailOfParpetHolderRem) {
		this.materailOfParpetHolderRem = materailOfParpetHolderRem;
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

	public List<AirHoldersListRemarks> getAirHolderList() {
		return airHolderList;
	}

	public void setAirHolderList(List<AirHoldersListRemarks> airHolderList) {
		this.airHolderList = airHolderList;
	}

	public LpsAirDiscriptionRemarks getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscriptionRemarks lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
