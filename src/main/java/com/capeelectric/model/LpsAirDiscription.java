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
@Table(name = "LPSAIRDESCRIPTION_TABLE")
public class LpsAirDiscription implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LPSAIRDESCRIPTION_ID")
	private Integer lpsAirDescId;
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "BUILDING_TYPE")
	private String buildingType;
	
	@Column(name = "BUILDING_LENGTH")
	private Integer buildingLength;
	
	@Column(name = "BUILDING_WIDTH")
	private Integer buildingWidth;
	
	@Column(name = "BUILDING_HEIGHT")
	private Integer buildingHeight;
	
	@Column(name = "PROTECTION_LEVEL")
	private String protectionLevel;
	
	@Column(name = "FLAG")
	private String flag;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LpsVerticalAirTermination>lpsVerticalAirTermination;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirClamps> airClamps;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirConnectors> airConnectors;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirExpansion> airExpansion;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirHolderDescription> airHolderDescription;
	 
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirMeshDescription> airMeshDescription;

	@JsonManagedReference
	@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AirBasicDescription> airBasicDescription;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "AIR_Termination_DETAILS_ID")
	private AirTermination airTerminationDetails;

	public Integer getLpsAirDescId() {
		return lpsAirDescId;
	}

	public void setLpsAirDescId(Integer lpsAirDescId) {
		this.lpsAirDescId = lpsAirDescId;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getBuildingCount() {
		return buildingCount;
	}

	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public Integer getBuildingLength() {
		return buildingLength;
	}

	public void setBuildingLength(Integer buildingLength) {
		this.buildingLength = buildingLength;
	}

	public Integer getBuildingWidth() {
		return buildingWidth;
	}

	public void setBuildingWidth(Integer buildingWidth) {
		this.buildingWidth = buildingWidth;
	}

	public Integer getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(Integer buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	public String getProtectionLevel() {
		return protectionLevel;
	}

	public void setProtectionLevel(String protectionLevel) {
		this.protectionLevel = protectionLevel;
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

	public List<AirBasicDescription> getAirBasicDescription() {
		return airBasicDescription;
	}

	public void setAirBasicDescription(List<AirBasicDescription> airBasicDescription) {
		this.airBasicDescription = airBasicDescription;
	}

	public AirTermination getAirTerminationDetails() {
		return airTerminationDetails;
	}

	public void setAirTerminationDetails(AirTermination airTerminationDetails) {
		this.airTerminationDetails = airTerminationDetails;
	}
	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
