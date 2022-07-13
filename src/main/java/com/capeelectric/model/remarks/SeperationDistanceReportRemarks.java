/**
 * 
 */
package com.capeelectric.model.remarks;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "SEPERATION_DISTANCE_REPORT_TABLE")
public class SeperationDistanceReportRemarks implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEPERATION_DISTANCE_REPORT_ID")
	private Integer seperationDistanceReportId;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "seperationDistanceReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SeperationDistanceDescriptionRemarks> seperationDistanceDescription;

	public Integer getSeperationDistanceReportId() {
		return seperationDistanceReportId;
	}

	public void setSeperationDistanceReportId(Integer seperationDistanceReportId) {
		this.seperationDistanceReportId = seperationDistanceReportId;
	}

	public Integer getBasicLpsId() {
		return basicLpsId;
	}

	public void setBasicLpsId(Integer basicLpsId) {
		this.basicLpsId = basicLpsId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<SeperationDistanceDescriptionRemarks> getSeperationDistanceDescription() {
		return seperationDistanceDescription;
	}

	public void setSeperationDistanceDescription(List<SeperationDistanceDescriptionRemarks> seperationDistanceDescription) {
		this.seperationDistanceDescription = seperationDistanceDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
