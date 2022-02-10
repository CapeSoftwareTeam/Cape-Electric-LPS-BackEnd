/**
 * 
 */
package com.capeelectric.model.remarks;

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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "EARTH_STUD_REPORT_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "EarthStudRemarksRepository.findByUserNameAndBasicLpsId", query = "select s from EarthStudRemarksReport s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "EarthStudRemarksRepository.findByBasicLpsId", query = "select s from EarthStudRemarksReport s where s.basicLpsId=:basicLpsId"),
})
public class EarthStudRemarksReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTH_STUD_REPORT_ID")
	private Integer earthStudReportId;
	
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
	@OneToMany(mappedBy = "earthStudRemarksReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EarthStudDescriptionRemarks> earthStudDescriptionRemarks;

	

	public Integer getEarthStudReportId() {
		return earthStudReportId;
	}

	public void setEarthStudReportId(Integer earthStudReportId) {
		this.earthStudReportId = earthStudReportId;
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

	public List<EarthStudDescriptionRemarks> getEarthStudDescriptionRemarks() {
		return earthStudDescriptionRemarks;
	}

	public void setEarthStudDescriptionRemarks(List<EarthStudDescriptionRemarks> earthStudDescriptionRemarks) {
		this.earthStudDescriptionRemarks = earthStudDescriptionRemarks;
	}

	

}
