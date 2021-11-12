package com.capeelectric.model;

public class LpsFinalReport {

	private String userName;

	private Integer lpsBasicId;

	private LpsAirDiscription lpsAirDiscription;

	private BasicLps basicLps;

	private DownConductorDescription downConductorDesc;

	private EarthingLpsDescription earthingLpsDescription;

	private EarthStudDescription earthStudDescription;

	private SPD SPDDesc;

	private SeperationDistanceDescription seperationDistanceDesc;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getLpsBasicId() {
		return lpsBasicId;
	}

	public void setLpsBasicId(Integer lpsBasicId) {
		this.lpsBasicId = lpsBasicId;
	}

	public LpsAirDiscription getLpsAirDiscription() {
		return lpsAirDiscription;
	}

	public void setLpsAirDiscription(LpsAirDiscription lpsAirDiscription) {
		this.lpsAirDiscription = lpsAirDiscription;
	}

	public BasicLps getBasicLps() {
		return basicLps;
	}

	public void setBasicLps(BasicLps basicLps) {
		this.basicLps = basicLps;
	}

	public DownConductorDescription getDownConductorDesc() {
		return downConductorDesc;
	}

	public void setDownConductorDesc(DownConductorDescription downConductorDesc) {
		this.downConductorDesc = downConductorDesc;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public EarthStudDescription getEarthStudDescription() {
		return earthStudDescription;
	}

	public void setEarthStudDescription(EarthStudDescription earthStudDescription) {
		this.earthStudDescription = earthStudDescription;
	}

	public SPD getSPDDesc() {
		return SPDDesc;
	}

	public void setSPDDesc(SPD sPDDesc) {
		SPDDesc = sPDDesc;
	}

	public SeperationDistanceDescription getSeperationDistanceDesc() {
		return seperationDistanceDesc;
	}

	public void setSeperationDistanceDesc(SeperationDistanceDescription seperationDistanceDesc) {
		this.seperationDistanceDesc = seperationDistanceDesc;
	}

	

}
