/**
 * 
 */
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.EarthingLpsService;
import com.capeelectric.util.AddRemovedStatus;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

/**
 *  
 *   This EarthingLpsServiceImpl service class doing save and retrieve  operation related to  EarthingLpsDetails
 *      
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class EarthingLpsServiceImpl implements EarthingLpsService {

	private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceImpl.class);
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private EarthingLpsRepository earthingLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@Autowired
	private AddRemovedStatus addRemovedStatus;
	
	@Transactional
	@Override
	public void addEarthingLpsDetails(EarthingReport earthingReport)
			throws  EarthingLpsException, AirTerminationException{
		logger.info("Called addEarthingLpsDetails function");

		if (earthingReport != null && earthingReport.getUserName() != null
				&& !earthingReport.getUserName().isEmpty() && earthingReport.getBasicLpsId() != null
				&& earthingReport.getBasicLpsId() != 0) {
			
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthingReport.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(earthingReport.getBasicLpsId())) {
				Optional<EarthingReport> earthingLpsRepo = earthingLpsRepository
						.findByBasicLpsId(earthingReport.getBasicLpsId());
				if (!earthingLpsRepo.isPresent()
						|| !earthingLpsRepo.get().getBasicLpsId().equals(earthingReport.getBasicLpsId())) {
					List<EarthingLpsDescription> earthingLpsDescription = earthingReport.getEarthingLpsDescription();
					if(earthingLpsDescription != null && earthingLpsDescription.size() > 0) {
						earthingReport.setCreatedDate(LocalDateTime.now());
						earthingReport.setUpdatedDate(LocalDateTime.now());
						earthingReport.setCreatedBy(userFullName.findByUserName(earthingReport.getUserName()));
						earthingReport.setUpdatedBy(userFullName.findByUserName(earthingReport.getUserName()));
						//addRemovedStatus.removeSummaryLps(earthingReport.getUserName(),earthingReport.getBasicLpsId());

						earthingLpsRepository.save(earthingReport);
						logger.debug("Earthing Report Details Successfully Saved in DB");
						userFullName.addUpdatedByandDate(earthingReport.getBasicLpsId(),userFullName.findByUserName(earthingReport.getUserName()));
						logger.debug("Basic Lps UpdatedBy and UpdatedDate by Earthing");
					} else {
						logger.error("Please fill all the fields before clicking next button");
						throw new EarthingLpsException("Please fill all the fields before clicking next button");
					}
					
				} else {
					logger.error("Basic LPS Id Already Available.Create New Basic Id");
					throw new EarthingLpsException("Basic LPS Id Already Available.Create New Basic Id");
				}
			}
			else {
				logger.error("Given Basic LPS Id is Not Registered in Basic LPS");
				throw new EarthingLpsException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		else {
			logger.error("Invalid Inputs");
			throw new EarthingLpsException("Invalid Inputs");
		}
		logger.info("Ended addEarthingLpsDetails function");
	
	}
	
	@Override
	public List<EarthingReport> retrieveEarthingLpsDetails(String userName, Integer basicLpsId)
			throws EarthingLpsException {
		logger.info("Called retrieveEarthingLpsDetails function");

		if (userName != null) {
			List<EarthingReport> earthingLpsRepo = earthingLpsRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (earthingLpsRepo != null && !earthingLpsRepo.isEmpty()) {
				for(EarthingReport earthingReportItr : earthingLpsRepo) {
					earthingReportItr.setEarthingLpsDescription(findNonRemovedObjects.findNonRemovedEarthingLpsBuildings(earthingReportItr));
					logger.debug("Successfully done findNonRemovedEarthingLpsBuildings() call");
				}
				logger.info("Ended retrieveEarthingLpsDetails function");
				return earthingLpsRepo;
			} else {
				logger.error("Given UserName & Id doesn't exist in Earthing Report Details");
				return new ArrayList<EarthingReport>();			}
		} else {
			logger.error("Invalid Inputs");
			throw new EarthingLpsException("Invalid Inputs");
		}

	}
	
	@Transactional
	@Override
	public void updateEarthingLpsDetails(EarthingReport earthingReport) throws EarthingLpsException, AirTerminationException {
		logger.info("Called updateEarthingLpsDetails function");

		if (earthingReport != null && earthingReport.getEarthingReportId() != null
				&& earthingReport.getEarthingReportId() != 0 && earthingReport.getBasicLpsId() != null
				&& earthingReport.getBasicLpsId() != 0) {
			Optional<EarthingReport> earthingLpsRepo = earthingLpsRepository
					.findById(earthingReport.getEarthingReportId());
			if (earthingLpsRepo.isPresent()
					&& earthingLpsRepo.get().getBasicLpsId().equals(earthingReport.getBasicLpsId())) {
				earthingReport.setUpdatedDate(LocalDateTime.now());
				earthingReport.setUpdatedBy(userFullName.findByUserName(earthingReport.getUserName()));

				EarthingReport earthingReportRepo = earthingLpsRepository.save(earthingReport);
				addRemovedStatus.removeEarthingSummaryLpsObservations(earthingReportRepo);

				logger.debug("Earthing Report Details Successfully Updated in DB");
				userFullName.addUpdatedByandDate(earthingReport.getBasicLpsId(),userFullName.findByUserName(earthingReport.getUserName()));
				logger.debug("Basic Lps UpdatedBy and UpdatedDate by Earthing");
			} else {
				logger.error("Given Basic LPS Id and Earthing LPS Id is Invalid");
				throw new EarthingLpsException("Given Basic LPS Id and Earthing LPS Id is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new EarthingLpsException("Invalid inputs");
		}
		logger.info("Ended updateEarthingLpsDetails function");

	}
}
