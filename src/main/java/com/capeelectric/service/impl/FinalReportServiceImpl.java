package com.capeelectric.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsFinalReport;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.FinalReportService;

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

			// Lps Air description
			logger.debug("fetching process started for LpsAirDiscription");
			Optional<LpsAirDiscription> lpsAirDisc = airTerminationLpsRepository.findByBasicLpsId(basicLpsId);
			logger.debug("LpsAirDiscription_fetching ended");

			// Down Conductors
			logger.debug("fetching process started for DownConductorDescription");
			Optional<DownConductorDescription> downConductorDetails = downConductorRepository
					.findByBasicLpsId(basicLpsId);
			logger.debug("DownConductorDescription_fetching ended");

			// Earthing Lps
			logger.debug("fetching process started for EarthingLpsDescription");
			Optional<EarthingLpsDescription> earthingLpsDetails = earthingLpsRepository.findByBasicLpsId(basicLpsId);
			logger.debug("EarthingLpsDescription_fetching ended");

			// SPD details
			logger.debug("fetching process started for SPD");
			Optional<SPD> spdDetails = spdRepository.findByBasicLpsId(basicLpsId);
			logger.debug("SPD_fetching ended");

			// Seperation Distance
			logger.debug("fetching process started for SeperationDistanceDescription");
			Optional<SeperationDistanceDescription> separateDistanceDetails = seperationDistanceRepository
					.findByBasicLpsId(basicLpsId);
			logger.debug("SeperationDistanceDescription_fetching ended");

			// Earth Stud
			logger.debug("fetching process started for EarthStud");
			Optional<EarthStudDescription> earthStudDetails = earthStudRepository.findByBasicLpsId(basicLpsId);
			logger.debug(" EarthStud_fetching ended");

			 if (basicLpsDetails.isPresent() && basicLpsDetails != null) {
				lpsFinalReport.setBasicLps(basicLpsDetails.get());
			}

			 if (lpsAirDisc.isPresent() && lpsAirDisc != null) {
				lpsFinalReport.setLpsAirDiscription(lpsAirDisc.get());

			}
			 if (downConductorDetails.isPresent() && downConductorDetails != null) {
				lpsFinalReport.setDownConductorDesc(downConductorDetails.get());
			}

			 if (earthingLpsDetails.isPresent() && earthingLpsDetails != null) {
				lpsFinalReport.setEarthingLpsDescription(earthingLpsDetails.get());
			}

			 if (spdDetails.isPresent() && spdDetails != null) {
				lpsFinalReport.setSPDDesc(spdDetails.get());
			}

			 if (separateDistanceDetails.isPresent() && separateDistanceDetails != null) {
				lpsFinalReport.setSeperationDistanceDesc(separateDistanceDetails.get());

			}
			 if (earthStudDetails.isPresent() && earthStudDetails != null) {
				lpsFinalReport.setEarthStudDescription(earthStudDetails.get());
				logger.debug("Successfully Seven_Steps fetching Operation done");
				return Optional.of(lpsFinalReport);

			}

		}
		else {
			throw new FinalReportException("Invalid Input");
		}
		return Optional.of(lpsFinalReport);
	}

}