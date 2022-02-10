/**
 * 
 */
package com.capeelectric.model.remarks;

import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "LPSAIRDESCRIPTION_TABLE")
public class LpsAirDiscriptionRemarks implements Serializable {
	 
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
		
		@Column(name = "BUILDING_TYPE_OTHERS")
		private String buildingTypeOthers;
		
		@Column(name = "BUILDING_LENGTH")
		private Integer buildingLength;
		
		@Column(name = "BUILDING_WIDTH")
		private Integer buildingWidth;
		
		@Column(name = "BUILDING_HEIGHT")
		private Integer buildingHeight;
		
		@Column(name = "PROTRUSION_HEIGHT")
		private Integer protrusionHeight;
		
		@Column(name = "PROTECTION_LEVEL")
		private String protectionLevel;
		
		@Column(name = "AIR_BASIC_AVAILABILITY_REM")
		private String airBasicDescriptionAvailabilityRem;
		
		@Column(name = "AIR_VERTICAL_TERMINAL_AVAILABILITY_REM")
		private String verticalAirTerminationAvailabilityRem;
		
		@Column(name = "AIR_CLAMPS_AVAILABILITY_REM")
		private String airClampsAvailabilityRem;
		
		@Column(name = "AIR_CONNECTORS_AVAILABILITY_REM")
		private String airConnectorsAvailabilityRem;
		
		@Column(name = "AIR_EXPANSION_AVAILABILITY_REM")
		private String airExpansionAvailabilityRem;
		
		@Column(name = "AIR_HOLDER_AVAILABILITY_REM")
		private String airHolderDescriptionAvailabilityRem;
		
		@Column(name = "AIR_MESH_AVAILABILITY_REM")
		private String airMeshDescriptionAvailabilityRem;
		
		
		@Column(name = "FLAG")
		private String flag;
		
		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<LpsVerticalAirTerminationRemarks>lpsVerticalAirTermination;
		 
		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<AirClampsRemarks> airClamps;
		 
		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<AirConnectorsRemarks> airConnectors;
		 
		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<AirExpansionRemarks> airExpansion;
		 
		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<AirHoldersRemarks> airHolderDescription;
		 
		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<AirMeshRemarks> airMeshDescription;

		@JsonManagedReference
		@OneToMany(mappedBy = "lpsAirDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<AirBasicDescRemarks> airBasicDescription;
		
		@JsonBackReference
		@ManyToOne
		@JoinColumn(name = "AIR_TERMINATION_DETAILS_ID")
		private AirTerminationRemarks airTerminationDetails;

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

		public String getBuildingTypeOthers() {
			return buildingTypeOthers;
		}

		public void setBuildingTypeOthers(String buildingTypeOthers) {
			this.buildingTypeOthers = buildingTypeOthers;
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

		public Integer getProtrusionHeight() {
			return protrusionHeight;
		}

		public void setProtrusionHeight(Integer protrusionHeight) {
			this.protrusionHeight = protrusionHeight;
		}

		public String getProtectionLevel() {
			return protectionLevel;
		}

		public void setProtectionLevel(String protectionLevel) {
			this.protectionLevel = protectionLevel;
		}

		public String getAirBasicDescriptionAvailabilityRem() {
			return airBasicDescriptionAvailabilityRem;
		}

		public void setAirBasicDescriptionAvailabilityRem(String airBasicDescriptionAvailabilityRem) {
			this.airBasicDescriptionAvailabilityRem = airBasicDescriptionAvailabilityRem;
		}

		public String getVerticalAirTerminationAvailabilityRem() {
			return verticalAirTerminationAvailabilityRem;
		}

		public void setVerticalAirTerminationAvailabilityRem(String verticalAirTerminationAvailabilityRem) {
			this.verticalAirTerminationAvailabilityRem = verticalAirTerminationAvailabilityRem;
		}

		public String getAirClampsAvailabilityRem() {
			return airClampsAvailabilityRem;
		}

		public void setAirClampsAvailabilityRem(String airClampsAvailabilityRem) {
			this.airClampsAvailabilityRem = airClampsAvailabilityRem;
		}

		public String getAirConnectorsAvailabilityRem() {
			return airConnectorsAvailabilityRem;
		}

		public void setAirConnectorsAvailabilityRem(String airConnectorsAvailabilityRem) {
			this.airConnectorsAvailabilityRem = airConnectorsAvailabilityRem;
		}

		public String getAirExpansionAvailabilityRem() {
			return airExpansionAvailabilityRem;
		}

		public void setAirExpansionAvailabilityRem(String airExpansionAvailabilityRem) {
			this.airExpansionAvailabilityRem = airExpansionAvailabilityRem;
		}

		public String getAirHolderDescriptionAvailabilityRem() {
			return airHolderDescriptionAvailabilityRem;
		}

		public void setAirHolderDescriptionAvailabilityRem(String airHolderDescriptionAvailabilityRem) {
			this.airHolderDescriptionAvailabilityRem = airHolderDescriptionAvailabilityRem;
		}

		public String getAirMeshDescriptionAvailabilityRem() {
			return airMeshDescriptionAvailabilityRem;
		}

		public void setAirMeshDescriptionAvailabilityRem(String airMeshDescriptionAvailabilityRem) {
			this.airMeshDescriptionAvailabilityRem = airMeshDescriptionAvailabilityRem;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public List<LpsVerticalAirTerminationRemarks> getLpsVerticalAirTermination() {
			return lpsVerticalAirTermination;
		}

		public void setLpsVerticalAirTermination(List<LpsVerticalAirTerminationRemarks> lpsVerticalAirTermination) {
			this.lpsVerticalAirTermination = lpsVerticalAirTermination;
		}

		public List<AirClampsRemarks> getAirClamps() {
			return airClamps;
		}

		public void setAirClamps(List<AirClampsRemarks> airClamps) {
			this.airClamps = airClamps;
		}

		public List<AirConnectorsRemarks> getAirConnectors() {
			return airConnectors;
		}

		public void setAirConnectors(List<AirConnectorsRemarks> airConnectors) {
			this.airConnectors = airConnectors;
		}

		public List<AirExpansionRemarks> getAirExpansion() {
			return airExpansion;
		}

		public void setAirExpansion(List<AirExpansionRemarks> airExpansion) {
			this.airExpansion = airExpansion;
		}

		

		public List<AirHoldersRemarks> getAirHolderDescription() {
			return airHolderDescription;
		}

		public void setAirHolderDescription(List<AirHoldersRemarks> airHolderDescription) {
			this.airHolderDescription = airHolderDescription;
		}

		public List<AirMeshRemarks> getAirMeshDescription() {
			return airMeshDescription;
		}

		public void setAirMeshDescription(List<AirMeshRemarks> airMeshDescription) {
			this.airMeshDescription = airMeshDescription;
		}

		public List<AirBasicDescRemarks> getAirBasicDescription() {
			return airBasicDescription;
		}

		public void setAirBasicDescription(List<AirBasicDescRemarks> airBasicDescription) {
			this.airBasicDescription = airBasicDescription;
		}

		public AirTerminationRemarks getAirTerminationDetails() {
			return airTerminationDetails;
		}

		public void setAirTerminationDetails(AirTerminationRemarks airTerminationDetails) {
			this.airTerminationDetails = airTerminationDetails;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
}
