/**
 * 
 */
package com.capeelectric.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
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
@Table(name = "BASIC_LPS_TABLE")

@NamedQueries(value = {
		@NamedQuery(name = "BasicLpsRepository.findByUserNameAndBasicLpsId", query = "select s from BasicLps s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "BasicLpsRepository.findByBasicLpsId", query = "select s from BasicLps s where s.basicLpsId=:basicLpsId")
})
public class BasicLps implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "PMC_NAME")
	private String pmcName;
	
	@Column(name = "CONSULTANT_NAME")
	private String consultantName;
	
	@Column(name = "CONTRACTOR_NAME")
	private String contractorName;
	
	@Column(name = "DEALER_CONTRACTOR")
	private String dealerContractorName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "INSTALLATION_CONTRACTOR")
	private String installationContractor;
	
	@Column(name = "INDUSTRY_TYPE")
	private String industryType;
	
	@Column(name = "BUILDING_TYPE")
	private String buildingType;
	
	@Column(name = "BUILDING_LENGTH")
	private String buildingLength;
	
	@Column(name = "BUILDING_WIDTH")
	private String buildingWidth;
	
	@Column(name = "BUILDING_HEIGHT")
	private String buildingHeight;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	
	@JsonManagedReference
	@OneToMany(mappedBy = "basicLps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BasicLpsDescription> basicLpsDescription;

	
	public Integer getBasicLpsId() {
		return basicLpsId;
	}

	public void setBasicLpsId(Integer basicLpsId) {
		this.basicLpsId = basicLpsId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPmcName() {
		return pmcName;
	}

	public void setPmcName(String pmcName) {
		this.pmcName = pmcName;
	}

	public String getConsultantName() {
		return consultantName;
	}

	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getDealerContractorName() {
		return dealerContractorName;
	}

	public void setDealerContractorName(String dealerContractorName) {
		this.dealerContractorName = dealerContractorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInstallationContractor() {
		return installationContractor;
	}

	public void setInstallationContractor(String installationContractor) {
		this.installationContractor = installationContractor;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getBuildingLength() {
		return buildingLength;
	}

	public void setBuildingLength(String buildingLength) {
		this.buildingLength = buildingLength;
	}

	public String getBuildingWidth() {
		return buildingWidth;
	}

	public void setBuildingWidth(String buildingWidth) {
		this.buildingWidth = buildingWidth;
	}

	public String getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(String buildingHeight) {
		this.buildingHeight = buildingHeight;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<BasicLpsDescription> getBasicLpsDescription() {
		return basicLpsDescription;
	}

	public void setBasicLpsDescription(Set<BasicLpsDescription> basicLpsDescription) {
		this.basicLpsDescription = basicLpsDescription;
	}
	
	

}
