/**
 * 
 */
package com.capeelectric.model;

import java.util.List;

import com.capeelectric.model.remarks.AirTerminationRemarks;
import com.capeelectric.model.remarks.DownConductorReportRemarks;
import com.capeelectric.model.remarks.EarthStudRemarksReport;
import com.capeelectric.model.remarks.EarthingReportRemarks;
import com.capeelectric.model.remarks.SPDReportRemarks;
import com.capeelectric.model.remarks.SeperationDistanceReportRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class AllStepsRemarks {
	
	private List<AirTerminationRemarks> airTermination;

	private List<DownConductorReportRemarks> downConductorReport;

	private List<EarthingReportRemarks> earthingReport;
	
	private List<SPDReportRemarks> spdReport;

	private List<SeperationDistanceReportRemarks> seperationDistanceReport;

	private List<EarthStudRemarksReport> earthStudReport;

	

	public List<AirTerminationRemarks> getAirTermination() {
		return airTermination;
	}

	public void setAirTermination(List<AirTerminationRemarks> airTermination) {
		this.airTermination = airTermination;
	}

	public List<DownConductorReportRemarks> getDownConductorReport() {
		return downConductorReport;
	}

	public void setDownConductorReport(List<DownConductorReportRemarks> downConductorReport) {
		this.downConductorReport = downConductorReport;
	}

	public List<EarthingReportRemarks> getEarthingReport() {
		return earthingReport;
	}

	public void setEarthingReport(List<EarthingReportRemarks> earthingReport) {
		this.earthingReport = earthingReport;
	}

	public List<SPDReportRemarks> getSpdReport() {
		return spdReport;
	}

	public void setSpdReport(List<SPDReportRemarks> spdReport) {
		this.spdReport = spdReport;
	}

	public List<SeperationDistanceReportRemarks> getSeperationDistanceReport() {
		return seperationDistanceReport;
	}

	public void setSeperationDistanceReport(List<SeperationDistanceReportRemarks> seperationDistanceReport) {
		this.seperationDistanceReport = seperationDistanceReport;
	}

	public List<EarthStudRemarksReport> getEarthStudReport() {
		return earthStudReport;
	}

	public void setEarthStudReport(List<EarthStudRemarksReport> earthStudReport) {
		this.earthStudReport = earthStudReport;
	}

	
	
	

}
