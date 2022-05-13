/**
 * 
 */
package com.capeelectric.util;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SummaryLps;
import com.capeelectric.model.SummaryLpsBuildings;
import com.capeelectric.repository.DownConductorListRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.repository.EarthStudListRepository;
import com.capeelectric.repository.EarthingLpsListRepository;
import com.capeelectric.repository.FileDBRepository;
import com.capeelectric.repository.SPDListRepository;
import com.capeelectric.repository.SeperationDistanceListRepository;
import com.capeelectric.repository.SummaryLpsListRepository;
import com.capeelectric.repository.SummaryLpsRepository;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Configuration
public class AddRemovedStatus {
	private static final Logger logger = LoggerFactory.getLogger(AddRemovedStatus.class);

	@Autowired
	private DownConductorListRepository downConductorListRepository;

	@Autowired
	private EarthingLpsListRepository earthingLpsListRepository;

	@Autowired
	private SPDListRepository spdListRepository;

	@Autowired
	private SeperationDistanceListRepository seperationDistanceListRepository;

	@Autowired
	private EarthStudListRepository earthStudListRepository;

	@Autowired
	private SummaryLpsListRepository summaryLpsListRepository;

	@Autowired

	private FileDBRepository fileDBRepository;

	@Autowired
	private DownConductorRepository downConductorRepository;
	
	@Autowired
	private SummaryLpsRepository summaryLpsRepository;

	// Method for adding R status in Down Conductors
	public void addRemoveStatusInDownConductors(List<LpsAirDiscription> lpsAirDiscription, Integer basicLpsId)
			throws AirTerminationException {
		logger.info("Called addRemoveStatusInDownConductors function");

		for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
			if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
					&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
                removedFileBaseOnBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
				DownConductorDescription downConductorDescriptionRepo = downConductorListRepository
						.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
				if (downConductorDescriptionRepo != null && downConductorDescriptionRepo.getBuildingCount()
						.equals(lpsAirDiscriptionItr.getBuildingCount())) {
					try {
						logger.debug("downConductorDescriptionRepo available for building count:"
								+ lpsAirDiscriptionItr.getBuildingCount());
						downConductorDescriptionRepo.setFlag("R");
						logger.debug("Building count:" + lpsAirDiscriptionItr.getBuildingCount()
								+ "for Downconductor updated with status R");
						downConductorListRepository.save(downConductorDescriptionRepo);
					} catch (Exception e) {
						logger.error(
								"Please check removed Air Termination Building data not available in Down Conductor"
										+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Down Conductor"
										+ e.getMessage());
					}
				}
			}
		}

		deleteRemovedFileInDownconductorInFileDB(basicLpsId);
		logger.info("Ended addRemoveStatusInDownConductors function");
	}

	// Method for adding R status in Earthing Lps
	public void addRemoveStatusInEarthingLps(List<LpsAirDiscription> lpsAirDiscription) throws AirTerminationException {
		logger.info("Called addRemoveStatusInEarthingLps function");

		for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
			if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
					&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {

				EarthingLpsDescription earthingLpsDescriptionRepo = earthingLpsListRepository
						.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
				if (earthingLpsDescriptionRepo != null && earthingLpsDescriptionRepo.getBuildingCount()
						.equals(lpsAirDiscriptionItr.getBuildingCount())) {
					try {
						logger.debug("earthingLpsDescriptionRepo available for building count:"
								+ lpsAirDiscriptionItr.getBuildingCount());
						earthingLpsDescriptionRepo.setFlag("R");
						logger.debug("Building count:" + lpsAirDiscriptionItr.getBuildingCount()
								+ "for Earthing Lps updated with status R");
						earthingLpsListRepository.save(earthingLpsDescriptionRepo);
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in Earthing Lps"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Earthing Lps"
										+ e.getMessage());
					}
				}
			}
		}
		logger.info("Ended addRemoveStatusInEarthingLps function");
	}

	// Method for adding R status in SPD
	public void addRemoveStatusInSpd(List<LpsAirDiscription> lpsAirDiscription) throws AirTerminationException {
		logger.info("Called addRemoveStatusInSpd function");

		for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
			if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
					&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {

				SPD spdRepo = spdListRepository.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
				if (spdRepo != null && spdRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
					try {
						logger.debug("spdRepo available for building count:" + lpsAirDiscriptionItr.getBuildingCount());
						spdRepo.setFlag("R");
						logger.debug("Building count:" + lpsAirDiscriptionItr.getBuildingCount()
								+ "for SPD updated with status R");
						spdListRepository.save(spdRepo);
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in SPD"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in SPD"
										+ e.getMessage());
					}
				}
			}
		}
		logger.info("Ended addRemoveStatusInSpd function");
	}

	// Method for adding R status in Seperation Distance
	public void addRemoveStatusInSeperationDistance(List<LpsAirDiscription> lpsAirDiscription)
			throws AirTerminationException {
		logger.info("Called addRemoveStatusInSeperationDistance function");

		for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
			if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
					&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {

				SeperationDistanceDescription seperationDistanceDescriptionRepo = seperationDistanceListRepository
						.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
				if (seperationDistanceDescriptionRepo != null && seperationDistanceDescriptionRepo.getBuildingCount()
						.equals(lpsAirDiscriptionItr.getBuildingCount())) {
					try {
						logger.debug("seperationDistanceDescriptionRepo available for building count:"
								+ lpsAirDiscriptionItr.getBuildingCount());
						seperationDistanceDescriptionRepo.setFlag("R");
						logger.debug("Building count:" + lpsAirDiscriptionItr.getBuildingCount()
								+ "for Seperation Distance updated with status R");
						seperationDistanceListRepository.save(seperationDistanceDescriptionRepo);
					} catch (Exception e) {
						logger.debug(
								"Please check removed Air Termination Building data not available in Seperation Distance"
										+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Seperation Distance"
										+ e.getMessage());
					}
				}
			}
		}
		logger.info("Ended addRemoveStatusInSeperationDistance function");

	}

	// Method for adding R status in Earth Stud
	public void addRemoveStatusInEarthStud(List<LpsAirDiscription> lpsAirDiscription) throws AirTerminationException {
		logger.info("Called addRemoveStatusInEarthStud function");

		for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
			if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
					&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {

				EarthStudDescription earthStudDescriptionRepo = earthStudListRepository
						.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
				if (earthStudDescriptionRepo != null && earthStudDescriptionRepo.getBuildingCount()
						.equals(lpsAirDiscriptionItr.getBuildingCount())) {
					try {
						logger.debug("earthStudDescriptionRepo available for building count:"
								+ lpsAirDiscriptionItr.getBuildingCount());
						earthStudDescriptionRepo.setFlag("R");
						logger.debug("Building count:" + lpsAirDiscriptionItr.getBuildingCount()
								+ "for Earth Stud updated with status R");
						earthStudListRepository.save(earthStudDescriptionRepo);
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in Earth Stud"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Earth Stud"
										+ e.getMessage());
					}
				}
			}
		}
		logger.info("Ended addRemoveStatusInEarthStud function");

	}

	// Method for adding R status in Earth Stud
	public void addRemoveStatusInSummaryLps(List<LpsAirDiscription> lpsAirDiscription) throws AirTerminationException {
 		logger.info("Called addRemoveStatusInSummaryLps function");

 		for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
 			if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
 					&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {

 				SummaryLpsBuildings summaryLpsBuildingsRepo = summaryLpsListRepository
 						.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
 				if (summaryLpsBuildingsRepo != null
 						&& summaryLpsBuildingsRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
 					try {
 						logger.debug("summaryLpsBuildingsRepo available for building count:"
 								+ lpsAirDiscriptionItr.getBuildingCount());
 						summaryLpsBuildingsRepo.setFlag("R");
 						logger.debug("Building count:" + lpsAirDiscriptionItr.getBuildingCount()
 								+ "for Summary updated with status R");
 						summaryLpsListRepository.save(summaryLpsBuildingsRepo);
 					} catch (Exception e) {
 						logger.debug("Please check removed Air Termination Building data not available in Summary"
 								+ e.getMessage());
 						throw new AirTerminationException(
 								"Please check removed Air Termination Building data not available in Summary"
 										+ e.getMessage());
 					}
 				}  
 			}
 		}
 		logger.info("Ended addRemoveStatusInSummaryLps function");
	}

	// Method for deleteRemovedFileInDownconductorInFileDB
	public void deleteRemovedFileInDownconductorInFileDB(Integer basicLpsId) throws AirTerminationException {
		logger.info("Called deleteRemovedFileInDownconductorInFileDB function");
		Optional<DownConductorReport> downConductorDescriptionRepo = downConductorRepository
				.findByBasicLpsId(basicLpsId);
		if (downConductorDescriptionRepo.isPresent()) {
			List<DownConductorDescription> downConductorDescription = downConductorDescriptionRepo.get()
					.getDownConductorDescription();
			for (DownConductorDescription downConductorDescriptioniter : downConductorDescription) {
				for (DownConductor downConductoriter : downConductorDescriptioniter.getDownConductor()) {
					List<ResponseFile> fileDB2 = fileDBRepository.findByLpsId(basicLpsId);
					for (ResponseFile responseFile : fileDB2) {
						if (downConductorDescriptioniter.getFlag().equals("R")
								&& downConductorDescriptioniter.getFileId1() != null
								&& downConductorDescriptioniter.getFileId1().equals(responseFile.getFileId())) {

							if (downConductorDescriptioniter.getFileId1().equals(responseFile.getFileId())
									&& downConductorDescriptioniter.getFileName1().equals(responseFile.getFileName())) {
								logger.debug("Called Removed File in FileDB From DownConductorDescription");
								fileDBRepository.delete(responseFile);
								logger.debug("Removed File in FileDB FileID" + responseFile.getFileId()
										+ "from DownConductorDescription");
							}
							if (downConductoriter.getFileId() != null && downConductoriter.getFileName() != null) {
								if (downConductoriter.getFileId().equals(responseFile.getFileId())
										&& downConductoriter.getFileName().equals(responseFile.getFileName())) {
									logger.debug("Called Removed File in FileDB from DownConductor");
									fileDBRepository.delete(responseFile);
									logger.debug("Removed File in FileDB FileID" + responseFile.getFileId()
											+ "from DownConductor");

								}
							}
						}
					}
				}
			}
		}
	}

	// Method for adding R status in summaryLps
	public void removeSummaryLps(String userName, Integer basiclpsId) throws AirTerminationException {
		logger.info("Called removeSummaryLps function");

		List<SummaryLps> summaryLps = summaryLpsRepository.findByUserNameAndBasicLpsId(userName, basiclpsId);
		for (SummaryLps summaryLpsData : summaryLps) {

			if (!summaryLpsData.getFlag().equalsIgnoreCase("R")) {
				for (SummaryLpsBuildings summaryLpsBuildings : summaryLpsData.getSummaryLpsBuildings()) {
					summaryLpsBuildings.setFlag("R");
				}
				summaryLpsData.setFlag("R");
				summaryLpsRepository.save(summaryLpsData);
				logger.debug("Summary Lps successfully updated in DB");
			}
		}
		logger.info("Ended removeSummaryLps function");

	}
	
	private void removedFileBaseOnBuildingCount(Integer buildingCount) {
		List<ResponseFile> removeAllBuildings = fileDBRepository.findByBuildingCount(buildingCount);
		if (!removeAllBuildings.isEmpty() && removeAllBuildings.size() > 0) {
			fileDBRepository.deleteAll(removeAllBuildings);
		}
	}
}
