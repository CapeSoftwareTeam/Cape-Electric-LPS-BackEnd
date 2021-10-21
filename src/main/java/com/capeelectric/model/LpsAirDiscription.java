package com.capeelectric.model;

import java.io.Serializable;
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

 
public class LpsAirDiscription implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LPSAIRDESCRIPTION_ID")
	private Integer lpsAirDescId;
	
	@Column(name = "CONNECTION_MADE_BRAZINGOBSERVATION")
	private String connectionMadeBraOb;
	
	@Column(name = "CONNECTION_MADE_BRAZINGREMARKS")
	private String connectionMadeBraRe;
	
	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDOBSERVATION")
	private String electricalEquipPlacedOb;
	
	@Column(name = "ELECTRICAL_EQUIPMENT_PLACEDREMARKS")
	private String electricalEquipPlacedRe;
	
	@Column(name = "COMBUSTABLE_PARTOBSERVATION")
	private String combastablePartOb;
	
	@Column(name = "COMBUSTABLE_PARTREMARKS")
	private String combastablePartRe;
	
	@Column(name = "TERMINATION_MESH_CONDUCTOROBSERVATION")
	private String terminationMeshConductorOb;
	
	@Column(name = "TERMINATION_MESH_CONDUCTORREMARKS")
	private String terminationMeshConductorRe;
	
	@Column(name = "BONDING_EQUIPOTENTIALOBSERVATION")
	private String bondingEquipotentialOb;
	
	@Column(name = "BONDING_EQUIPOTENTIALREMARKS VARCHAR")
	private String bondingEquipotentialRe;
	
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

	 

	public Integer getLpsAirDescId() {
		return lpsAirDescId;
	}

	public void setLpsAirDescId(Integer lpsAirDescId) {
		this.lpsAirDescId = lpsAirDescId;
	}

	public String getConnectionMadeBraOb() {
		return connectionMadeBraOb;
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

	public String getCombastablePartOb() {
		return combastablePartOb;
	}

	public void setCombastablePartOb(String combastablePartOb) {
		this.combastablePartOb = combastablePartOb;
	}

	public String getCombastablePartRe() {
		return combastablePartRe;
	}

	public void setCombastablePartRe(String combastablePartRe) {
		this.combastablePartRe = combastablePartRe;
	}

	public String getTerminationMeshConductorOb() {
		return terminationMeshConductorOb;
	}

	public void setTerminationMeshConductorOb(String terminationMeshConductorOb) {
		this.terminationMeshConductorOb = terminationMeshConductorOb;
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
