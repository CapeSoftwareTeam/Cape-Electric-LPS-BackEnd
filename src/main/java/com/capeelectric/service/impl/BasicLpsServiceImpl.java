package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.controller.BasicLpsController;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.service.BasicLpsService;
import com.capeelectric.util.UserFullName;

/**
 * This BasicLpsServiceImpl service class doing save and retrieve operation related to BasicLpsDetails
 * @author capeelectricsoftware
 *
 */

@Service
public class BasicLpsServiceImpl implements BasicLpsService {

	private static final Logger logger = LoggerFactory.getLogger(BasicLpsController.class);

	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Transactional
	@Override
	public BasicLps addBasicLpsDetails(BasicLps basicLps) throws BasicLpsException {
		
		if (basicLps != null && basicLps.getClientName() != null ) {
			Optional<BasicLps> basicLpsDetailsRepo = basicLpsRepository.findByClientName(basicLps.getClientName());
			logger.debug("Basic Client Repo data available");
				if(!basicLpsDetailsRepo.isPresent()) {
					basicLps.setCreatedDate(LocalDateTime.now());
					basicLps.setUpdatedDate(LocalDateTime.now());
					basicLps.setCreatedBy(userFullName.findByUserName(basicLps.getUserName()));
					basicLps.setUpdatedBy(userFullName.findByUserName(basicLps.getUserName()));
					return basicLpsRepository.save(basicLps);
				}
				else {
					logger.error("Client name "+basicLps.getClientName()+" already exists");
					throw new BasicLpsException("Client name "+basicLps.getClientName()+" already exists");
				}
			
		} else {
			logger.error("Invalid Inputs");
			throw new BasicLpsException("Invalid Inputs");
		}
	}
	
	@Override
	public List<BasicLps> retrieveBasicLpsDetails(String userName, Integer basicLpsId)
			throws BasicLpsException {
		if (userName != null) {
			List<BasicLps> basicLpsDetailsRepo = basicLpsRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (basicLpsDetailsRepo != null && !basicLpsDetailsRepo.isEmpty()) {	
				logger.debug("Basic Client Repo data available");
				return basicLpsDetailsRepo;
			} else {
				logger.error("Given UserName & Id doesn't exist in Basic Lps Details");
				throw new BasicLpsException("Given UserName & Id doesn't exist in Basic Lps Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new BasicLpsException("Invalid Inputs");
		}
	}
	
	@Transactional
	@Override
	public void updateBasicLpsDetails(BasicLps basicLps) throws BasicLpsException {

		if (basicLps != null && basicLps.getBasicLpsId() != null && basicLps.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository
					.findByBasicLpsId(basicLps.getBasicLpsId());
			if (basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(basicLps.getBasicLpsId())) {
				basicLps.setUpdatedDate(LocalDateTime.now());
				basicLps.setUpdatedBy(userFullName.findByUserName(basicLps.getUserName()));
				basicLpsRepository.save(basicLps);
				logger.debug("Basic Lps successfully Updated in DB");

			} else {
				logger.error("Given Basic LPS Id is Invalid");
				throw new BasicLpsException("Given Basic LPS Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new BasicLpsException("Invalid inputs");
		}
	}
	
}
	
