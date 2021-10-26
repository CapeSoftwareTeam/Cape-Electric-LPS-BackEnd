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

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.EarthingLpsService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
public class EarthingLpsServiceImpl implements EarthingLpsService {

private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceImpl.class);
	
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
			Optional<EarthingLpsDescription> earthingLpsRepo = earthingLpsRepository
					.findBySiteId(earthingLpsDesc.getBasicLpsId());
			if (!earthingLpsRepo.isPresent()
					|| !earthingLpsRepo.get().getBasicLpsId().equals(earthingLpsDesc.getBasicLpsId())) {
				
				earthingLpsDesc.setCreatedDate(LocalDateTime.now());
				earthingLpsDesc.setUpdatedDate(LocalDateTime.now());
				earthingLpsDesc.setCreatedBy(userFullName.findByUserName(earthingLpsDesc.getUserName()));
				earthingLpsDesc.setUpdatedBy(userFullName.findByUserName(earthingLpsDesc.getUserName()));
				earthingLpsRepository.save(earthingLpsDesc);
			} else {
				throw new EarthingLpsException("Basic LPS Id Already Available,Create New Basic Id");
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
}
