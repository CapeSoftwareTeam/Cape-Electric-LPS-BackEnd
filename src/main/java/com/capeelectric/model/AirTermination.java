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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "AIR_TERMINATION_DETAILS_TABLE")


@NamedQueries(value = {
		@NamedQuery(name = "AirTerminationLpsRepository.findByUserNameAndBasicLpsId", query = "select s from AirTermination s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "AirTerminationLpsRepository.findByBasicLpsId", query = "select s from AirTermination s where s.basicLpsId=:basicLpsId"),
})

public class AirTermination implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AIR_TERMINATION_DETAILS_ID")
	private Integer airTerminationId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;   
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "airTerminationDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<LpsAirDiscription> lpsAirDescription;

	
	public Integer getAirTerminationId() {
		return airTerminationId;
	}

	public void setAirTerminationId(Integer airTerminationId) {
		this.airTerminationId = airTerminationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getBasicLpsId() {
		return basicLpsId;
	}

	public void setBasicLpsId(Integer basicLpsId) {
		this.basicLpsId = basicLpsId;
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

	public List<LpsAirDiscription> getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(List<LpsAirDiscription> lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
