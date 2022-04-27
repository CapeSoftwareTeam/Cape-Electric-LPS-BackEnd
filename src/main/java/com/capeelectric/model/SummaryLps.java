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
@Table(name = "SUMMARY_LPS_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "SummaryLpsRepository.findByUserNameAndBasicLpsId", query = "select s from SummaryLps s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "SummaryLpsRepository.findByBasicLpsIdAndFlag", query = "select s from SummaryLps s where s.basicLpsId=:basicLpsId and s.flag ='A'"),
})
public class SummaryLps implements Serializable  {
	
	private static final long serialVersionUID = -7161836502468880542L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUMMARY_LPS_ID")
	private Integer summaryLpsId;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "SUMMARY_DATE")
	private String summaryDate;
	
	@Column(name = "INSPECTED_YEAR")
	private Integer inspectedYear;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@Column(name = "FLAG")
	private String flag;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "summaryLps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SummaryLpsBuildings> summaryLpsBuildings;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "summaryLps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SummaryLpsDeclaration> summaryLpsDeclaration;

	public Integer getSummaryLpsId() {
		return summaryLpsId;
	}

	public void setSummaryLpsId(Integer summaryLpsId) {
		this.summaryLpsId = summaryLpsId;
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


	public String getSummaryDate() {
		return summaryDate;
	}

	public void setSummaryDate(String summaryDate) {
		this.summaryDate = summaryDate;
	}

	public Integer getInspectedYear() {
		return inspectedYear;
	}

	public void setInspectedYear(Integer inspectedYear) {
		this.inspectedYear = inspectedYear;
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

	public List<SummaryLpsBuildings> getSummaryLpsBuildings() {
		return summaryLpsBuildings;
	}

	public void setSummaryLpsBuildings(List<SummaryLpsBuildings> summaryLpsBuildings) {
		this.summaryLpsBuildings = summaryLpsBuildings;
	}

	public List<SummaryLpsDeclaration> getSummaryLpsDeclaration() {
		return summaryLpsDeclaration;
	}

	public void setSummaryLpsDeclaration(List<SummaryLpsDeclaration> summaryLpsDeclaration) {
		this.summaryLpsDeclaration = summaryLpsDeclaration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}	
	
}
