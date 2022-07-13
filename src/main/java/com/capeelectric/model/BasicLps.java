/**
 * 
 */
package com.capeelectric.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.Query;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Entity
@Table(name = "BASIC_LPS_TABLE")

@NamedQueries(value = {
		@NamedQuery(name = "BasicLpsRepository.findByUserNameAndBasicLpsId", query = "select s from BasicLps s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "BasicLpsRepository.findByBasicLpsId", query = "select s from BasicLps s where s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "BasicLpsRepository.findByClientName", query = "select s from BasicLps s where s.clientName=:clientName"),
		@NamedQuery(name = "BasicLpsRepository.findByClientNameAndStatus", query = "select s from BasicLps s where s.clientName=:clientName and s.status='Active'")
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
	
	@Column(name = "INDUSTRY_TYPE")
	private String industryType;
	
	@Column(name = "SOIL_RESISTIVITY")
	private String soilResistivity;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "COMPANY")
	private String company;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name = "MAIL_ID")
	private String mailId;
	
	@Column(name = "PREVIOUS_REPORT")
	private String availabilityOfPreviousReport;
	
	@Column(name = "ALL_STEPS_COMPLETED")
	private String allStepsCompleted;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	

	public String getSoilResistivity() {
		return soilResistivity;
	}

	public void setSoilResistivity(String soilResistivity) {
		this.soilResistivity = soilResistivity;
	}

	
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

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getAvailabilityOfPreviousReport() {
		return availabilityOfPreviousReport;
	}

	public void setAvailabilityOfPreviousReport(String availabilityOfPreviousReport) {
		this.availabilityOfPreviousReport = availabilityOfPreviousReport;
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

	public String getAllStepsCompleted() {
		return allStepsCompleted;
	}

	public void setAllStepsCompleted(String allStepsCompleted) {
		this.allStepsCompleted = allStepsCompleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
