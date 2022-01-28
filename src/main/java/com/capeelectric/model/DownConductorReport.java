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
@Table(name = "DOWN_CONDUCTOR_REPORT_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "DownConductorRepository.findByUserNameAndBasicLpsId", query = "select s from DownConductorReport s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "DownConductorRepository.findByBasicLpsId", query = "select s from DownConductorReport s where s.basicLpsId=:basicLpsId"),
})
public class DownConductorReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWN_CONDUCTOR_REPORT_ID")
	private Integer downConductorReportId;
	
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
	@OneToMany(mappedBy = "downConductorReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DownConductorDescription>downConductorDescription;

	public Integer getDownConductorReportId() {
		return downConductorReportId;
	}

	public void setDownConductorReportId(Integer downConductorReportId) {
		this.downConductorReportId = downConductorReportId;
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

	public List<DownConductorDescription> getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(List<DownConductorDescription> downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
