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


@Entity
@Table(name = "LPSAIRDESCRIPTION_TABLE")

 
@NamedQueries(value = {
		@NamedQuery(name = "AirTerminationLpsRepository.findByUserNameAndBasicLpsId", query = "select s from BasicLps s where s.userName=:userName and s.basicLpsId=:basicLpsId"),
		@NamedQuery(name = "AirTerminationLpsRepository.findByBasicLpsId", query = "select s from BasicLps s where s.basicLpsId=:basicLpsId")
})
public class LpsAirDiscription implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LPSAIRDESCRIPTION_ID")
	private Integer lpsAirDescId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
    @Column(name = "CONNECTION_MADE_BRAZINGOBSERVATION")
	private String connectionMadeBraOb;
	
	@Column(name = "CONNECTION_MADE_BRAZINGREMARKS")
	private String connectionMadeBraRe;

	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDOBSERVATION")
	private String electricalEquipPlacedOb;

	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDREMARKS")
	private String electricalEquipPlacedRe;
	
	@Column(name = "COMBUSTABLE_PARTOBSERVATION")
	private String combustablePartOb;

	
	@Column(name = "COMBUSTABLE_PARTREMARKS")
	private String combustablePartRe;
	
	@Column(name = "TERMINATION_MESH_CONDUCTOROBSERVATION")
	private String terminationMeshConductorOb;


	@Column(name = "TERMINATION_MESH_CONDUCTORREMARKS")
	private String terminationMeshConductorRe;
	
	@Column(name = "BONDING_EQUIPOTENTIALOBSERVATION")
	private String bondingEquipotentialOb;


	@Column(name = "BONDING_EQUIPOTENTIALREMARKS")
	private String bondingEquipotentialRe;
   
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LpsVerticalAirTermination>lpsVerticalAirTermination;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirClamps> airClamps;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirConnectors> airConnectors;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirExpansion> airExpansion;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirHolderDescription> airHolderDescription;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirMeshDescription> airMeshDescription;

	 
	
	
	public String getCombustablePartOb() {
		return combustablePartOb;
	}

	public void setCombustablePartOb(String combustablePartOb) {
		this.combustablePartOb = combustablePartOb;
	}

	public Integer getLpsAirDescId() {
		return lpsAirDescId;
	}

	public void setLpsAirDescId(Integer lpsAirDescId) {
		this.lpsAirDescId = lpsAirDescId;
	}

	public String getConnectionMadeBraOb() {
		return connectionMadeBraOb;
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

	public String getCombustablePartRe() {
		return combustablePartRe;
	}

	public void setCombustablePartRe(String combustablePartRe) {
		this.combustablePartRe = combustablePartRe;
	}

	public void setConnectionMadeBraOb(String connectionMadeBraOb) {
		this.connectionMadeBraOb = connectionMadeBraOb;
	}

	public String getConnectionMadeBraRe() {
		return connectionMadeBraRe;
	}

	public void setConnectionMadeBraRe(String connectionMadeBraRe) {
		this.connectionMadeBraRe = connectionMadeBraRe;
	}

	public String getElectricalEquipPlacedOb() {
		return electricalEquipPlacedOb;
	}

	public void setElectricalEquipPlacedOb(String electricalEquipPlacedOb) {
		this.electricalEquipPlacedOb = electricalEquipPlacedOb;
	}

	public String getElectricalEquipPlacedRe() {
		return electricalEquipPlacedRe;
	}

	public void setElectricalEquipPlacedRe(String electricalEquipPlacedRe) {
		this.electricalEquipPlacedRe = electricalEquipPlacedRe;
	}

 

	public String getTerminationMeshConductorOb() {
		return terminationMeshConductorOb;
	}

	public void setTerminationMeshConductorOb(String terminationMeshConductorOb) {
		this.terminationMeshConductorOb = terminationMeshConductorOb;
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

	public String getTerminationMeshConductorRe() {
		return terminationMeshConductorRe;
	}

	public void setTerminationMeshConductorRe(String terminationMeshConductorRe) {
		this.terminationMeshConductorRe = terminationMeshConductorRe;
	}

	public String getBondingEquipotentialOb() {
		return bondingEquipotentialOb;
	}

	public void setBondingEquipotentialOb(String bondingEquipotentialOb) {
		this.bondingEquipotentialOb = bondingEquipotentialOb;
	}

	public String getBondingEquipotentialRe() {
		return bondingEquipotentialRe;
	}

	public void setBondingEquipotentialRe(String bondingEquipotentialRe) {
		this.bondingEquipotentialRe = bondingEquipotentialRe;
	}

	public List<LpsVerticalAirTermination> getLpsVerticalAirTermination() {
		return lpsVerticalAirTermination;
	}

	public void setLpsVerticalAirTermination(List<LpsVerticalAirTermination> lpsVerticalAirTermination) {
		this.lpsVerticalAirTermination = lpsVerticalAirTermination;
	}

	public List<AirClamps> getAirClamps() {
		return airClamps;
	}

	public void setAirClamps(List<AirClamps> airClamps) {
		this.airClamps = airClamps;
	}

	public List<AirConnectors> getAirConnectors() {
		return airConnectors;
	}

	public void setAirConnectors(List<AirConnectors> airConnectors) {
		this.airConnectors = airConnectors;
	}

	public List<AirExpansion> getAirExpansion() {
		return airExpansion;
	}

	public void setAirExpansion(List<AirExpansion> airExpansion) {
		this.airExpansion = airExpansion;
	}

	public List<AirHolderDescription> getAirHolderDescription() {
		return airHolderDescription;
	}

	public void setAirHolderDescription(List<AirHolderDescription> airHolderDescription) {
		this.airHolderDescription = airHolderDescription;
	}

	public List<AirMeshDescription> getAirMeshDescription() {
		return airMeshDescription;
	}

	public void setAirMeshDescription(List<AirMeshDescription> airMeshDescription) {
		this.airMeshDescription = airMeshDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
