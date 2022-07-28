package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.service.AirTerminationLpsService;
import com.capeelectric.util.AddRemovedStatus;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UpdateBuildingCountToFile;
import com.capeelectric.util.UserFullName;

/**
 * This AirTerminationLpsServiceImpl service class doing save and retrieve
 * operation related to AirTerminationLpsDetails
 * 
 * @author capeelectricsoftware
 *
 */

@Service
public class AirTerminationLpsServiceImpl implements AirTerminationLpsService {
	
	private static final Logger logger = LoggerFactory.getLogger(AirTerminationLpsServiceImpl.class);

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;	

	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@Autowired
	private AddRemovedStatus addRemovedStatus;
	
	@Autowired
	private UpdateBuildingCountToFile updateBuildingCountToFile;

	@Transactional
	@Override
	public void addAirTerminationLpsDetails(AirTermination airTermination ) throws AirTerminationException {
		logger.info("Called addAirTerminationLpsDetails function");

		if (airTermination != null && airTermination.getBasicLpsId() != null && airTermination.getUserName() != null && airTermination.getUserName() != "") {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(airTermination.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(airTermination.getBasicLpsId())) {
				List<LpsAirDiscription> lpsAirDiscription = airTermination.getLpsAirDescription();
				Optional<AirTermination> airTerminationLpsRepo = airTerminationLpsRepository.findByBasicLpsId(airTermination.getBasicLpsId());
				if(!airTerminationLpsRepo.isPresent() || !airTerminationLpsRepo.get().getBasicLpsId().equals(airTermination.getBasicLpsId())) {
					if(lpsAirDiscription != null && lpsAirDiscription.size() > 0) {
						for(LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
							lpsAirDiscriptionItr.setBuildingCount(new Random().nextInt(999999999));
						}
					}
					airTermination.setCreatedDate(LocalDateTime.now());
					airTermination.setUpdatedDate(LocalDateTime.now());
					airTermination.setCreatedBy(userFullName.findByUserName(airTermination.getUserName()));
					airTermination.setUpdatedBy(userFullName.findByUserName(airTermination.getUserName()));
					try {
						AirTermination termination = airTerminationLpsRepository.save(airTermination);
						updateBuildingCountToFile.updateAirterminationBuildingCount(termination);
						logger.debug("Air Termination Successfully Saved in DB");
						userFullName.addUpdatedByandDate(airTermination.getBasicLpsId(),airTermination.getUserName());
						logger.debug("Basic Lps Updated By and Updated Date by AirTermination");
						userFullName.addUpdatedByandDate(airTermination.getBasicLpsId(),userFullName.findByUserName(airTermination.getUserName()));
						logger.debug("Basic Lps UpdatedBy and UpdatedDate by AirTermination");
					}catch(Exception e) {
						logger.error("Not able to save Air Termination data "+e.getMessage());
						throw new AirTerminationException("Not able to save Air Termination data "+e.getMessage());
					}
				}
				else {
					logger.error("Given Basic LPS Id is already Available in Air Termination");
					throw new AirTerminationException("Given Basic LPS Id is already Available in Air Termination");
				}
			}
			else {
				logger.error("Given Basic LPS Id is Not Registered in Basic LPS");
				throw new AirTerminationException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		 else {
			logger.error("Invalid Inputs");
			throw new AirTerminationException("Invalid Inputs");
		}
		logger.info("Ended addAirTerminationLpsDetails function");
	}

	@Override
	public List<AirTermination> retrieveAirTerminationLps(String userName, Integer basicLpsId)
			throws AirTerminationException {
		logger.info("Called retrieveAirTerminationLps function");

		if (userName != null && basicLpsId != null) {
			List<AirTermination> airTerminationLpsRepo = airTerminationLpsRepository
					.findByUserNameAndBasicLpsId(userName, basicLpsId);
			if (airTerminationLpsRepo != null && !airTerminationLpsRepo.isEmpty()) {
				for(AirTermination airTerminationRepo : airTerminationLpsRepo) {
					airTerminationRepo.setLpsAirDescription(findNonRemovedObjects.findNonRemovedAirTerminationBuildings(airTerminationRepo));
					logger.debug("Successfully done findNonRemovedAirTerminationBuildings() call");
				}
				logger.info("Ended retrieveAirTerminationLps function");
				return airTerminationLpsRepo;
				
			} else {
				logger.error("Given UserName & Id doesn't exist in Air Termination LPS Details");
				return new ArrayList<AirTermination>();
			}
		} else {
			logger.error("Invalid Inputs");
			throw new AirTerminationException("Invalid Inputs");
		}		
	}

	@Transactional
	@Override
	public void updateAirTerminationLps(AirTermination airTermination) throws AirTerminationException {
		logger.info("Called updateAirTerminationLps function");

		if (airTermination != null && airTermination.getAirTerminationId() != null
				&& airTermination.getAirTerminationId() != 0 && airTermination.getBasicLpsId() != null
				&& airTermination.getBasicLpsId() != 0) {
			Optional<AirTermination> airTerminationLpsRepo = airTerminationLpsRepository
					.findById(airTermination.getAirTerminationId());
			if (airTerminationLpsRepo.isPresent()
					&& airTerminationLpsRepo.get().getBasicLpsId().equals(airTermination.getBasicLpsId())) {
				addRemovedStatus.addRemoveStatusInDownConductors(airTermination.getLpsAirDescription());
				addRemovedStatus.addRemoveStatusInEarthingLps(airTermination.getLpsAirDescription());
				addRemovedStatus.addRemoveStatusInSpd(airTermination.getLpsAirDescription());
				addRemovedStatus.addRemoveStatusInSeperationDistance(airTermination.getLpsAirDescription());
				addRemovedStatus.addRemoveStatusInEarthStud(airTermination.getLpsAirDescription());
				addRemovedStatus.addRemoveStatusInSummaryLps(airTermination.getLpsAirDescription(),airTermination.getUserName(),airTermination.getBasicLpsId());
//				addRemovedStatus.deleteRemovedFileInDownconductorInFileDB(airTermination);

				List<LpsAirDiscription> lpsAirDiscription = airTermination.getLpsAirDescription();
				for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
					logger.debug("Building Count value adding for new buildings");
					// Building count value adding for new buildings
					if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() == null) {
						lpsAirDiscriptionItr.setBuildingCount(new Random().nextInt(999999999));
					}
				}				
				airTermination.setUpdatedDate(LocalDateTime.now());
				airTermination.setUpdatedBy(userFullName.findByUserName(airTermination.getUserName()));
			    AirTermination termination = airTerminationLpsRepository.save(airTermination);
				logger.debug("Air Termination successfully updated into DB");
				updateBuildingCountToFile.updateAirterminationBuildingCount(termination);
				userFullName.addUpdatedByandDate(airTermination.getBasicLpsId(),userFullName.findByUserName(airTermination.getUserName()));
				logger.debug("Basic Lps Updated By and Updated Date by AirTermination");
			} else {
				logger.error("Given Basic LPS Id and LPS Air Description Id is Invalid");
				throw new AirTerminationException("Given Basic LPS Id and LPS Air Description Id is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new AirTerminationException("Invalid inputs");
		}
		logger.info("Ended updateAirTerminationLps function");
	}
	
}
