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

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPDDescription;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.service.SPDService;
import com.capeelectric.util.UserFullName;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class SPDServiceImpl implements SPDService{

	private static final Logger logger = LoggerFactory.getLogger(SPDServiceImpl.class);
	
	@Autowired
	private SPDRepository spdRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public void addSPDDetails(SPDDescription spdDesc)
			throws  SPDException{
		if (spdDesc != null && spdDesc.getUserName() != null
				&& !spdDesc.getUserName().isEmpty() && spdDesc.getBasicLpsId() != null
				&& spdDesc.getBasicLpsId() != 0) {
			Optional<SPDDescription> spdRepo = spdRepository
					.findByBasicLpsId(spdDesc.getBasicLpsId());
			if (!spdRepo.isPresent()
					|| !spdRepo.get().getBasicLpsId().equals(spdDesc.getBasicLpsId())) {
				
				spdDesc.setCreatedDate(LocalDateTime.now());
				spdDesc.setUpdatedDate(LocalDateTime.now());
				spdDesc.setCreatedBy(userFullName.findByUserName(spdDesc.getUserName()));
				spdDesc.setUpdatedBy(userFullName.findByUserName(spdDesc.getUserName()));
				spdRepository.save(spdDesc);
			} else {
				throw new SPDException("Basic LPS Id Already Available,Create New Basic Id");
			}
			
		}
		else {
			throw new SPDException("Invalid Inputs");
		}
			
	}
	
	@Override
	public List<SPDDescription> retrieveSPDDetails(String userName, Integer basicLpsId)
			throws SPDException {
		if (userName != null) {
			List<SPDDescription> spdRepo = spdRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (spdRepo != null && !spdRepo.isEmpty()) {				
				return spdRepo;
			} else {
				throw new SPDException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new SPDException("Invalid Inputs");
		}
	}
}
