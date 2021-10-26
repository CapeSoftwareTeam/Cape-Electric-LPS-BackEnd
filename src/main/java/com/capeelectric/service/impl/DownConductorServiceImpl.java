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
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.service.DownConductorService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Service
public class DownConductorServiceImpl implements DownConductorService{
	
	private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceImpl.class);
	
	@Autowired
	private DownConductorRepository downConductorRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public void addDownConductorsDetails(DownConductorDescription downConductorDesc)
			throws  DownConductorException{
		if (downConductorDesc != null && downConductorDesc.getUserName() != null
				&& !downConductorDesc.getUserName().isEmpty() && downConductorDesc.getBasicLpsId() != null
				&& downConductorDesc.getBasicLpsId() != 0) {
			Optional<DownConductorDescription> downConductorRepo = downConductorRepository
					.findByBasicLpsId(downConductorDesc.getBasicLpsId());
			if (!downConductorRepo.isPresent()
					|| !downConductorRepo.get().getBasicLpsId().equals(downConductorDesc.getBasicLpsId())) {
				
				downConductorDesc.setCreatedDate(LocalDateTime.now());
				downConductorDesc.setUpdatedDate(LocalDateTime.now());
				downConductorDesc.setCreatedBy(userFullName.findByUserName(downConductorDesc.getUserName()));
				downConductorDesc.setUpdatedBy(userFullName.findByUserName(downConductorDesc.getUserName()));
				downConductorRepository.save(downConductorDesc);
			} else {
				throw new DownConductorException("Basic LPS Id Already Available,Create New Basic Id");
			}
			
		}
		else {
			throw new DownConductorException("Invalid Inputs");
		}
			
	}
	
	@Override
	public List<DownConductorDescription> retrieveDownConductorDetails(String userName, Integer basicLpsId)
			throws DownConductorException {
		if (userName != null) {
			List<DownConductorDescription> downConductorRepo = downConductorRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (downConductorRepo != null && !downConductorRepo.isEmpty()) {				
				return downConductorRepo;
			} else {
				throw new DownConductorException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new DownConductorException("Invalid Inputs");
		}
	}
	
}
