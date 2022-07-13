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
@Table(name = "LPSVERTICALAIRTERMINAL_TABLE")
public class LpsVerticalAirTerminationRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LPSVERTICALAIRTERMINAL_ID")
	private Integer lpsVerticalAirTerminationId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "PHYSICAL_INSPECTIONREMARKS")
	private String physicalInspectionRe;

	@Column(name = "TOTAL_NUMBERREMARKS")
	private String totalNumberRe;

	@Column(name = "INSP_NO_REM")
	private String inspNoRe;

	@Column(name = "INSP_PASSED_NO_REM")
	private String inspPassedNoRe;

	@Column(name = "INSP_FAILED_NO_REM")
	private String inspFaileddNoRe;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsVerticalAirTermination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VerticalAirTerminationListRemarks> verticalAirTerminationList;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscriptionRemarks lpsAirDescription;

	public Integer getLpsVerticalAirTerminationId() {
		return lpsVerticalAirTerminationId;
	}

	public void setLpsVerticalAirTerminationId(Integer lpsVerticalAirTerminationId) {
		this.lpsVerticalAirTerminationId = lpsVerticalAirTerminationId;
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

	public List<VerticalAirTerminationListRemarks> getVerticalAirTerminationList() {
		return verticalAirTerminationList;
	}

	public void setVerticalAirTerminationList(List<VerticalAirTerminationListRemarks> verticalAirTerminationList) {
		this.verticalAirTerminationList = verticalAirTerminationList;
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
