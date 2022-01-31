package com.capeelectric.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsFinalReport;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.model.SpdReport;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.FinalReportService;
import com.capeelectric.util.FindNonRemovedObjects;

/**
 * This FinalReportServiceImpl class to doing retrieve_site and
 * retrieve_allFinalinformations based on siteId and userName
 *
 * @author capeelectricsoftware
 *
 */
@Service
public class FinalReportServiceImpl implements FinalReportService {

	private static final Logger logger = LoggerFactory.getLogger(FinalReportServiceImpl.class);

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	@Autowired
	private DownConductorRepository downConductorRepository;

	@Autowired
	private EarthingLpsRepository earthingLpsRepository;

	@Autowired
	private SPDRepository spdRepository;

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@Autowired
	private SeperationDistanceRepository seperationDistanceRepository;

	@Autowired
	private EarthStudRepository earthStudRepository;

	private LpsFinalReport lpsFinalReport;
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObject;

	/**
	 * @param userName and departmentName also string retrieveSiteDetails method to
	 *                 retrieve site details based on userName and departmentName
	 * @return List of sites
	 *
	 */

	@Override
	public List<BasicLps> retrieveListOfBasicLps(String userName) throws FinalReportException {
		if (userName != null) {
			try {
				logger.info("Basic fetching process started");
				return basicLpsRepository.findByUserName(userName);
			} catch (Exception e) {
				logger.info("Basic fetching process failed");
				throw new FinalReportException("Fetching basic process failed");
			}
		} else {
			throw new FinalReportException("Invaild Input");
		}

	}

	@Override
	public Optional<LpsFinalReport> retrieveLpsReports(String userName, Integer basicLpsId)
			throws FinalReportException {
		if (userName != null && basicLpsId != null) {
			lpsFinalReport = new LpsFinalReport();
			lpsFinalReport.setUserName(userName);
			lpsFinalReport.setLpsBasicId(basicLpsId);

			// Basic Lps
			logger.debug("fetching process started for BasicLpsDetails_Information");
			Optional<BasicLps> basicLpsDetails = basicLpsRepository.findByBasicLpsId(basicLpsId);
			logger.debug("BasicLpsDetails_Information fetching ended");

			// Lps Air Termination
			logger.debug("fetching process started for LpsAirTermination");
			Optional<AirTermination> airTermination = airTerminationLpsRepository.findByBasicLpsId(basicLpsId);
			logger.debug("LpsAirTermination_fetching ended");

			// Down Conductors
			logger.debug("fetching process started for DownConductorReport");
			Optional<DownConductorReport> downConductorReport = downConductorRepository
					.findByBasicLpsId(basicLpsId);
			logger.debug("DownConductorReport_fetching ended");

			// Earthing Lps
			logger.debug("fetching process started for EarthingReport");
			Optional<EarthingReport> earthingReport = earthingLpsRepository.findByBasicLpsId(basicLpsId);
			logger.debug("EarthingReport_fetching ended");

			// SPD details
			logger.debug("fetching process started for SpdReport");
			Optional<SpdReport> spdReport = spdRepository.findByBasicLpsId(basicLpsId);
			logger.debug("SpdReport_fetching ended");

			// Seperation Distance
			logger.debug("fetching process started for SeperationDistanceReport");
			Optional<SeperationDistanceReport> seperationDistanceReport = seperationDistanceRepository
					.findByBasicLpsId(basicLpsId);
			logger.debug("SeperationDistanceReport_fetching ended");

			// Earth Stud
			logger.debug("fetching process started for EarthStudReport");
			Optional<EarthStudReport> earthStudReport = earthStudRepository.findByBasicLpsId(basicLpsId);
			logger.debug("EarthStudReport_fetching ended");

			 if (basicLpsDetails.isPresent() && basicLpsDetails != null) {
				lpsFinalReport.setBasicLps(basicLpsDetails.get());
			}

			 if (airTermination.isPresent() && airTermination != null) {
				 airTermination.get().setLpsAirDescription(
							findNonRemovedObject.findNonRemovedAirTerminationBuildings(airTermination.get()));
				lpsFinalReport.setAirTermination(airTermination.get());

			}
			 if (downConductorReport.isPresent() && downConductorReport != null) {
				 downConductorReport.get().setDownConductorDescription(
							findNonRemovedObject.findNonRemovedDownConductorsBuildings(downConductorReport.get()));
				lpsFinalReport.setDownConductorReport(downConductorReport.get());
			}

			 if (earthingReport.isPresent() && earthingReport != null) {
				 earthingReport.get().setEarthingLpsDescription(
							findNonRemovedObject.findNonRemovedEarthingLpsBuildings(earthingReport.get()));
				lpsFinalReport.setEarthingReport(earthingReport.get());
			}

			 if (spdReport.isPresent() && spdReport != null) {
				 spdReport.get().setSpd(
							findNonRemovedObject.findNonRemovedSpdBuildings(spdReport.get()));
				lpsFinalReport.setSpdReport(spdReport.get());
			}

			 if (seperationDistanceReport.isPresent() && seperationDistanceReport != null) {
				 seperationDistanceReport.get().setSeperationDistanceDescription(
							findNonRemovedObject.findNonRemovedSeperationDistanceBuildings(seperationDistanceReport.get()));
				lpsFinalReport.setSeperationDistanceReport(seperationDistanceReport.get());

			}
			 if (earthStudReport.isPresent() && earthStudReport != null) {
				 earthStudReport.get().setEarthStudDescription(
							findNonRemovedObject.findNonRemovedEarthStudBuildings(earthStudReport.get()));
				lpsFinalReport.setEarthStudReport(earthStudReport.get());
				logger.debug("Successfully Seven_Steps fetching Operation done");
				return Optional.of(lpsFinalReport);

			}

		}
		else {
			logger.error("Invalid Input");
			throw new FinalReportException("Invalid Input");
		}
		return Optional.of(lpsFinalReport);
	}

}