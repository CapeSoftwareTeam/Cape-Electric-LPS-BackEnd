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
import com.capeelectric.model.EarthingReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.EarthingLpsService;
import com.capeelectric.util.UserFullName;

/**
 *  
 *   This EarthingLpsServiceImpl service class doing save and retrieve  operation related to  EarthingLpsDetails
 *      
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
	public void addEarthingLpsDetails(EarthingReport earthingReport)
			throws  EarthingLpsException{
		if (earthingReport != null && earthingReport.getUserName() != null
				&& !earthingReport.getUserName().isEmpty() && earthingReport.getBasicLpsId() != null
				&& earthingReport.getBasicLpsId() != 0) {
			
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthingReport.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(earthingReport.getBasicLpsId())) {
				Optional<EarthingReport> earthingLpsRepo = earthingLpsRepository
						.findByBasicLpsId(earthingReport.getBasicLpsId());
				if (!earthingLpsRepo.isPresent()
						|| !earthingLpsRepo.get().getBasicLpsId().equals(earthingReport.getBasicLpsId())) {
					List<EarthingLpsDescription> earthingLpsDescription = earthingReport.getEarthingLpsDescription();
					if(earthingLpsDescription != null && earthingLpsDescription.size() > 0) {
						earthingReport.setCreatedDate(LocalDateTime.now());
						earthingReport.setUpdatedDate(LocalDateTime.now());
						earthingReport.setCreatedBy(userFullName.findByUserName(earthingReport.getUserName()));
						earthingReport.setUpdatedBy(userFullName.findByUserName(earthingReport.getUserName()));
						earthingLpsRepository.save(earthingReport);
					} else {
						throw new EarthingLpsException("Please fill all the fields before clicking next button");
					}
					
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
	public List<EarthingReport> retrieveEarthingLpsDetails(String userName, Integer basicLpsId)
			throws EarthingLpsException {
		if (userName != null) {
			List<EarthingReport> earthingLpsRepo = earthingLpsRepository.findByUserNameAndBasicLpsId(userName,
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
	public void updateEarthingLpsDetails(EarthingReport earthingReport) throws EarthingLpsException {

		if (earthingReport != null && earthingReport.getEarthingReportId() != null
				&& earthingReport.getEarthingReportId() != 0 && earthingReport.getBasicLpsId() != null
				&& earthingReport.getBasicLpsId() != 0) {
			Optional<EarthingReport> earthingLpsRepo = earthingLpsRepository
					.findById(earthingReport.getEarthingReportId());
			if (earthingLpsRepo.isPresent()
					&& earthingLpsRepo.get().getBasicLpsId().equals(earthingReport.getBasicLpsId())) {
				earthingReport.setUpdatedDate(LocalDateTime.now());
				earthingReport.setUpdatedBy(userFullName.findByUserName(earthingReport.getUserName()));
				earthingLpsRepository.save(earthingReport);
			} else {
				throw new EarthingLpsException("Given Basic LPS Id and Earthing LPS Id is Invalid");
			}

		} else {
			throw new EarthingLpsException("Invalid inputs");
		}
	}
}
