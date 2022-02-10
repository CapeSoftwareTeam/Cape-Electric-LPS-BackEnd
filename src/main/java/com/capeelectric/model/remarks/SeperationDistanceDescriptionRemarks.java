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
@Table(name = "SEPERATION_DISTANCE_TABLE")
public class SeperationDistanceDescriptionRemarks implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEPERATION_DISTANCE_ID")
	private Integer seperationDistanceId;
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "FLAG")
	private String flag;	
	
	@Column(name = "CALCULATED_SEPERATION_DISTANCE_REM")
	private String calculatedSeperationDistanceRem;
		
	@JsonManagedReference
	@OneToMany(mappedBy = "seperationDistanceDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SeparateDistanceRemarks> separateDistance;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "seperationDistanceDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SeparateDistanceDownConductRemarks> separateDistanceDownConductors;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SEPERATION_DISTANCE_REPORT_ID")
	private SeperationDistanceReportRemarks seperationDistanceReport;

	public Integer getSeperationDistanceId() {
		return seperationDistanceId;
	}

	public void setSeperationDistanceId(Integer seperationDistanceId) {
		this.seperationDistanceId = seperationDistanceId;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public Integer getBuildingCount() {
		return buildingCount;
	}

	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCalculatedSeperationDistanceRem() {
		return calculatedSeperationDistanceRem;
	}

	public void setCalculatedSeperationDistanceRem(String calculatedSeperationDistanceRem) {
		this.calculatedSeperationDistanceRem = calculatedSeperationDistanceRem;
	}

	public List<SeparateDistanceRemarks> getSeparateDistance() {
		return separateDistance;
	}

	public void setSeparateDistance(List<SeparateDistanceRemarks> separateDistance) {
		this.separateDistance = separateDistance;
	}

	public List<SeparateDistanceDownConductRemarks> getSeparateDistanceDownConductors() {
		return separateDistanceDownConductors;
	}

	public void setSeparateDistanceDownConductors(List<SeparateDistanceDownConductRemarks> separateDistanceDownConductors) {
		this.separateDistanceDownConductors = separateDistanceDownConductors;
	}

	public SeperationDistanceReportRemarks getSeperationDistanceReport() {
		return seperationDistanceReport;
	}

	public void setSeperationDistanceReport(SeperationDistanceReportRemarks seperationDistanceReport) {
		this.seperationDistanceReport = seperationDistanceReport;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
