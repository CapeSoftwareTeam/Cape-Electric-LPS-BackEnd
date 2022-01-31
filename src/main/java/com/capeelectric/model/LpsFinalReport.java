package com.capeelectric.model;

public class LpsFinalReport {

	private String userName;

	private Integer lpsBasicId;

	private AirTermination airTermination;

	private BasicLps basicLps;

	private DownConductorReport downConductorReport;

	private EarthingReport earthingReport;

	private EarthStudReport earthStudReport;

	private SpdReport spdReport;

	private SeperationDistanceReport seperationDistanceReport;

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

	public BasicLps getBasicLps() {
		return basicLps;
	}

	public void setBasicLps(BasicLps basicLps) {
		this.basicLps = basicLps;
	}

	public AirTermination getAirTermination() {
		return airTermination;
	}

	public void setAirTermination(AirTermination airTermination) {
		this.airTermination = airTermination;
	}

	public DownConductorReport getDownConductorReport() {
		return downConductorReport;
	}

	public void setDownConductorReport(DownConductorReport downConductorReport) {
		this.downConductorReport = downConductorReport;
	}

	public EarthingReport getEarthingReport() {
		return earthingReport;
	}

	public void setEarthingReport(EarthingReport earthingReport) {
		this.earthingReport = earthingReport;
	}

	public EarthStudReport getEarthStudReport() {
		return earthStudReport;
	}

	public void setEarthStudReport(EarthStudReport earthStudReport) {
		this.earthStudReport = earthStudReport;
	}

	public SpdReport getSpdReport() {
		return spdReport;
	}

	public void setSpdReport(SpdReport spdReport) {
		this.spdReport = spdReport;
	}

	public SeperationDistanceReport getSeperationDistanceReport() {
		return seperationDistanceReport;
	}

	public void setSeperationDistanceReport(SeperationDistanceReport seperationDistanceReport) {
		this.seperationDistanceReport = seperationDistanceReport;
	}
	

}
