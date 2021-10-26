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

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */


@Entity
@Table(name = "DOWNCONDUCTORDESCRIPTION_TABLE")
public class DownConductorDescription  implements Serializable  {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private Integer downConduDescId;
	
	@Column(name = "BASIC_LPS_ID")
	private Integer basicLpsId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "BI_METALLICISSUE_OB")
	private String biMetallicIssueOb;
	
	@Column(name = "BI_METALLICISSUE_REM")
	private String biMetallicIssueRem;
	
	@Column(name = "WARNINGNOTICE_GROUNDLEVEL_OB")
	private String warningNoticeGroundLevelOb;
	
	@Column(name = "WARNINGNOTICE_GROUNDLEVEL_REM")
	private String warningNoticeGroundLevelRem;
	
	@Column(name = "NOPOWER_DOWNCONDUCTOR_OB")
	private String noPowerDownConductorOb;

	@Column(name = "NOPOWER_DOWNCONDUCTOR_REM")
	private String noPowerDownConductorRem;

	@Column(name = "CONNECTIONSMADE_BY_BRAZING_OB")
	private String connectMadeBrazingOb;
	
	@Column(name = "CONNECTIONSMADE_BY_BRAZING_REM")
	private String connectMadeBrazingRem;
	 
	@Column(name = "CHEMICAL_SPRINKLER_OB")
	private String chemicalSprinklerOb;
	 
	@Column(name = "CHEMICAL_SPRINKLER_REM")
	private String chemicalSprinklerRem;
	
	 
	@Column(name = "COMBUSTIBLEMATERIAL_WALL_OB")
	private String cobustMaterialWallOB;
	
	@Column(name = "COMBUSTIBLEMATERIAL_WALL_REM")
	private String cobustMaterialWallRem;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BridzingDescription>bridzingDescription;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Holder>holder;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Connectors>connectors;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LightningCounter>lightningCounter;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestingJoint>testingJoint;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "downConductorDescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DownConductor> downConductor;

	public Integer getDownConduDescId() {
		return downConduDescId;
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




	public List<BridzingDescription> getBridzingDescription() {
		return bridzingDescription;
	}




	public void setBridzingDescription(List<BridzingDescription> bridzingDescription) {
		this.bridzingDescription = bridzingDescription;
	}




	public List<Holder> getHolder() {
		return holder;
	}




	public void setHolder(List<Holder> holder) {
		this.holder = holder;
	}




	public List<Connectors> getConnectors() {
		return connectors;
	}




	public void setConnectors(List<Connectors> connectors) {
		this.connectors = connectors;
	}




	public List<LightningCounter> getLightningCounter() {
		return lightningCounter;
	}




	public void setLightningCounter(List<LightningCounter> lightningCounter) {
		this.lightningCounter = lightningCounter;
	}




	public List<TestingJoint> getTestingJoint() {
		return testingJoint;
	}




	public void setTestingJoint(List<TestingJoint> testingJoint) {
		this.testingJoint = testingJoint;
	}




	public void setDownConduDescId(Integer downConduDescId) {
		this.downConduDescId = downConduDescId;
	}

	public String getBiMetallicIssueOb() {
		return biMetallicIssueOb;
	}

	public void setBiMetallicIssueOb(String biMetallicIssueOb) {
		this.biMetallicIssueOb = biMetallicIssueOb;
	}

	public String getBiMetallicIssueRem() {
		return biMetallicIssueRem;
	}

	public void setBiMetallicIssueRem(String biMetallicIssueRem) {
		this.biMetallicIssueRem = biMetallicIssueRem;
	}

	public String getWarningNoticeGroundLevelOb() {
		return warningNoticeGroundLevelOb;
	}

	public void setWarningNoticeGroundLevelOb(String warningNoticeGroundLevelOb) {
		this.warningNoticeGroundLevelOb = warningNoticeGroundLevelOb;
	}

	public String getWarningNoticeGroundLevelRem() {
		return warningNoticeGroundLevelRem;
	}

	public void setWarningNoticeGroundLevelRem(String warningNoticeGroundLevelRem) {
		this.warningNoticeGroundLevelRem = warningNoticeGroundLevelRem;
	}

	public String getNoPowerDownConductorOb() {
		return noPowerDownConductorOb;
	}

	public void setNoPowerDownConductorOb(String noPowerDownConductorOb) {
		this.noPowerDownConductorOb = noPowerDownConductorOb;
	}

	public String getNoPowerDownConductorRem() {
		return noPowerDownConductorRem;
	}

	public void setNoPowerDownConductorRem(String noPowerDownConductorRem) {
		this.noPowerDownConductorRem = noPowerDownConductorRem;
	}

	public String getConnectMadeBrazingOb() {
		return connectMadeBrazingOb;
	}

	public void setConnectMadeBrazingOb(String connectMadeBrazingOb) {
		this.connectMadeBrazingOb = connectMadeBrazingOb;
	}

	public String getConnectMadeBrazingRem() {
		return connectMadeBrazingRem;
	}

	public void setConnectMadeBrazingRem(String connectMadeBrazingRem) {
		this.connectMadeBrazingRem = connectMadeBrazingRem;
	}

	public String getChemicalSprinklerOb() {
		return chemicalSprinklerOb;
	}

	public void setChemicalSprinklerOb(String chemicalSprinklerOb) {
		this.chemicalSprinklerOb = chemicalSprinklerOb;
	}

	public String getChemicalSprinklerRem() {
		return chemicalSprinklerRem;
	}

	public void setChemicalSprinklerRem(String chemicalSprinklerRem) {
		this.chemicalSprinklerRem = chemicalSprinklerRem;
	}

	public String getCobustMaterialWallOB() {
		return cobustMaterialWallOB;
	}

	public void setCobustMaterialWallOB(String cobustMaterialWallOB) {
		this.cobustMaterialWallOB = cobustMaterialWallOB;
	}

	public String getCobustMaterialWallRem() {
		return cobustMaterialWallRem;
	}

	public void setCobustMaterialWallRem(String cobustMaterialWallRem) {
		this.cobustMaterialWallRem = cobustMaterialWallRem;
	}

	public List<DownConductor> getDownConductor() {
		return downConductor;
	}

	public void setDownConductor(List<DownConductor> downConductor) {
		this.downConductor = downConductor;
	}
	
	
}
