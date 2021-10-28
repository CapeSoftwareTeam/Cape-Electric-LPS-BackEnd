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
@Table(name = "EARTHING_INSPECTION_TABLE")
public class EarthingInspection implements Serializable {

	
	
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_INSPECTION_ID")
	private Integer earthingInspectionId;
	
	@Column(name = "INSPECTION_TYPE")
	private String inspectionType;
	
	@Column(name = "INSPECTED_NO_OB")
	private String inspectedNoOb;
	
	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;
	
	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private String inspectedPassedNoOb;
	
	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectedPassedNoRem;
	
	@Column(name = "INSPECTIONFAILED_NO_OB")
	private String inspectedFailedNo;
	
	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectedFailedRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthingInspectionId() {
		return earthingInspectionId;
	}

	public void setEarthingInspectionId(Integer earthingInspectionId) {
		this.earthingInspectionId = earthingInspectionId;
	}

	public String getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
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

	public String getInspectedPassedNoOb() {
		return inspectedPassedNoOb;
	}

	public void setInspectedPassedNoOb(String inspectedPassedNoOb) {
		this.inspectedPassedNoOb = inspectedPassedNoOb;
	}

	public String getInspectedPassedNoRem() {
		return inspectedPassedNoRem;
	}

	public void setInspectedPassedNoRem(String inspectedPassedNoRem) {
		this.inspectedPassedNoRem = inspectedPassedNoRem;
	}

	public String getInspectedFailedNo() {
		return inspectedFailedNo;
	}

	public void setInspectedFailedNo(String inspectedFailedNo) {
		this.inspectedFailedNo = inspectedFailedNo;
	}

	public String getInspectedFailedRem() {
		return inspectedFailedRem;
	}

	public void setInspectedFailedRem(String inspectedFailedRem) {
		this.inspectedFailedRem = inspectedFailedRem;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}
	
	
}
