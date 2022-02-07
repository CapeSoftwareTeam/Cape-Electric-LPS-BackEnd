/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class SeperationDistanceDescription implements Serializable {
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
	
	@Column(name = "CALCULATED_SEPERATION_DISTANCE_OB")
	private String calculatedSeperationDistanceOb;
	
	@Column(name = "CALCULATED_SEPERATION_DISTANCE_REM")
	private String calculatedSeperationDistanceRem;
		
	@JsonManagedReference
	@OneToMany(mappedBy = "seperationDistanceDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SeparateDistance> separateDistance;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SEPERATION_DISTANCE_REPORT_ID")
	private SeperationDistanceReport seperationDistanceReport;


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

	public SeperationDistanceReport getSeperationDistanceReport() {
		return seperationDistanceReport;
	}

	public void setSeperationDistanceReport(SeperationDistanceReport seperationDistanceReport) {
		this.seperationDistanceReport = seperationDistanceReport;
	}

	public List<SeparateDistance> getSeparateDistance() {
		return separateDistance;
	}

	public void setSeparateDistance(List<SeparateDistance> separateDistance) {
		this.separateDistance = separateDistance;
	}

	public String getCalculatedSeperationDistanceOb() {
		return calculatedSeperationDistanceOb;
	}

	public void setCalculatedSeperationDistanceOb(String calculatedSeperationDistanceOb) {
		this.calculatedSeperationDistanceOb = calculatedSeperationDistanceOb;
	}

	public String getCalculatedSeperationDistanceRem() {
		return calculatedSeperationDistanceRem;
	}

	public void setCalculatedSeperationDistanceRem(String calculatedSeperationDistanceRem) {
		this.calculatedSeperationDistanceRem = calculatedSeperationDistanceRem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
