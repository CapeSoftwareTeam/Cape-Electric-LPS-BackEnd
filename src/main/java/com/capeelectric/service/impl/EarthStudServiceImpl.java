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

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.service.EarthStudService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class EarthStudServiceImpl implements EarthStudService{
private static final Logger logger = LoggerFactory.getLogger(EarthStudServiceImpl.class);
	
	@Autowired
	private EarthStudRepository earthStudRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public void addEarthStudDetails(EarthStudDescription earthStudDescription)
			throws  EarthStudException{
		if (earthStudDescription != null && earthStudDescription.getUserName() != null
				&& !earthStudDescription.getUserName().isEmpty() && earthStudDescription.getBasicLpsId() != null
				&& earthStudDescription.getBasicLpsId() != 0) {
			Optional<EarthStudDescription> earthStudRepo = earthStudRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			if (!earthStudRepo.isPresent()
					|| !earthStudRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
				
				earthStudDescription.setCreatedDate(LocalDateTime.now());
				earthStudDescription.setUpdatedDate(LocalDateTime.now());
				earthStudDescription.setCreatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
				earthStudDescription.setUpdatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
				earthStudRepository.save(earthStudDescription);
			} else {
				throw new EarthStudException("Basic LPS Id Already Available,Create New Basic Id");
			}
			
		}
		else {
			throw new EarthStudException("Invalid Inputs");
		}
			
	}
	
	@Override
	public List<EarthStudDescription> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException {
		if (userName != null) {
			List<EarthStudDescription> earthStudRepo = earthStudRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (earthStudRepo != null && !earthStudRepo.isEmpty()) {				
				return earthStudRepo;
			} else {
				throw new EarthStudException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new EarthStudException("Invalid Inputs");
		}
	}
		
	@Override
	public void updateEarthStudDetails(EarthStudDescription earthStudDescription) throws EarthStudException {

		if (earthStudDescription != null && earthStudDescription.getEarthStudDescId() != null
				&& earthStudDescription.getEarthStudDescId() != 0 && earthStudDescription.getBasicLpsId() != null
				&& earthStudDescription.getBasicLpsId() != 0) {
			Optional<EarthStudDescription> earthStudRepo = earthStudRepository
					.findById(earthStudDescription.getEarthStudDescId());
			if (earthStudRepo.isPresent()
					&& earthStudRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
				earthStudDescription.setUpdatedDate(LocalDateTime.now());
				earthStudDescription.setUpdatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
				earthStudRepository.save(earthStudDescription);
			} else {
				throw new EarthStudException("Given BasicLpsId and Earth Stud Id is Invalid");
			}

		} else {
			throw new EarthStudException("Invalid inputs");
		}
	}
}
