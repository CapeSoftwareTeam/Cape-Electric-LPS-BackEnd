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
@Table(name = "LPSVERTICALAIRTERMINAL_TABLE")

public class LpsVerticalAirTermination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LPSVERTICALAIRTERMINAL_ID")
	private Integer lpsVerticalAirTerminationId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTIONOBSERVATION")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTIONREMARKS")
	private String physicalInspectionRe;

	@Column(name = "TOTAL_NUMBEROBSERVATION")
	private Integer totalNumberOb;

	@Column(name = "TOTAL_NUMBERREMARKS")
	private String totalNumberRe;

	@Column(name = "INSP_NO_OBS")
	private Integer inspNoOb;

	@Column(name = "INSP_NO_REM")
	private String inspNoRe;

	@Column(name = "INSP_PASSED_NO_OBS")
	private Integer inspPassedNoOb;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspPassedNoRe;

	@Column(name = "INSP_FAILED_NO_OBS")
	private Integer inspFaileddNoOb;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspFaileddNoRe;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsVerticalAirTermination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VerticalAirTerminationList> verticalAirTerminationList;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDescription;

	public Integer getLpsVerticalAirTerminationId() {
		return lpsVerticalAirTerminationId;
	}	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public void setLpsVerticalAirTerminationId(Integer lpsVerticalAirTerminationId) {
		this.lpsVerticalAirTerminationId = lpsVerticalAirTerminationId;
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

	public String getTotalNumberRe() {
		return totalNumberRe;
	}

	public void setTotalNumberRe(String totalNumberRe) {
		this.totalNumberRe = totalNumberRe;
	}

	public String getInspNoRe() {
		return inspNoRe;
	}

	public void setInspNoRe(String inspNoRe) {
		this.inspNoRe = inspNoRe;
	}

	public String getInspPassedNoRe() {
		return inspPassedNoRe;
	}

	public void setInspPassedNoRe(String inspPassedNoRe) {
		this.inspPassedNoRe = inspPassedNoRe;
	}

	public String getInspFaileddNoRe() {
		return inspFaileddNoRe;
	}

	public void setInspFaileddNoRe(String inspFaileddNoRe) {
		this.inspFaileddNoRe = inspFaileddNoRe;
	}

	public LpsAirDiscription getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscription lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public Integer getTotalNumberOb() {
		return totalNumberOb;
	}

	public void setTotalNumberOb(Integer totalNumberOb) {
		this.totalNumberOb = totalNumberOb;
	}

	public Integer getInspNoOb() {
		return inspNoOb;
	}

	public void setInspNoOb(Integer inspNoOb) {
		this.inspNoOb = inspNoOb;
	}

	public Integer getInspPassedNoOb() {
		return inspPassedNoOb;
	}

	public void setInspPassedNoOb(Integer inspPassedNoOb) {
		this.inspPassedNoOb = inspPassedNoOb;
	}

	public Integer getInspFaileddNoOb() {
		return inspFaileddNoOb;
	}

	public void setInspFaileddNoOb(Integer inspFaileddNoOb) {
		this.inspFaileddNoOb = inspFaileddNoOb;
	}

	public List<VerticalAirTerminationList> getVerticalAirTerminationList() {
		return verticalAirTerminationList;
	}

	public void setVerticalAirTerminationList(List<VerticalAirTerminationList> verticalAirTerminationList) {
		this.verticalAirTerminationList = verticalAirTerminationList;
	}
	

}
