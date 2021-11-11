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
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.service.DownConductorService;
import com.capeelectric.util.UserFullName;

/**
 * 
 *   This DownConductorServiceImpl service class doing save and retrieve operation related to DownConductorDetails
 * 
 * @author CAPE-SOFTWARE
 *
 */

@Service
public class DownConductorServiceImpl implements DownConductorService{
	
	private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceImpl.class);
	
	@Autowired
	private DownConductorRepository downConductorRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public void addDownConductorsDetails(DownConductorDescription downConductorDesc)
			throws  DownConductorException{
		if (downConductorDesc != null && downConductorDesc.getUserName() != null
				&& !downConductorDesc.getUserName().isEmpty() && downConductorDesc.getBasicLpsId() != null
				&& downConductorDesc.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(downConductorDesc.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(downConductorDesc.getBasicLpsId())) {
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
					throw new DownConductorException("Basic LPS Id Already Available.Create New Basic Id");
				}
			}
			else {
				throw new DownConductorException("Given Basic LPS Id is Not Registered in Basic LPS");
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
	
	@Override
	public void updateDownConductorDetails(DownConductorDescription downConductorDesc) throws DownConductorException {

		if (downConductorDesc != null && downConductorDesc.getDownConduDescId() != null
				&& downConductorDesc.getDownConduDescId() != 0 && downConductorDesc.getBasicLpsId() != null
				&& downConductorDesc.getBasicLpsId() != 0) {
			Optional<DownConductorDescription> downConductorRepo = downConductorRepository
					.findById(downConductorDesc.getDownConduDescId());
			if (downConductorRepo.isPresent()
					&& downConductorRepo.get().getBasicLpsId().equals(downConductorDesc.getBasicLpsId())) {
				downConductorDesc.setUpdatedDate(LocalDateTime.now());
				downConductorDesc.setUpdatedBy(userFullName.findByUserName(downConductorDesc.getUserName()));
				downConductorRepository.save(downConductorDesc);
			} else {
				throw new DownConductorException("Given Basic LPS Id and Down Conductor Id is Invalid");
			}

		} else {
			throw new DownConductorException("Invalid inputs");
		}
	}
	
}
