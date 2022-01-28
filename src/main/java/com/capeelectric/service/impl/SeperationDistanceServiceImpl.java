/**
 * 
 */
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.SeperationDistanceService;
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
	
	@Override
	public void addSeperationDistance(SeperationDistanceDescription seperationDistanceDesc)
			throws  SeperationDistanceException{
		if (seperationDistanceDesc != null && seperationDistanceDesc.getUserName() != null
				&& !seperationDistanceDesc.getUserName().isEmpty() && seperationDistanceDesc.getBasicLpsId() != null
				&& seperationDistanceDesc.getBasicLpsId() != 0) {
			
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(seperationDistanceDesc.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(seperationDistanceDesc.getBasicLpsId())) {
				Optional<SeperationDistanceDescription> seperationDistanceRepo = seperationDistanceRepository
						.findByBasicLpsId(seperationDistanceDesc.getBasicLpsId());
				if (!seperationDistanceRepo.isPresent()
						|| !seperationDistanceRepo.get().getBasicLpsId().equals(seperationDistanceDesc.getBasicLpsId())) {
					
					seperationDistanceDesc.setCreatedDate(LocalDateTime.now());
					seperationDistanceDesc.setUpdatedDate(LocalDateTime.now());
					seperationDistanceDesc.setCreatedBy(userFullName.findByUserName(seperationDistanceDesc.getUserName()));
					seperationDistanceDesc.setUpdatedBy(userFullName.findByUserName(seperationDistanceDesc.getUserName()));
					seperationDistanceRepository.save(seperationDistanceDesc);
					logger.debug("Seperation Distance Report Details Successfully Saved in DB");
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
			
	}
	
	@Override
	public List<SeperationDistanceDescription> retrieveSeperationDetails(String userName, Integer basicLpsId)
			throws SeperationDistanceException {
		if (userName != null) {
			List<SeperationDistanceDescription> seperationDistanceRepo = seperationDistanceRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (seperationDistanceRepo != null && !seperationDistanceRepo.isEmpty()) {				
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
	
	@Override
	public void updateSeperationDetails(SeperationDistanceDescription seperationDistanceDesc) throws SeperationDistanceException {

		if (seperationDistanceDesc != null && seperationDistanceDesc.getSeperationDistanceId() != null
				&& seperationDistanceDesc.getSeperationDistanceId() != 0 && seperationDistanceDesc.getBasicLpsId() != null
				&& seperationDistanceDesc.getBasicLpsId() != 0) {
			Optional<SeperationDistanceDescription> seperationDistanceRepo = seperationDistanceRepository
					.findById(seperationDistanceDesc.getSeperationDistanceId());
			if (seperationDistanceRepo.isPresent()
					&& seperationDistanceRepo.get().getBasicLpsId().equals(seperationDistanceDesc.getBasicLpsId())) {
				seperationDistanceDesc.setUpdatedDate(LocalDateTime.now());
				seperationDistanceDesc.setUpdatedBy(userFullName.findByUserName(seperationDistanceDesc.getUserName()));
				seperationDistanceRepository.save(seperationDistanceDesc);
				logger.debug("Seperation Distance Report Details Successfully Updated in DB");
			} else {
				logger.error("Given Basic LPS Id and Seperation Distance Id is Invalid");
				throw new SeperationDistanceException("Given Basic LPS Id and Seperation Distance Id is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new SeperationDistanceException("Invalid inputs");
		}
	}
}
