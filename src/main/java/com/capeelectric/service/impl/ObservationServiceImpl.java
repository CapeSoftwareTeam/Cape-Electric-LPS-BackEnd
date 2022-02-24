/**
 * 
 */
package com.capeelectric.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capeelectric.model.AllStepsRemarks;
import com.capeelectric.model.remarks.AirTerminationRemarks;
import com.capeelectric.model.remarks.DownConductorReportRemarks;
import com.capeelectric.model.remarks.EarthStudRemarksReport;
import com.capeelectric.model.remarks.EarthingReportRemarks;
import com.capeelectric.model.remarks.SPDReportRemarks;
import com.capeelectric.model.remarks.SeperationDistanceReportRemarks;
import com.capeelectric.repository.AirTerminationRemarksRepository;
import com.capeelectric.repository.DownConductorRemarksRepository;
import com.capeelectric.repository.EarthStudRemarksRepository;
import com.capeelectric.repository.EarthingLpsRemarksRepository;
import com.capeelectric.repository.SPDRemarksRepository;
import com.capeelectric.repository.SeperationDistanceRemarksRepository;
import com.capeelectric.service.ObservationService;
import com.capeelectric.util.FindNonRemovedRemarksObjects;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class ObservationServiceImpl implements ObservationService {
	private static final Logger logger = LoggerFactory.getLogger(ObservationServiceImpl.class);

	@Autowired
	private AirTerminationRemarksRepository airTerminationRemarksRepository;

	@Autowired
	private DownConductorRemarksRepository downConductorRemarksRepository;

	@Autowired
	private EarthingLpsRemarksRepository earthingLpsRemarksRepository;

	@Autowired
	private SPDRemarksRepository spdRemarksRepository;

	@Autowired
	private SeperationDistanceRemarksRepository seperationDistanceRemarksRepository;

	@Autowired
	private EarthStudRemarksRepository earthStudRemarksRepository;

	@Autowired
	private FindNonRemovedRemarksObjects findNonRemovedRemarksObjects;

	@Override
	public AllStepsRemarks retrieveObservationsInSummary(Integer basicLpsId) {
		logger.info("Called retrieveObservationsInSummary function");

		AllStepsRemarks allStepsRemarks = new AllStepsRemarks();

		logger.debug("fetching process started for Air Termination Remarks");
		List<AirTerminationRemarks> airTerminationRemarks = airTerminationRemarksRepository
				.findByBasicLpsId(basicLpsId);
		logger.debug("fetching process Ended for Air Termination Remarks");

		logger.debug("fetching process started for Down Conductors Remarks");
		List<DownConductorReportRemarks> downConductorReportRemarks = downConductorRemarksRepository
				.findByBasicLpsId(basicLpsId);
		logger.debug("fetching process Ended for Down Conductors Remarks");

		logger.debug("fetching process started for Earthing Lps Remarks");
		List<EarthingReportRemarks> earthingReportRemarks = earthingLpsRemarksRepository
				.findByBasicLpsId(basicLpsId);
		logger.debug("fetching process Ended for Earthing Lps Remarks");
		
		logger.debug("fetching process started for SPD Remarks");
		List<SPDReportRemarks> spdReportRemarks = spdRemarksRepository
				.findByBasicLpsId(basicLpsId);
		logger.debug("fetching process Ended for SPD Remarks");

		logger.debug("fetching process started for Seperation Distance Remarks");
		List<SeperationDistanceReportRemarks> seperationDistanceReportRemarks = seperationDistanceRemarksRepository
				.findByBasicLpsId(basicLpsId);
		logger.debug("fetching process Ended for Seperation Distance Remarks");

		logger.debug("fetching process started for Earth Stud Remarks");
		List<EarthStudRemarksReport> earthStudRemarksReport = earthStudRemarksRepository
				.findByBasicLpsId(basicLpsId);
		logger.debug("fetching process Ended for Earth Stud Remarks");

		
		if (!airTerminationRemarks.isEmpty() && airTerminationRemarks != null) {
			if (airTerminationRemarks.get(0).getLpsAirDiscription().size() > 0) {
				airTerminationRemarks.get(0).setLpsAirDiscription(findNonRemovedRemarksObjects
						.findNonRemovedAirTerminationBuildings(airTerminationRemarks.get(0)));
			}
			logger.debug("Air Termination Remarks fetched");
			allStepsRemarks.setAirTermination(airTerminationRemarks);
		}

		if (!downConductorReportRemarks.isEmpty() && downConductorReportRemarks != null) {
			if (downConductorReportRemarks.get(0).getDownConductorDescription().size() > 0) {
				downConductorReportRemarks.get(0).setDownConductorDescription(findNonRemovedRemarksObjects
						.findNonRemovedDownConductorsBuildings(downConductorReportRemarks.get(0)));
			}
			logger.debug("Down Conductors Remarks fetched");
			allStepsRemarks.setDownConductorReport(downConductorReportRemarks);
		}

		if (!earthingReportRemarks.isEmpty() && earthingReportRemarks != null) {
			if (earthingReportRemarks.get(0).getEarthingLpsDescription().size() > 0) {
				earthingReportRemarks.get(0).setEarthingLpsDescription(findNonRemovedRemarksObjects
						.findNonRemovedEarthingLpsBuildings(earthingReportRemarks.get(0)));
			}
			logger.debug("Earthing Lps Remarks fetched");
			allStepsRemarks.setEarthingReport(earthingReportRemarks);
		}

		if (!spdReportRemarks.isEmpty() && spdReportRemarks != null) {
			if (spdReportRemarks.get(0).getSpd().size() > 0) {
				spdReportRemarks.get(0).setSpd(findNonRemovedRemarksObjects
						.findNonRemovedSpdBuildings(spdReportRemarks.get(0)));
			}
			logger.debug("SPD Report Remarks fetched");
			allStepsRemarks.setSpdReport(spdReportRemarks);
		}

		if (!seperationDistanceReportRemarks.isEmpty() && seperationDistanceReportRemarks != null) {
			if (seperationDistanceReportRemarks.get(0).getSeperationDistanceDescription().size() > 0) {
				seperationDistanceReportRemarks.get(0).setSeperationDistanceDescription(findNonRemovedRemarksObjects
						.findNonRemovedSeperationDistanceBuildings(seperationDistanceReportRemarks.get(0)));
			}
			logger.debug("Seperation Distance Remarks fetched");
			allStepsRemarks.setSeperationDistanceReport(seperationDistanceReportRemarks);
		}

		if (!earthStudRemarksReport.isEmpty() && earthStudRemarksReport != null) {
			if (earthStudRemarksReport.get(0).getEarthStudDescription().size() > 0) {
				earthStudRemarksReport.get(0).setEarthStudDescription(findNonRemovedRemarksObjects
						.findNonRemovedEarthStudRemarksBuildings(earthStudRemarksReport.get(0)));
			}
			logger.debug("Earth Stud Remarks fetched");
			allStepsRemarks.setEarthStudReport(earthStudRemarksReport);
		}
		
		logger.info("Ended retrieveObservationsInSummary function");
		return allStepsRemarks;
	}

}
