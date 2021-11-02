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

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.EarthingLpsService;
import com.capeelectric.util.UserFullName;

/**
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
	
	@Override
	public void addEarthingLpsDetails(EarthingLpsDescription earthingLpsDesc)
			throws  EarthingLpsException{
		if (earthingLpsDesc != null && earthingLpsDesc.getUserName() != null
				&& !earthingLpsDesc.getUserName().isEmpty() && earthingLpsDesc.getBasicLpsId() != null
				&& earthingLpsDesc.getBasicLpsId() != 0) {
			
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthingLpsDesc.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(earthingLpsDesc.getBasicLpsId())) {
				Optional<EarthingLpsDescription> earthingLpsRepo = earthingLpsRepository
						.findByBasicLpsId(earthingLpsDesc.getBasicLpsId());
				if (!earthingLpsRepo.isPresent()
						|| !earthingLpsRepo.get().getBasicLpsId().equals(earthingLpsDesc.getBasicLpsId())) {
					
					earthingLpsDesc.setCreatedDate(LocalDateTime.now());
					earthingLpsDesc.setUpdatedDate(LocalDateTime.now());
					earthingLpsDesc.setCreatedBy(userFullName.findByUserName(earthingLpsDesc.getUserName()));
					earthingLpsDesc.setUpdatedBy(userFullName.findByUserName(earthingLpsDesc.getUserName()));
					earthingLpsRepository.save(earthingLpsDesc);
				} else {
					throw new EarthingLpsException("Basic LPS Id Already Available.Create New Basic Id");
				}
			}
			else {
				throw new EarthingLpsException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		else {
			throw new EarthingLpsException("Invalid Inputs");
		}
			
	}
	
	@Override
	public List<EarthingLpsDescription> retrieveEarthingLpsDetails(String userName, Integer basicLpsId)
			throws EarthingLpsException {
		if (userName != null) {
			List<EarthingLpsDescription> earthingLpsRepo = earthingLpsRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (earthingLpsRepo != null && !earthingLpsRepo.isEmpty()) {				
				return earthingLpsRepo;
			} else {
				throw new EarthingLpsException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new EarthingLpsException("Invalid Inputs");
		}
	}
	
	@Override
	public void updateEarthingLpsDetails(EarthingLpsDescription earthingLpsDesc) throws EarthingLpsException {

		if (earthingLpsDesc != null && earthingLpsDesc.getEarthingId() != null
				&& earthingLpsDesc.getEarthingId() != 0 && earthingLpsDesc.getBasicLpsId() != null
				&& earthingLpsDesc.getBasicLpsId() != 0) {
			Optional<EarthingLpsDescription> earthingLpsRepo = earthingLpsRepository
					.findById(earthingLpsDesc.getEarthingId());
			if (earthingLpsRepo.isPresent()
					&& earthingLpsRepo.get().getBasicLpsId().equals(earthingLpsDesc.getBasicLpsId())) {
				earthingLpsDesc.setUpdatedDate(LocalDateTime.now());
				earthingLpsDesc.setUpdatedBy(userFullName.findByUserName(earthingLpsDesc.getUserName()));
				earthingLpsRepository.save(earthingLpsDesc);
			} else {
				throw new EarthingLpsException("Given Basic LPS Id and Earthing LPS Id is Invalid");
			}

		} else {
			throw new EarthingLpsException("Invalid inputs");
		}
	}
}
