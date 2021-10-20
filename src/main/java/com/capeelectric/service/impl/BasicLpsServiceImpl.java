package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public BasicLps addBasicLpsDetails(BasicLps basicLps) throws BasicLpsException {
		
		if (basicLps != null && basicLps.getClientName() != null ) {
			basicLps.setCreatedDate(LocalDateTime.now());
			basicLps.setUpdatedDate(LocalDateTime.now());
			basicLps.setCreatedBy(userFullName.findByUserName(basicLps.getUserName()));
			basicLps.setUpdatedBy(userFullName.findByUserName(basicLps.getUserName()));
			return basicLpsRepository.save(basicLps);
		} else {
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
				return basicLpsDetailsRepo;
			} else {
				throw new BasicLpsException("Given UserName & Id doesn't exist in Basic Lps Details");
			}
		} else {
			throw new BasicLpsException("Invalid Inputs");
		}
	}
	
}
