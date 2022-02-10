/**
 * 
 */
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SPDException;
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.SeperationDistanceService;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

/**
 *  This SeperationDistanceServiceImpl service class doing save and retrieve operation related to SeperationDistanceDetails
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class SeperationDistanceServiceImpl implements SeperationDistanceService{

	private static final Logger logger = LoggerFactory.getLogger(SeperationDistanceServiceImpl.class);
	
	@Autowired
	private SeperationDistanceRepository seperationDistanceRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@Transactional
	@Override
	public void addSeperationDistance(SeperationDistanceReport seperationDistanceReport)
			throws  SeperationDistanceException{
		logger.info("Called addSeperationDistance function");

		if (seperationDistanceReport != null && seperationDistanceReport.getUserName() != null
				&& !seperationDistanceReport.getUserName().isEmpty() && seperationDistanceReport.getBasicLpsId() != null
				&& seperationDistanceReport.getBasicLpsId() != 0) {
			
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(seperationDistanceReport.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(seperationDistanceReport.getBasicLpsId())) {
				Optional<SeperationDistanceReport> seperationDistanceRepo = seperationDistanceRepository
						.findByBasicLpsId(seperationDistanceReport.getBasicLpsId());
				if (!seperationDistanceRepo.isPresent()
						|| !seperationDistanceRepo.get().getBasicLpsId().equals(seperationDistanceReport.getBasicLpsId())) {
					List<SeperationDistanceDescription> seperationDistanceDescription = seperationDistanceReport.getSeperationDistanceDescription();
					if(seperationDistanceDescription != null && seperationDistanceDescription.size() > 0) {
						seperationDistanceReport.setCreatedDate(LocalDateTime.now());
						seperationDistanceReport.setUpdatedDate(LocalDateTime.now());
						seperationDistanceReport.setCreatedBy(userFullName.findByUserName(seperationDistanceReport.getUserName()));
						seperationDistanceReport.setUpdatedBy(userFullName.findByUserName(seperationDistanceReport.getUserName()));
						seperationDistanceRepository.save(seperationDistanceReport);
						logger.debug("Seperation Distance Report Details Successfully Saved in DB");
					} else {
						logger.error("Please fill all the fields before clicking next button");
						throw new SeperationDistanceException("Please fill all the fields before clicking next button");
					}
				} else {
					logger.error("Basic LPS Id Already Available.Create New Basic Id");
					throw new SeperationDistanceException("Basic LPS Id Already Available.Create New Basic Id");
				}
			}
			else {
				logger.error("Given Basic LPS Id is Not Registered in Basic LPS");
				throw new SeperationDistanceException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		else {
			logger.error("Invalid Inputs");
			throw new SeperationDistanceException("Invalid Inputs");
		}
		logger.info("Ended addSeperationDistance function");

	}
	
	@Override
	public List<SeperationDistanceReport> retrieveSeperationDetails(String userName, Integer basicLpsId)
			throws SeperationDistanceException {
		logger.info("Called retrieveSeperationDetails function");
		if (userName != null) {
			List<SeperationDistanceReport> seperationDistanceRepo = seperationDistanceRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (seperationDistanceRepo != null && !seperationDistanceRepo.isEmpty()) {	
				for(SeperationDistanceReport seperationDistanceReportItr : seperationDistanceRepo) {
					seperationDistanceReportItr.setSeperationDistanceDescription(findNonRemovedObjects.findNonRemovedSeperationDistanceBuildings(seperationDistanceReportItr));
					logger.debug("Successfully done findNonRemovedSeperationDistanceBuildings() call");
				}
				logger.info("Ended retrieveSeperationDetails function");
				return seperationDistanceRepo;
			} else {
				logger.error("Given UserName & Id doesn't exist in Seperation Distance Details");
				throw new SeperationDistanceException("Given UserName & Id doesn't exist in Seperation Distance Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new SeperationDistanceException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public void updateSeperationDetails(SeperationDistanceReport seperationDistanceReport) throws SeperationDistanceException {
		logger.info("Called updateSeperationDetails function");
		
		if (seperationDistanceReport != null && seperationDistanceReport.getSeperationDistanceReportId() != null
				&& seperationDistanceReport.getSeperationDistanceReportId() != 0 && seperationDistanceReport.getBasicLpsId() != null
				&& seperationDistanceReport.getBasicLpsId() != 0) {
			Optional<SeperationDistanceReport> seperationDistanceRepo = seperationDistanceRepository
					.findById(seperationDistanceReport.getSeperationDistanceReportId());
			if (seperationDistanceRepo.isPresent()
					&& seperationDistanceRepo.get().getBasicLpsId().equals(seperationDistanceReport.getBasicLpsId())) {
				seperationDistanceReport.setUpdatedDate(LocalDateTime.now());
				seperationDistanceReport.setUpdatedBy(userFullName.findByUserName(seperationDistanceReport.getUserName()));
				seperationDistanceRepository.save(seperationDistanceReport);
				logger.debug("Seperation Distance Report Details Successfully Updated in DB");
			} else {
				logger.error("Given Basic LPS Id and Seperation Distance Id is Invalid");
				throw new SeperationDistanceException("Given Basic LPS Id and Seperation Distance Id is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new SeperationDistanceException("Invalid inputs");
		}
		logger.info("Ended updateSeperationDetails function");
	}
}
