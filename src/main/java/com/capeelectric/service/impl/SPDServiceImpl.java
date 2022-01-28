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

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SpdDescription;
import com.capeelectric.model.SpdReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.service.SPDService;
import com.capeelectric.util.FindNonRemovedObjects;
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
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@Override
	public void addSPDDetails(SpdReport spdReport)
			throws  SPDException{
		if (spdReport != null && spdReport.getUserName() != null
				&& !spdReport.getUserName().isEmpty() && spdReport.getBasicLpsId() != null
				&& spdReport.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(spdReport.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(spdReport.getBasicLpsId())) {
				Optional<SpdReport> spdRepo = spdRepository
						.findByBasicLpsId(spdReport.getBasicLpsId());
				if (!spdRepo.isPresent()
						|| !spdRepo.get().getBasicLpsId().equals(spdReport.getBasicLpsId())) {
					List<SPD> spd = spdReport.getSpd();
					if(spd != null && spd.size() > 0) {
						spdReport.setCreatedDate(LocalDateTime.now());
						spdReport.setUpdatedDate(LocalDateTime.now());
						spdReport.setCreatedBy(userFullName.findByUserName(spdReport.getUserName()));
						spdReport.setUpdatedBy(userFullName.findByUserName(spdReport.getUserName()));
						spdRepository.save(spdReport);
						logger.debug("SPD Report Details Successfully Saved in DB");
					} else {
						logger.error("Please fill all the fields before clicking next button");
						throw new SPDException("Please fill all the fields before clicking next button");
					}
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
	public List<SpdReport> retrieveSPDDetails(String userName, Integer basicLpsId)
			throws SPDException {
		if (userName != null) {
			List<SpdReport> spdRepo = spdRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (spdRepo != null && !spdRepo.isEmpty()) {
				for(SpdReport spdReportItr : spdRepo) {
					spdReportItr.setSpd(findNonRemovedObjects.findNonRemovedSpdBuildings(spdReportItr));
					logger.debug("Successfully done findNonRemovedSpdBuildings() call");
				}
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
	public void updateSpdDetails(SpdReport spdReport) throws SPDException {

		if (spdReport != null && spdReport.getSpdReportId() != null
				&& spdReport.getSpdReportId() != 0 && spdReport.getBasicLpsId() != null
				&& spdReport.getBasicLpsId() != 0) {
			Optional<SpdReport> spdRepo = spdRepository
					.findById(spdReport.getSpdReportId());
			if (spdRepo.isPresent()
					&& spdRepo.get().getBasicLpsId().equals(spdReport.getBasicLpsId())) {
				spdReport.setUpdatedDate(LocalDateTime.now());
				spdReport.setUpdatedBy(userFullName.findByUserName(spdReport.getUserName()));
				spdRepository.save(spdReport);
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
