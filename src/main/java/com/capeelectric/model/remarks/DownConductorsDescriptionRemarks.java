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
@Table(name = "DOWNCONDUCTORDESCRIPTION_TABLE")
public class DownConductorsDescriptionRemarks implements Serializable  {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private Integer downConduDescId;
	
	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@Column(name = "BUILDING_NUMBER")
	private Integer buildingNumber;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "BI_METALLICISSUE_REM")
	private String biMetallicIssueRem;
	
	@Column(name = "WARNINGNOTICE_GROUNDLEVEL_REM")
	private String warningNoticeGroundLevelRem;
		
	@Column(name = "INSULATION_PRESENCE_REM")
	private String insulationPresenceRem;

	@Column(name = "NOPOWER_DOWNCONDUCTOR_REM")
	private String noPowerDownConductorRem;
	
	@Column(name = "CONNECTIONSMADE_BY_BRAZING_REM")
	private String connectMadeBrazingRem;
	 
	@Column(name = "CHEMICAL_SPRINKLER_REM")
	private String chemicalSprinklerRem;
	
	@Column(name = "COMBUSTIBLEMATERIAL_WALL_REM")
	private String cobustMaterialWallRem;
	
	@Column(name = "BRIDGING_DESC_AVAILABILITY_REM")
	private String bridgingDescriptionAvailabilityRem;
	
	@Column(name = "HOLDER_AVAILABILITY_REM")
	private String holderAvailabilityRem;
	
	@Column(name = "CONNECTORS_AVAILABILITY_REM")
	private String connectorsAvailabilityRem;
	
	@Column(name = "LIGHTNING_COUNTERS_AVAILABILITY_REM")
	private String lightningCounterAvailabilityRem;
	
	@Column(name = "TESTING_JOINT_AVAILABILITY_REM")
	private String testingJointAvailabilityRem;
	
	@Column(name = "DOWNCONDUCTOR_AVAILABILITY_REM")
	private String downConductorAvailabilityRem;
	
	@Column(name = "DOWNCONDUCTOR_TESTING_AVAILABILITY_REM")
	private String downConductorTestingAvailabilityRem;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BridgingDescriptionRemarks>bridgingDescription;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HolderRemarks> holder;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ConnectorRemarks> connectors;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LightningCounterRemarks> lightningCounter;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestingJointRemarks> testingJoint;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DownConductorRemarks> downConductor;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DownConductorTestingRemarks> downConductorTesting;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWN_CONDUCTOR_REPORT_ID")
	private DownConductorReportRemarks downConductorReport;

	public Integer getDownConduDescId() {
		return downConduDescId;
	}

	public void setDownConduDescId(Integer downConduDescId) {
		this.downConduDescId = downConduDescId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public Integer getBuildingCount() {
		return buildingCount;
	}

	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBiMetallicIssueRem() {
		return biMetallicIssueRem;
	}

	public void setBiMetallicIssueRem(String biMetallicIssueRem) {
		this.biMetallicIssueRem = biMetallicIssueRem;
	}

	public String getWarningNoticeGroundLevelRem() {
		return warningNoticeGroundLevelRem;
	}

	public void setWarningNoticeGroundLevelRem(String warningNoticeGroundLevelRem) {
		this.warningNoticeGroundLevelRem = warningNoticeGroundLevelRem;
	}

	public String getInsulationPresenceRem() {
		return insulationPresenceRem;
	}

	public void setInsulationPresenceRem(String insulationPresenceRem) {
		this.insulationPresenceRem = insulationPresenceRem;
	}

	public String getNoPowerDownConductorRem() {
		return noPowerDownConductorRem;
	}

	public void setNoPowerDownConductorRem(String noPowerDownConductorRem) {
		this.noPowerDownConductorRem = noPowerDownConductorRem;
	}

	public String getConnectMadeBrazingRem() {
		return connectMadeBrazingRem;
	}

	public void setConnectMadeBrazingRem(String connectMadeBrazingRem) {
		this.connectMadeBrazingRem = connectMadeBrazingRem;
	}

	public String getChemicalSprinklerRem() {
		return chemicalSprinklerRem;
	}

	public void setChemicalSprinklerRem(String chemicalSprinklerRem) {
		this.chemicalSprinklerRem = chemicalSprinklerRem;
	}

	public String getCobustMaterialWallRem() {
		return cobustMaterialWallRem;
	}

	public void setCobustMaterialWallRem(String cobustMaterialWallRem) {
		this.cobustMaterialWallRem = cobustMaterialWallRem;
	}

	public String getBridgingDescriptionAvailabilityRem() {
		return bridgingDescriptionAvailabilityRem;
	}

	public void setBridgingDescriptionAvailabilityRem(String bridgingDescriptionAvailabilityRem) {
		this.bridgingDescriptionAvailabilityRem = bridgingDescriptionAvailabilityRem;
	}

	public String getHolderAvailabilityRem() {
		return holderAvailabilityRem;
	}

	public void setHolderAvailabilityRem(String holderAvailabilityRem) {
		this.holderAvailabilityRem = holderAvailabilityRem;
	}

	public String getConnectorsAvailabilityRem() {
		return connectorsAvailabilityRem;
	}

	public void setConnectorsAvailabilityRem(String connectorsAvailabilityRem) {
		this.connectorsAvailabilityRem = connectorsAvailabilityRem;
	}

	public String getLightningCounterAvailabilityRem() {
		return lightningCounterAvailabilityRem;
	}

	public void setLightningCounterAvailabilityRem(String lightningCounterAvailabilityRem) {
		this.lightningCounterAvailabilityRem = lightningCounterAvailabilityRem;
	}

	public String getTestingJointAvailabilityRem() {
		return testingJointAvailabilityRem;
	}

	public void setTestingJointAvailabilityRem(String testingJointAvailabilityRem) {
		this.testingJointAvailabilityRem = testingJointAvailabilityRem;
	}

	public String getDownConductorAvailabilityRem() {
		return downConductorAvailabilityRem;
	}

	public void setDownConductorAvailabilityRem(String downConductorAvailabilityRem) {
		this.downConductorAvailabilityRem = downConductorAvailabilityRem;
	}

	public String getDownConductorTestingAvailabilityRem() {
		return downConductorTestingAvailabilityRem;
	}

	public void setDownConductorTestingAvailabilityRem(String downConductorTestingAvailabilityRem) {
		this.downConductorTestingAvailabilityRem = downConductorTestingAvailabilityRem;
	}

	public List<BridgingDescriptionRemarks> getBridgingDescription() {
		return bridgingDescription;
	}

	public void setBridgingDescription(List<BridgingDescriptionRemarks> bridgingDescription) {
		this.bridgingDescription = bridgingDescription;
	}

	public List<HolderRemarks> getHolder() {
		return holder;
	}

	public void setHolder(List<HolderRemarks> holder) {
		this.holder = holder;
	}

	public List<ConnectorRemarks> getConnectors() {
		return connectors;
	}

	public void setConnectors(List<ConnectorRemarks> connectors) {
		this.connectors = connectors;
	}

	public List<LightningCounterRemarks> getLightningCounter() {
		return lightningCounter;
	}

	public void setLightningCounter(List<LightningCounterRemarks> lightningCounter) {
		this.lightningCounter = lightningCounter;
	}

	public List<TestingJointRemarks> getTestingJoint() {
		return testingJoint;
	}

	public void setTestingJoint(List<TestingJointRemarks> testingJoint) {
		this.testingJoint = testingJoint;
	}

	public List<DownConductorRemarks> getDownConductor() {
		return downConductor;
	}

	public void setDownConductor(List<DownConductorRemarks> downConductor) {
		this.downConductor = downConductor;
	}

	public List<DownConductorTestingRemarks> getDownConductorTesting() {
		return downConductorTesting;
	}

	public void setDownConductorTesting(List<DownConductorTestingRemarks> downConductorTesting) {
		this.downConductorTesting = downConductorTesting;
	}

	public DownConductorReportRemarks getDownConductorReport() {
		return downConductorReport;
	}

	public void setDownConductorReport(DownConductorReportRemarks downConductorReport) {
		this.downConductorReport = downConductorReport;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
