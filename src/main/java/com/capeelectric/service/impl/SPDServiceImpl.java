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
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SPD;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.service.SPDService;
import com.capeelectric.util.UserFullName;

/**
 * This SPDServiceImpl service class doing save and retrieve operation related to SPDDetails
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class SPDServiceImpl implements SPDService{

	private static final Logger logger = LoggerFactory.getLogger(SPDServiceImpl.class);
	
	@Autowired
	private SPDRepository spdRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public void addSPDDetails(SPD spdDesc)
			throws  SPDException{
		if (spdDesc != null && spdDesc.getUserName() != null
				&& !spdDesc.getUserName().isEmpty() && spdDesc.getBasicLpsId() != null
				&& spdDesc.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(spdDesc.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(spdDesc.getBasicLpsId())) {
				Optional<SPD> spdRepo = spdRepository
						.findByBasicLpsId(spdDesc.getBasicLpsId());
				if (!spdRepo.isPresent()
						|| !spdRepo.get().getBasicLpsId().equals(spdDesc.getBasicLpsId())) {
					
					spdDesc.setCreatedDate(LocalDateTime.now());
					spdDesc.setUpdatedDate(LocalDateTime.now());
					spdDesc.setCreatedBy(userFullName.findByUserName(spdDesc.getUserName()));
					spdDesc.setUpdatedBy(userFullName.findByUserName(spdDesc.getUserName()));
					spdRepository.save(spdDesc);
					logger.debug("SPD Report Details Successfully Saved in DB");
				} else {
					logger.error("Basic LPS Id Already Available.Create New Basic Id");
					throw new SPDException("Basic LPS Id Already Available.Create New Basic Id");
				}
			}
			else {
				logger.error("Given Basic LPS Id is Not Registered in Basic LPS");
				throw new SPDException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		else {
			logger.error("Invalid Inputs");
			throw new SPDException("Invalid Inputs");
		}
			
	}
	
	@Override
	public List<SPD> retrieveSPDDetails(String userName, Integer basicLpsId)
			throws SPDException {
		if (userName != null) {
			List<SPD> spdRepo = spdRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (spdRepo != null && !spdRepo.isEmpty()) {				
				return spdRepo;
			} else {
				logger.error("Given UserName & Id doesn't exist in SPD Report Details");
				throw new SPDException("Given UserName & Id doesn't exist in SPD Report Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new SPDException("Invalid Inputs");
		}
	}
	
	@Override
	public void updateSpdDetails(SPD spdDesc) throws SPDException {

		if (spdDesc != null && spdDesc.getSpdId() != null
				&& spdDesc.getSpdId() != 0 && spdDesc.getBasicLpsId() != null
				&& spdDesc.getBasicLpsId() != 0) {
			Optional<SPD> spdRepo = spdRepository
					.findById(spdDesc.getSpdId());
			if (spdRepo.isPresent()
					&& spdRepo.get().getBasicLpsId().equals(spdDesc.getBasicLpsId())) {
				spdDesc.setUpdatedDate(LocalDateTime.now());
				spdDesc.setUpdatedBy(userFullName.findByUserName(spdDesc.getUserName()));
				spdRepository.save(spdDesc);
				logger.debug("Earthing Report Details Successfully Updated in DB");
			} else {
				logger.error("Given Basic LPS Id and SPD Id is Invalid");
				throw new SPDException("Given Basic LPS Id and SPD Id is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new SPDException("Invalid inputs");
		}
	}
}
